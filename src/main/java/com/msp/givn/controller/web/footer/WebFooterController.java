package com.msp.givn.controller.web.footer;

import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebFooterController {

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "/lien-he")
    public ModelAndView contactUs(){
        ModelAndView modelAndView = new ModelAndView("web/contact");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/danh-cho-hoc-vien")
    public ModelAndView forStudent(){
        ModelAndView modelAndView = new ModelAndView("web/for-student");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/chinh-sach")
    public ModelAndView securityPolicy(){
        ModelAndView modelAndView = new ModelAndView("web/policy");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/dieu-khoan")
    public ModelAndView termsOfUse(){
        ModelAndView modelAndView = new ModelAndView("web/term");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/giao-dich")
    public ModelAndView transaction(){
        ModelAndView modelAndView = new ModelAndView("web/transaction");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/tuyen-dung")
    public ModelAndView recruit(){
        ModelAndView modelAndView = new ModelAndView("web/recruitment");

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }
}
