package com.msp.givn.controller.admin.post;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.PostDTO;
import com.msp.givn.entity.Post;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.post.PostDTOService;
import com.msp.givn.service.post.PostService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/admin/event/post")
public class PostController {

    @Autowired
    private PostDTOService postDTOService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostTypeService typeService;

    @GetMapping(value = "")
    public ModelAndView showPostTable() {
        ModelAndView modelAndView = new ModelAndView("admin/post/table-post");
        List<PostDTO> postList = postDTOService.findAll();

        modelAndView.addObject("postList", postList);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddTypeForm() {
        ModelAndView modelAndView = new ModelAndView("admin/post/add-post");
        Post post = new Post();
        List<PostType> typeList = typeService.findAll();

        modelAndView.addObject("post", post);
        modelAndView.addObject("typeList", typeList);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView processAddPost(@ModelAttribute("post") Post post
            , @RequestParam(value = "enabled", required = false) String checkBox
            , @RequestParam(value = "file", required = false) MultipartFile[] files
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/post");

        if (!result.hasErrors()) {
            addAuthorAndDateForPost(post);
            setEnable(post, checkBox);

            post.setUrlName("/tin-tuc/" + StringFunction.convertNameToUrl(post.getName()));
            postService.save(post);
            redirectAttributes.addFlashAttribute("message", "Tạo bài viết thành công");
        } else {
            redirectAttributes.addFlashAttribute("message", "Tạo bài viết thất bại");
        }

        return modelAndView;
    }

    private void addAuthorAndDateForPost(Post post) {
        int authorId = ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        post.setAuthorId(authorId);

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        post.setCreateDate(date);
    }

    private void setEnable(Post post, String checkBox){
        if (checkBox!=null){
            post.setEnabled(true);
        } else {
            post.setEnabled(false);
        }
    }
}
