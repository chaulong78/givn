package com.msp.givn.controller.admin.user;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.PasswordChangeDTO;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/info")
public class ChangePasswordController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/change-password")
    public ModelAndView showChangePasswordForm() {
        ModelAndView modelAndView = new ModelAndView("admin/user-info/change-password-form");

        PasswordChangeDTO passwordDTO = new PasswordChangeDTO();
        modelAndView.addObject("passwordDTO", passwordDTO);
        return modelAndView;
    }

    @PostMapping("/change-password")
    public ModelAndView updatePassword(
            @ModelAttribute("passwordDTO") PasswordChangeDTO passwordDTO
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/info/change-password");
        String message;

        if (!result.hasErrors()) {
            CustomUserDetail user = userService.getCustomUserDetail();

            String oldEncoded = userService.getPasswordById(user.getUserId());
            String oldRaw = passwordDTO.getOldPassword();
            String newp = passwordDTO.getPassword();
            String again = passwordDTO.getPasswordAgain();
            message = registerService.validPasswordReset(newp, again);

            if (message == null) {
                /* Nếu khớp password cũ */
                if (encoder.matches(oldRaw, oldEncoded)) {
                    if (newp.equals(oldRaw)) {
                        modelAndView.setViewName("redirect:/admin");
                        message = "Cập nhật mật khẩu thành công";
                    } else if (userService.updatePassword(user.getUserId(), encoder.encode(newp))) {
                        message = "Cập nhật mật khẩu thành công";
                    } else {
                        message = "Cập nhật mật khẩu thất bại";
                    }
                } else {
                    message = "Mật khẩu cũ không đúng";
                }
            }
        } else {
            message = "Cập nhật mật khẩu thât bại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }
}
