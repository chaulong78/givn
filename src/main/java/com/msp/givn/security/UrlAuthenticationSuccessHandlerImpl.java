package com.msp.givn.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class UrlAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        String targetUrl = determineTargetUrl(auth);

        if (response.isCommitted()) {
            /*Print log*/
            return;
        }

        try {
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        /*
         * TRUE to create a new session for this request if necessary
         * FALSE to return NULL if there's no current session
         * */
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    protected String determineTargetUrl(Authentication auth) {
        boolean isUser = false;
        boolean isAdmin = false;

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        for (GrantedAuthority ga : authorities) {

            if ("ROLE_USER".equals(ga.toString())){
                isUser = true;
                break;
            } else {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "/";
        } else if (isAdmin) {
            return "/admin";
        } else {
            throw new IllegalStateException();
        }
    }
}