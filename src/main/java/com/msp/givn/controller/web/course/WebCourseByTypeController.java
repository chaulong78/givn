package com.msp.givn.controller.web.course;

import com.msp.givn.dto.CourseDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseDTOService;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/danh-muc")
public class WebCourseByTypeController {

    @Autowired
    private CourseDTOService dtoService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "/{type}")
    public ModelAndView showCoursesByType(@PathVariable(value = "type") String url){
        ModelAndView modelAndView = new ModelAndView("web/courses-by-type");

        if (url!=null){
            List<CourseDTO> courseList = dtoService.findByTypeUrl(url);
            modelAndView.addObject("courseList", courseList);

            /*For menu*/
            List<CourseType> courseTypeList = courseTypeService.findAll();
            modelAndView.addObject("courseTypeList", courseTypeList);
            List<PostType> postTypeList = postTypeService.findAll();
            modelAndView.addObject("postTypeList", postTypeList);
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/404");
        return modelAndView;
    }
}
