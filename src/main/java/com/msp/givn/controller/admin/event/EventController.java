package com.msp.givn.controller.admin.event;

import com.msp.givn.entity.Event;
import com.msp.givn.entity.Speaker;
import com.msp.givn.service.event.EventService;
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
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SpeakerService speakerService;

    @GetMapping(value = "")
    public ModelAndView showAllEvent() {
        ModelAndView modelAndView = new ModelAndView("admin/event/table-event");
        List<Event> eventList = eventService.findAll();
        modelAndView.addObject("eventList", eventList);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("admin/event/add-event");
        Event event = new Event();
        modelAndView.addObject("event", event);

        List<Speaker> speakerList = speakerService.findAll();
        modelAndView.addObject("speakerList", speakerList);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView processAdd(
            @ModelAttribute(value = "event") Event event
            , @RequestParam(value = "checkbox", required = false) String[] checkBox
            , @RequestParam(value = "enabled", required = false) String enabled
            , BindingResult result, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event");

        if (!result.hasErrors()) {
            if (enabled != null) {
                event.setEnabled(true);
            } else {
                event.setEnabled(false);
            }

            String url = "/su-kien/" + StringFunction.convertNameToUrl(event.getName());
            event.setUrlName(url);

            int id = eventService.save(event).getId();
            if (checkBox.length > 0) {
                eventService.saveSpeakerForEvent(checkBox, id);
            }

            ra.addFlashAttribute("message", "Tạo sự kiện thành công");
            return modelAndView;
        }

        ra.addFlashAttribute("message", "Tạo sự kiện thất bại");
        return modelAndView;
    }
}
