package com.msp.givn.controller.web.post;

import com.msp.givn.dto.PostDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostDTOService;
import com.msp.givn.service.post.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/danh-muc-tin")
public class WebPostByTypeController {

    @Autowired
    private PostDTOService dtoService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "/{type}")
    public ModelAndView showCoursesByType(@PathVariable(value = "type") String url){
        ModelAndView modelAndView = new ModelAndView("web/posts-by-type");

        if (url!=null){
            List<PostDTO> postList = dtoService.findByTypeUrl(url);
            modelAndView.addObject("postList", postList);

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
