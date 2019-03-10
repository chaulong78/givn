package com.msp.givn.controller.admin.system;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.AccountDTO;
import com.msp.givn.entity.Role;
import com.msp.givn.entity.User;
import com.msp.givn.service.account.AccountDTOService;
import com.msp.givn.service.role.RoleService;
import com.msp.givn.service.user.UserRoleService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/account")
public class AccountAuthorizeController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountDTOService accountDTOService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry registry;

    @GetMapping(value = "/authorize")
    public ModelAndView showAuthorizeForm(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/account/authorize");

        if (id != null) {
            if (userService.findById(id) != null) {
                AccountDTO accountDTO = accountDTOService.findById(id);
                List<Role> roleList = roleService.findAll();

                if (accountDTO != null && roleList != null) {
                    modelAndView.addObject("accountDTO", accountDTO);
                    modelAndView.addObject("roleList", roleList);
                    return modelAndView;
                }
            }
        }

        modelAndView.setViewName("redirect:/admin/account");
        redirectAttributes.addFlashAttribute("message", "Tài khoản không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/authorize")
    public ModelAndView processAuthorize(
            @ModelAttribute("accountDTO") AccountDTO accountDTO
            , @RequestParam(value = "roleId") int roleId
            , @RequestParam(value = "enabled", required = false) String checkBox
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/account");

        if (!result.hasErrors()) {
            if (roleId == 1) {
                redirectAttributes.addFlashAttribute("message", "Không thể sửa tài khoản này");
                return modelAndView;
            }

            if (roleService.findById(roleId) != null) {
                userRoleService.updateRoleForUser(accountDTO.getId(), roleId);
                userService.updateEnabled(accountDTO.getId(), checkBox != null ? true : false);
                expireUser(accountDTO.getId());

                redirectAttributes.addFlashAttribute("message", "Cập nhât quyền thành công");
            } else {
                redirectAttributes.addFlashAttribute("message", "Cập nhât quyền thất bại");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Cập nhât quyền thất bại");
        }

        return modelAndView;
    }

    private void expireUser(int id) {
        List<CustomUserDetail> principalList = (List<CustomUserDetail>) (List<?>) registry.getAllPrincipals();
        List<SessionInformation> sessionInformationList;

        for (CustomUserDetail userDetail : principalList) {
            if (userDetail.getUserId() == id) {
                sessionInformationList = registry.getAllSessions(userDetail, true);

                if (sessionInformationList.size() > 0) {
                    sessionInformationList.get(0).expireNow();
                }
            }
        }
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteById(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/account");

        if (id != null) {
            User user = userService.findById(id);
            if (user != null) {
                if (user.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
                    redirectAttributes.addFlashAttribute("message", "Không thể xóa tài khoản này");
                } else {
                    userService.deleteById(id);
                    redirectAttributes.addFlashAttribute("message", "Xóa tài khoản thành công");
                }

                return modelAndView;
            }
        }

        redirectAttributes.addFlashAttribute("message", "Tài khoản không tồn tại");
        return modelAndView;
    }
}
