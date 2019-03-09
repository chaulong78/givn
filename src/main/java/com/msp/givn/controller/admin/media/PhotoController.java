package com.msp.givn.controller.admin.media;

import com.msp.givn.entity.Image;
import com.msp.givn.service.media.FlickrService;
import com.msp.givn.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/media/photo")
public class PhotoController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private FlickrService flickrService;

    @RequestMapping("")
    public ModelAndView showAllPhoto() {
        ModelAndView modelAndView = new ModelAndView("admin/photo/table-photo");
        List<Image> imageList = imageService.findAll();
        modelAndView.addObject("imageList", imageList);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addPhoto() {
        return "admin/photo/add-photo";
    }

    @PostMapping("/add")
    public ModelAndView processAddPhoto(@RequestParam(value = "file", required = false) MultipartFile[] files
            , HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/media/photo");

        if (files.length > 0) {
            List<String> urlList = flickrService.uploadPhotoMulti(request, files);
            imageService.saveAll(urlList);
            redirectAttributes.addFlashAttribute("message", "Upload hình ảnh thành công");
            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Không có hình ảnh nào được upload");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deletePhoto(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/media/photo");
        if (id != null) {
            String photoId = flickrService.getPhotoId(imageService.findUrlById(id));
            flickrService.delete(photoId);

            imageService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Xóa hình ảnh thành công");
        }

        redirectAttributes.addFlashAttribute("message", "Hình ảnh không tồn tại");
        return modelAndView;
    }

}
