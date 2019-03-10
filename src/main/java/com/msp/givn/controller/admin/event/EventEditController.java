package com.msp.givn.controller.admin.event;

import com.msp.givn.entity.Event;
import com.msp.givn.entity.EventSpeaker;
import com.msp.givn.entity.Speaker;
import com.msp.givn.service.event.EventService;
import com.msp.givn.service.event.EventSpeakerService;
import com.msp.givn.service.event.SpeakerService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/event")
public class EventEditController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private EventSpeakerService eventSpeakerService;

    @GetMapping(value = "/edit")
    public ModelAndView showAddForm(@RequestParam(value = "id") Integer id, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("admin/event/edit-event");

        if (id != null) {
            Event event = eventService.findById(id);
            if (event != null) {
                modelAndView.addObject("event", event);

                List<EventSpeaker> eventSpeakerList = eventSpeakerService.findAllES();
                modelAndView.addObject("eventSpeakerList", eventSpeakerList);

                List<Speaker> speakerList = speakerService.findAll();
                modelAndView.addObject("speakerList", speakerList);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/admin/event");
        ra.addFlashAttribute("message", "Sự kiện không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView processAdd(
            @ModelAttribute(value = "event") Event event
            , @RequestParam(value = "checkbox", required = false) String[] checkBox
            , @RequestParam(value = "enabled", required = false) String enabled
            , BindingResult result, RedirectAttributes ra
    ) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event");

        if (!result.hasErrors()) {
            if (enabled != null) {
                event.setEnabled(true);
            } else {
                event.setEnabled(false);
            }

            String url = "/su-kien/" + StringFunction.convertNameToUrl(event.getName());
            event.setUrlName(url);

            eventService.save(event);
            if (checkBox!=null) {
                eventService.updateSpeakerForEvent(checkBox, event.getId());
            }

            ra.addFlashAttribute("message", "Cập nhật thông tin thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Cập nhật thông tin thất bại");
        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteSpeaker(@RequestParam(value = "id") Integer id, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event");
        if (id != null) {
            eventService.deleteById(id);
            ra.addFlashAttribute("message", "Xóa sự kiện thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Sự kiện không tồn tại");
        return modelAndView;
    }
}
