package com.msp.givn.controller.web.userinfo;

import com.msp.givn.entity.*;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.email.EmailService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.service.user.PasswordResetTokenService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/forgot-pass")
public class WebForgotPassController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showForgotPasswordForm() {
        ModelAndView modelAndView = new ModelAndView("web/forgot-pass");
        modelAndView.addObject("email", null);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @PostMapping(value = "")
    public ModelAndView processSendMail(
            @RequestParam(value = "email") String email, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/forgot-pass");

        if (email != null) {
            User user = userService.findByEmail(email);
            if (user != null) {
                PasswordResetToken passwordResetToken = passwordResetTokenService.createNewResetPasswordToken(user);
                Mail mail = emailService.createEmail(user, passwordResetToken.getToken(), request);

                emailService.sendEmail(mail);
                redirectAttributes.addFlashAttribute("message", "Thư xác nhận đã được gửi đến email của bạn");
            } else {
                redirectAttributes.addFlashAttribute("message", "Email không tồn tại");
            }

            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Lỗi gửi email");
        return modelAndView;
    }
}
