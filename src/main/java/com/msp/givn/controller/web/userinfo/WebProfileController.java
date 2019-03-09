package com.msp.givn.controller.web.userinfo;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.media.FlickrService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.ProfileDTOService;
import com.msp.givn.service.user.UserDetailService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class WebProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService detailService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ProfileDTOService profileDTOService;

    @Autowired
    private FlickrService flickrService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showUserProfile() {
        ModelAndView modelAndView = new ModelAndView("web/profile");
        int id = userService.getCustomUserDetail().getUserId();

        ProfileDTO profile = profileDTOService.findById(id);
        profile.setId(id);
        modelAndView.addObject("profile", profile);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @PostMapping(value = "")
    public ModelAndView processUserProfile(@ModelAttribute(value = "profile") ProfileDTO profile
            , @RequestParam(value = "file", required = false) MultipartFile file
            , BindingResult result, RedirectAttributes ra, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        CustomUserDetail currentUser = userService.getCustomUserDetail();
        int id = currentUser.getUserId();

        if (!result.hasErrors() && profile.getId() == id) {
            String profileEmail = profile.getEmail();

            /*Email has been changed*/
            if (!profileEmail.equals(currentUser.getEmail())) {
                if (!registerService.validateEmailPattern(profileEmail)) {
                    ra.addFlashAttribute("message", "Email không đúng định dạng");
                    return modelAndView;
                } else if (userService.findByEmail(profileEmail) != null) {
                    ra.addFlashAttribute("message", "Email đã được sử dụng");
                    return modelAndView;
                } else {
                    /*Update local and session email*/
                    userService.updateEmail(id, profileEmail);
                    currentUser.setEmail(profileEmail);
                }
            }

            /*Update local and session avatar*/
            if (!"".equals(file.getOriginalFilename())) {
                String avatar = flickrService.uploadPhoto(request, file);
                userService.updateAvatar(id, avatar);
                currentUser.setAvatar(avatar);
            }

            detailService.update(profile, id);
            ra.addFlashAttribute("message", "Cập nhật thông tin thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Cập nhật thông tin không thành công");
        return modelAndView;
    }
}
