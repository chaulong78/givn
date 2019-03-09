package com.msp.givn.controller.admin.event;

import com.msp.givn.entity.Speaker;
import com.msp.givn.service.event.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/event/speaker")
public class SpeakerEditController {

    @Autowired
    private SpeakerService speakerService;

    @GetMapping(value = "/edit")
    public ModelAndView showAddForm(@RequestParam(value = "id") Integer id, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("admin/speaker/edit-speaker");

        if (id != null) {
            Speaker speaker = speakerService.findById(id);
            if (speaker != null) {
                modelAndView.addObject("speaker", speaker);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/admin/event/speaker");
        ra.addFlashAttribute("message", "Diễn giả không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView processAdd(@ModelAttribute(value = "speaker") Speaker speaker, BindingResult result, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/speaker");

        if (!result.hasErrors()) {
            speakerService.save(speaker);
            ra.addFlashAttribute("message", "Cập nhật thông tin thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Cập nhật thông tin thất bại");
        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteSpeaker(@RequestParam(value = "id") Integer id, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/speaker");
        if (id != null) {
            speakerService.deleteById(id);
            ra.addFlashAttribute("message", "Xóa diễn giả thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Diễn giả không tồn tại");
        return modelAndView;
    }

}
