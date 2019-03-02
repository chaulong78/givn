package com.msp.givn.controller.web.course;

import com.msp.givn.dto.CourseDTO;
import com.msp.givn.entity.Course;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.User;
import com.msp.givn.entity.UserDetail;
import com.msp.givn.service.course.CourseDTOService;
import com.msp.givn.service.course.CourseService;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.user.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/course")
public class WebCourseController {

    @Autowired
    private CourseDTOService courseDTOService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showAllCourse(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView("web/course/view");

        if (id != null) {
            modelAndView.setViewName("web/course/detail");
            Course course = courseService.findById(id);
            UserDetail userDetail = userDetailService.findById(id);
            CourseType type = courseTypeService.findById(course.getTypeId());

            modelAndView.addObject("course", course);
            modelAndView.addObject("type", type);
            modelAndView.addObject("userDetail", userDetail);
            return modelAndView;
        }

        List<CourseDTO> courseList = courseDTOService.findAll();
        modelAndView.addObject("courseList", courseList);
        return modelAndView;
    }
}
