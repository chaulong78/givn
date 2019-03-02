package com.msp.givn.controller.admin.user;

import com.msp.givn.entity.Mail;
import com.msp.givn.entity.PasswordResetToken;
import com.msp.givn.entity.User;
import com.msp.givn.service.email.EmailService;
import com.msp.givn.service.user.PasswordResetTokenService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "")
    public ModelAndView showForgotPasswordForm() {
        String email = "";
        ModelAndView modelAndView = new ModelAndView("forgot-password-form");
        modelAndView.addObject("email", email);

        return modelAndView;
    }

    @PostMapping(value = "/process")
    public ModelAndView processSendMail(
            @RequestParam(value = "email") String email, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/forgot-password");
        String message;

        if (email != null) {
            User user = userService.findByEmail(email);
            if (user != null) {
                PasswordResetToken passwordResetToken = passwordResetTokenService.createNewResetPasswordToken(user);
                Mail mail = emailService.createEmail(user, passwordResetToken.getToken(), request);

                emailService.sendEmail(mail);
                message = "Email xác nhận đã được gửi đến hòm thư của bạn";
            } else {
                message = "Email không tồn tại";
            }
        } else {
            message = "Lỗi gửi email";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }
}
