package com.msp.givn.controller.web.post;

import com.msp.givn.dto.PostDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.Post;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostDTOService;
import com.msp.givn.service.post.PostService;
import com.msp.givn.service.post.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/tin-tuc")
public class WebPostController {

    @Autowired
    private PostDTOService postDTOService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showAllPost() {
        ModelAndView modelAndView = new ModelAndView("web/posts");
        List<PostDTO> postList = postDTOService.findAllWithOutContent();
        modelAndView.addObject("postList", postList);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/{name}")
    public ModelAndView showSinglePost(@PathVariable(value = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("web/post-single");
        if (name != null) {
            PostDTO postDTO = postDTOService.findByUrlName("/tin-tuc/" + name);
            if (postDTO != null) {
                modelAndView.addObject("post", postDTO);

                /*Other post in right-side bar*/
                List<Post> postList = postService.getNewestPost();
                modelAndView.addObject("newPostList", postList);

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
