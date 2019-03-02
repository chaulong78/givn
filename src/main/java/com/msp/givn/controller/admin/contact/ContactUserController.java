package com.msp.givn.controller.admin.contact;

import com.msp.givn.entity.ContactUser;
import com.msp.givn.service.contact.ContactUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactUserController {

    @Autowired
    private ContactUserService contactService;

    @GetMapping("/admin/account/contact")
    public ModelAndView showAllContact() {
        ModelAndView modelAndView = new ModelAndView("admin/statistic/table-contact");
        List<ContactUser> contactUserList = contactService.findAll();

        modelAndView.addObject("contactList", contactUserList);
        return modelAndView;
    }

    @PostMapping("/contact/submit")
    public void saveContact(@ModelAttribute("contact") ContactUser contact) {
        if (contact != null) {
            contactService.save(contact);
        }
    }

    @GetMapping("/admin/account/contact/delete")
    public ModelAndView deleteContact(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/system/contact");
        if (id != null) {
            contactService.deleteById(id);
        }

        return modelAndView;
    }
}
