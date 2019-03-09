package com.msp.givn.controller.web.event;

import com.msp.givn.entity.*;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.event.EventService;
import com.msp.givn.service.event.SpeakerService;
import com.msp.givn.service.post.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/su-kien")
public class WebEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showAllEvent() {
        ModelAndView modelAndView = new ModelAndView("web/events");
        List<Event> eventList = eventService.findAllDesc();
        modelAndView.addObject("eventList", eventList);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @GetMapping(value = "/{name}")
    public ModelAndView showSingleEvent(@PathVariable(value = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("web/event-single");
        if (name != null) {
            Event event = eventService.findByUrlName("/su-kien/" + name);
            if (event != null) {
                modelAndView.addObject("event", event);

                List<Speaker> speakerList = speakerService.findByEvent(event.getId());
                modelAndView.addObject("speakerList", speakerList);

                /*For menu*/
                List<CourseType> courseTypeList = courseTypeService.findAll();
                modelAndView.addObject("courseTypeList", courseTypeList);
                List<PostType> postTypeList = postTypeService.findAll();
                modelAndView.addObject("postTypeList", postTypeList);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/404");
        return modelAndView;
    }
}
