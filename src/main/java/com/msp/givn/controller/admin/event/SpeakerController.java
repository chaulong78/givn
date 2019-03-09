package com.msp.givn.controller.admin.event;

import com.msp.givn.entity.Speaker;
import com.msp.givn.service.event.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/event/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @GetMapping(value = "")
    public ModelAndView showAllSpeaker() {
        ModelAndView modelAndView = new ModelAndView("admin/speaker/table-speaker");
        List<Speaker> speakerList = speakerService.findAll();
        modelAndView.addObject("speakerList", speakerList);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("admin/speaker/add-speaker");
        Speaker speaker = new Speaker();
        modelAndView.addObject("speaker", speaker);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView processAdd(@ModelAttribute(value = "speaker") Speaker speaker, BindingResult result, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/speaker");

        if (!result.hasErrors()) {
            speakerService.save(speaker);
            ra.addFlashAttribute("message", "Tạo diễn giả thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Tạo diễn giả thất bại");
        return modelAndView;
    }
}
