package com.msp.givn.service.media;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;

public interface LocalUploadService {

    InputStream doLocalOne(HttpServletRequest request, MultipartFile file);

    HashMap<String, InputStream> doLocalMulti(HttpServletRequest request, MultipartFile[] files);
}
