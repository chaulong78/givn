package com.msp.givn.controller.admin.system;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.FunctionAuthorizeDTO;
import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.Role;
import com.msp.givn.entity.RoleFunction;
import com.msp.givn.service.function.FunctionAuthorizeDTOService;
import com.msp.givn.service.function.FunctionDTOService;
import com.msp.givn.service.role.RoleFunctionService;
import com.msp.givn.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/system")
public class RoleAuthorizeController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Autowired
    private FunctionDTOService functionDTOService;

    @Autowired
    private FunctionAuthorizeDTOService functionAuthorizeDTOService;

    @GetMapping(value = "/role/authorize")
    public ModelAndView showAuthorizeForm(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/role/authorize-role");

        if (id != null) {
            Role role = roleService.findById(id);
            List<FunctionAuthorizeDTO> functionAuthorizeDTOList = functionAuthorizeDTOService.findAllByRoleId(id);

            if (role == null || functionAuthorizeDTOList == null || functionAuthorizeDTOList.size() == 0) {
                modelAndView.setViewName("redirect:/admin/system/role");
                redirectAttributes.addFlashAttribute("message", "Quyền không tồn tại");
                return modelAndView;
            }

            modelAndView.addObject("role", role);
            modelAndView.addObject("functionAuthorizeDTOList", functionAuthorizeDTOList);
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/admin/system/role");
        redirectAttributes.addFlashAttribute("message", "Quyền không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/role/authorize")
    public ModelAndView updateAuthorizeRole(
            RedirectAttributes redirectAttributes,
            @RequestParam("roleId") int roleId,
            @RequestParam(value = "view", required = false) List<String> views,
            @RequestParam(value = "create", required = false) List<String> creates,
            @RequestParam(value = "update", required = false) List<String> updates,
            @RequestParam(value = "delete", required = false) List<String> deletes) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/system/role");
        String message;
        Role role = roleService.findById(roleId);

        if (role != null) {
            /* Lấy ra bảng role_function của role cần cập nhật theo role_id */
            List<RoleFunction> roleFunctionList = roleFunctionService.findAllByRoleId(role.getId());

            /* Cập nhật quyền cho role_function
             * Set false cho tất cả các function
             * Lặp các list view, create, ... để gán giá trị mới
             * Lưu
             * */
            roleFunctionService.setFalseAllFunction(roleFunctionList);
            roleFunctionService.updateAllFunction(roleFunctionList, views, creates, updates, deletes);
            roleFunctionService.saveList(roleFunctionList);

            /* Lấy ra danh sách functionDTO mới để gán các chức năng trên giao diện */
            List<FunctionDTO> functionDTOList = functionDTOService.findByRoleId(role.getId());
            /* Lấy ra danh sách người dùng đang login và có role là role đang được sửa */
            List<CustomUserDetail> customUserDetailList = getLoggedUser(role.getName());

            /* Cập nhật quyền cho các người dùng vừa tìm */
            functionDTOService.updateFunctionDTOForUsers(functionDTOList, customUserDetailList);
            message = "Cập nhật chức năng cho quyền thành công";
        } else {
            message = "Cập nhật chức năng cho quyền thất bại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }

    private List<CustomUserDetail> getLoggedUser(String roleName) {
        /* Lấy ra những session có roleId = roleId vừa sửa */
        List<CustomUserDetail> principalList = (List<CustomUserDetail>) (List<?>) sessionRegistry.getAllPrincipals();
        List<CustomUserDetail> editedUserList = new ArrayList<>();
        if (roleName != null) {
            for (CustomUserDetail custom : principalList) {
                Object[] objects = custom.getAuthorities().toArray();

                if (objects[0].toString().equals(roleName)) {
                    editedUserList.add(custom);
                }
            }
        }
        return editedUserList;
    }
}
