package com.msp.givn.controller;

import com.msp.givn.dto.CourseDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.Event;
import com.msp.givn.entity.Post;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseDTOService;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.event.EventService;
import com.msp.givn.service.post.PostService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController extends HttpServlet {

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private CourseDTOService courseDTOService;

    @Autowired
    private PostService postService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;


    @GetMapping(value = "/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("web/login");
        List<GrantedAuthority> authList = ((List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        String role = authList.get(0).toString();

        if (!("ROLE_ANONYMOUS".equals(role))) {
            modelAndView.setViewName("redirect:/");
        }

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
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

        return "redirect:/";
    }

    @GetMapping(value = "/admin")
    public String showAdminPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = ((List<GrantedAuthority>) auth.getAuthorities()).get(0).toString();

        if ("ROLE_ADMIN".equals(role) || "ROLE_MANAGER".equals(role) || "ROLE_SALE".equals(role)) {
            return "admin/home";
        }
        return "redirect:/";
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView("web/home");
        List<CourseDTO> courseList = courseDTOService.findAll();
        modelAndView.addObject("courseList", courseList);

        List<Post> postList = postService.getNewestPost();
        modelAndView.addObject("postList", postList);

        Event event = eventService.getNewestEvent();
        modelAndView.addObject("event", event);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/404")
    public ModelAndView handleResourceNotFoundException() {
        ModelAndView modelAndView = new ModelAndView("web/404");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/tim-kiem")
    public ModelAndView findByName(@RequestParam(value = "khoa-hoc", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("web/courses");
        modelAndView.addObject("key", name);

        name = StringFunction.convertNameToUrl(name);

        if (name!=null){
            List<CourseDTO> courseList = courseDTOService.findMultiByUrlName(name);
            if (courseList.size() > 0) {
                modelAndView.addObject("courseList", courseList);
            }
        }

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/about-us")
    public ModelAndView aboutUs(){
        ModelAndView modelAndView = new ModelAndView("web/about");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }
}
