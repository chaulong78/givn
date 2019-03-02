package com.msp.givn.controller.admin.user;

import com.msp.givn.entity.PasswordResetToken;
import com.msp.givn.entity.User;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.PasswordResetTokenService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/reset-password")
public class ResetPasswordController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "")
    public ModelAndView processConfirmationLink(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/forgot-password");
        PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(token);
        String message = "";

        if (passwordResetToken != null) {
            if (!passwordResetTokenService.validateExpiry(passwordResetToken)) {
                message = "Mã xác thực đã hết hạn";
            } else {
                modelAndView.addObject("token", token);
                modelAndView.setViewName("reset-password-form");
            }
        } else {
            message = "Mã xác thực không tồn tại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }

    @PostMapping(value = "/reset")
    public ModelAndView processResetPassword(
            @RequestParam("password") String password
            , @RequestParam("passwordAgain") String again
            , @RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/reset-password?token=" + token);
        String message;
        User user = passwordResetTokenService.findByToken(token).getUser();

        if (user != null && password != null && again != null) {
            message = registerService.validPasswordReset(password, again);

            if (message == null) {
                user.setPassword(encoder.encode(password));
                userService.save(user);
                passwordResetTokenService.deleteById(user.getId());

                message = "Khôi phục mật khẩu thành công";
                redirectAttributes.addFlashAttribute("message", message);
                modelAndView.setViewName("redirect:/login");
                return modelAndView;
            }
        } else {
            message = "Lỗi khi khôi phục mật khẩu";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }
}
