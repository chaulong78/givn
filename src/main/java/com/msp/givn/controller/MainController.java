package com.msp.givn.controller;

import com.msp.givn.service.media.FlickrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private FlickrService flickrService;

    @GetMapping(value = "/admin")
    public String showAdminPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = ((List<GrantedAuthority>) auth.getAuthorities()).get(0).toString();

        if ("ROLE_ADMIN".equals(role) || "ROLE_MANAGER".equals(role) || "ROLE_POSTER".equals(role)) {
            return "admin/home";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String showHomePage() {
        return "web/home";
    }

    @GetMapping(value = "/login")
    public ModelAndView showLoginForm(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("login-form");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = ((List<GrantedAuthority>) auth.getAuthorities()).get(0).toString();

        if (!("ROLE_ANONYMOUS".equals(role))) {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping(value = "/logout")
    public String performLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Cookie[] cookies = request.getCookies();
            int cookieLength = cookies.length;

            Cookie tempCookie = null;
            for (int i = 0; i < cookieLength; i++) {
                tempCookie = cookies[i];
                if (tempCookie.getName().equals("remember-me")) {
                    persistentTokenRepository.removeUserTokens(auth.getName());
                    break;
                }
            }

            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @GetMapping(value = "/admin/statistic")
    public String showStatisticPage() {
        return "admin/statistic/view";
    }

    @PostMapping(value = "/upload")
    public String testUploadMulti(@RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request) {

        List<String> urlList = flickrService.uploadPhotoMulti(request, files);
        System.out.println(urlList);

        return null;
    }
}
