package com.msp.givn.service.media;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public interface FlickrService {

    String savePhoto(InputStream photo, String fileName);

    List<String> savePhotoMulti(HashMap<String, InputStream> photoList);

    String uploadPhoto(HttpServletRequest request, MultipartFile file);

    List<String> uploadPhotoMulti(HttpServletRequest request, MultipartFile[] files);

    String getPhotoId(String url);

    void delete(String photoId);
}
