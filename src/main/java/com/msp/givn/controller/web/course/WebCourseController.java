package com.msp.givn.controller.web.course;

import com.msp.givn.dto.CourseDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseDTOService;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/khoa-hoc")
public class WebCourseController {

    @Autowired
    private CourseDTOService courseDTOService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showAllCourse() {
        ModelAndView modelAndView = new ModelAndView("web/courses");
        List<CourseDTO> courseList = courseDTOService.findAll();
        modelAndView.addObject("courseList", courseList);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/{name}")
    public ModelAndView showAllCourse(@PathVariable(value = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("web/course-single");

        if (name != null) {
            CourseDTO course = courseDTOService.findByUrlName("/khoa-hoc/" + name);
            if (course != null) {
                modelAndView.addObject("course", course);

                List<CourseDTO> courseList = courseDTOService.findAll();
                modelAndView.addObject("courseList", courseList);

                List<CourseDTO> otherCourses = courseDTOService.find3Other();
                modelAndView.addObject("otherCourses", otherCourses);

                /*For menu*/
                List<CourseType> courseTypeList = courseTypeService.findAll();
                modelAndView.addObject("courseTypeList", courseTypeList);
                List<PostType> postTypeList = postTypeService.findAll();
                modelAndView.addObject("postTypeList", postTypeList);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/404");
        return modelAndView;
    }
}
