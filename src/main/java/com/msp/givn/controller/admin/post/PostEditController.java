package com.msp.givn.controller.admin.post;

import com.msp.givn.entity.Post;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.post.PostService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/event/post")
public class PostEditController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostTypeService typeService;

    @GetMapping(value = "/edit")
    public ModelAndView showEditForm(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/post/edit-post");

        if (id != null) {
            Post post = postService.findById(id);
            if (post != null) {
                List<PostType> typeList = typeService.findAll();

                modelAndView.addObject("post", post);
                modelAndView.addObject("typeList", typeList);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/admin/event/post");
        redirectAttributes.addFlashAttribute("message", "Bài viết không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView processEdit(@ModelAttribute("post") Post post
            , @RequestParam(value = "enabled", required = false) String checkBox
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/post");
        String message;

        if (!result.hasErrors()) {
            Object objectId = post.getId();

            if (objectId != null) {
                setEnabled(post, checkBox);
                post.setUrlName("/tin-tuc/" +StringFunction.convertNameToUrl(post.getName()));
                postService.save(post);
                message = "Sửa thể loại thành công";
            } else {
                message = "Thể loại không tồn tại";
            }
        } else {
            message = "Sửa thể loại thất bại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }

    private void setEnabled(Post post, String checkBox) {
        if (checkBox != null) {
            post.setEnabled(true);
        } else {
            post.setEnabled(false);
        }
    }

    @GetMapping(value = "/delete")
    public ModelAndView deletePost(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttribute) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/post");

        if (id != null) {
            if (postService.findById(id) != null) {
                postService.delete(id);
                redirectAttribute.addFlashAttribute("message", "Xóa bài viết thành công");
                return modelAndView;
            }
        }

        redirectAttribute.addFlashAttribute("message", "Bài viết không tồn tại");
        return modelAndView;
    }
}
