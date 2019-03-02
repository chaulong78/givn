package com.msp.givn.controller.admin.user;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.service.media.FlickrService;
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
import java.io.File;

@Controller
@RequestMapping("/admin/info")
public class ProfileController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileDTOService profileDTOService;

    @Autowired
    private FlickrService flickrService;

    @GetMapping("/profile")
    public ModelAndView showProfile() {
        ModelAndView modelAndView = new ModelAndView("admin/user-info/profile");
        CustomUserDetail user = userService.getCustomUserDetail();

        ProfileDTO profileDTO = profileDTOService.findById(user.getUserId());
        profileDTO.setId(user.getUserId());
        profileDTO.setAvatar(user.getAvatar());

        modelAndView.addObject("profileDTO", profileDTO);
        return modelAndView;
    }

    @PostMapping("/profile")
    public ModelAndView updateProfile(
            @ModelAttribute("profileDTO") ProfileDTO profileDTO
            , @RequestParam(value = "file", required = false) MultipartFile file
            , BindingResult result
            , RedirectAttributes redirectAttributes
            , HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/info/profile");
        CustomUserDetail user = userService.getCustomUserDetail();
        String message;

        if (!result.hasErrors()) {
            int userId = user.getUserId();

            /* Kiểm tra username ko bị thay đổi */
            if (user.getUsername().equals(profileDTO.getUserName())) {
                /* Nếu email bị thay đổi */
                if (!user.getEmail().equals(profileDTO.getEmail())) {
                    if (userService.findByEmail(profileDTO.getEmail()) == null) {
                        userService.updateSessionEmail(user, profileDTO.getEmail());
                        userService.updateEmail(userId, profileDTO.getEmail());
                    } else {
                        message = "Email đã tồn tại";
                        redirectAttributes.addFlashAttribute("message", message);
                        return modelAndView;
                    }
                }

                /*Cập nhật user detail*/
                userDetailService.update(profileDTO, userId);

                /*Cập nhật avatar*/
                if (!"".equals(file.getOriginalFilename())) {
                    String avatar = flickrService.uploadPhoto(request, file);
                    /*Cập nhật avatar cho session và cho database*/
                    userService.updateSessionAvatar(user, avatar);
                    userService.updateAvatar(userId, avatar);
                }

                message = "Cập nhật thông tin thành công";
            } else {
                message = "Lỗi cập nhật";
            }
        } else {
            message = "Cập nhật thông tin thất bại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }
}
