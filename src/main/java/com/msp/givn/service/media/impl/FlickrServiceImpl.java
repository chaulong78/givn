package com.msp.givn.service.media.impl;

import com.msp.givn.dao.media.FlickrDAO;
import com.msp.givn.dao.media.LocalUploadDAO;
import com.msp.givn.service.media.FlickrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@Service
public class FlickrServiceImpl implements FlickrService {

    @Autowired
    private FlickrDAO flickrDAO;

    @Autowired
    private LocalUploadDAO localUploadDAO;

    @Override
    @Transactional
    public String savePhoto(InputStream photo, String fileName) {
        return flickrDAO.savePhoto(photo, fileName);
    }

    @Override
    @Transactional
    public List<String> savePhotoMulti(HashMap<String, InputStream> photoList) {
        return flickrDAO.savePhotoMulti(photoList);
    }

    @Override
    @Transactional
    public String uploadPhoto(HttpServletRequest request, MultipartFile file) {
        InputStream stream = localUploadDAO.doLocalOne(request, file);
        String urlPhoto = savePhoto(stream, file.getName());
        deleteLocalPhoto(file, request);
        return urlPhoto;
    }

    @Override
    public List<String> uploadPhotoMulti(HttpServletRequest request, MultipartFile[] files) {
        HashMap<String, InputStream> streamList = localUploadDAO.doLocalMulti(request, files);
        List<String> urlList = savePhotoMulti(streamList);
        return urlList;
    }

    @Override
    public String getPhotoId(String url) {
        String photoId = "";

        int length = url.length();
        int begin = 0;
        int end = 0;
        for (int i = 0; i < length; i++) {
            if (begin == 4) {
                begin = i;
                for (int j = i; j < length; j++) {
                    if (url.charAt(j) == '_') {
                        end = j;
                        break;
                    }
                }
                break;
            }
            if (url.charAt(i) == '/') {
                begin++;
            }
        }

        photoId = url.substring(begin, end);
        return photoId;
    }

    @Override
    public void delete(String photoId) {
        flickrDAO.delete(photoId);
    }

    private void deleteLocalPhoto(MultipartFile file, HttpServletRequest request) {
        String uploadRootPath = request.getServletContext().getRealPath("media");
        String pathNoCom = uploadRootPath + "\\" + file.getOriginalFilename();
//        String pathCom = uploadRootPath + "\\comp_" + file.getOriginalFilename();

        File file1 = new File(pathNoCom);
        file1.delete();
    }
}
