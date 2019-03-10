package com.msp.givn.controller.admin.system;

import com.msp.givn.dto.AccountDTO;
import com.msp.givn.dto.PasswordChangeDTO;
import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.entity.User;
import com.msp.givn.service.account.AccountDTOService;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.ProfileDTOService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/account")
public class AccountChangePassController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private AccountDTOService accountDTOService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileDTOService profileDTOService;

    @GetMapping(value = "")
    public ModelAndView showAccountTable(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView("admin/account/table-account");

        if (id != null) {
            ProfileDTO profileDTO = profileDTOService.findById(id);
            modelAndView.setViewName("admin/account/detail");
            modelAndView.addObject("profileDTO", profileDTO);
            return modelAndView;
        }

        List<AccountDTO> accountDTOList = accountDTOService.findAll();
        modelAndView.addObject("accountDTOList", accountDTOList);
        return modelAndView;
    }

    @GetMapping(value = "/change-pass")
    public ModelAndView showChangePasswordForm(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/account/change-pass");

        if (id != null) {
            if (userService.findById(id) != null) {
                PasswordChangeDTO password = new PasswordChangeDTO();

                modelAndView.addObject("password", password);
                modelAndView.addObject("id", id);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/admin/account");
        redirectAttributes.addFlashAttribute("message", "Tài khoảng không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/change-pass")
    public ModelAndView processChangePass(
            @ModelAttribute("password") PasswordChangeDTO passwordChangeDTO
            , @RequestParam(value = "id") int id
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/system/account");
        String message;

        if (!result.hasErrors()) {
            User user = userService.findById(id);
            if (user != null) {
                if (user.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
                    message = "Không thể sửa tài khoản này";
                    redirectAttributes.addFlashAttribute("message", message);
                    return modelAndView;
                }

                String password = passwordChangeDTO.getPassword();
                message = registerService.validPasswordReset(password, passwordChangeDTO.getPasswordAgain());
                if (message == null) {
                    password = passwordEncoder.encode(password);
                    if (userService.updatePassword(id, password)) {
                        message = "Cập nhật mật khẩu thành công";
                    } else {
                        message = "Câp nhật mật khẩu thất bại";
                    }
                }
            } else {
                message = "Người dùng không tồn tại";
            }
        } else {
            message = "Câp nhật mật khẩu thất bại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }

}
