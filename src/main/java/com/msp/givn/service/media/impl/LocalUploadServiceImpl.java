package com.msp.givn.service.media.impl;

import com.msp.givn.dao.media.LocalUploadDAO;
import com.msp.givn.service.media.LocalUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;

@Service
public class LocalUploadServiceImpl implements LocalUploadService {

    @Autowired
    private LocalUploadDAO localUploadDAO;

    @Override
    public InputStream doLocalOne(HttpServletRequest request, MultipartFile file) {
        return localUploadDAO.doLocalOne(request, file);
    }

    @Override
    public HashMap<String, InputStream> doLocalMulti(HttpServletRequest request, MultipartFile[] files) {
        return localUploadDAO.doLocalMulti(request, files);
    }
}
