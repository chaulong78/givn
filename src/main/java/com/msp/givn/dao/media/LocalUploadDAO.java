package com.msp.givn.dao.media;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;

public interface LocalUploadDAO {

    InputStream doLocalOne(HttpServletRequest request, MultipartFile file);

    HashMap<String, InputStream> doLocalMulti(HttpServletRequest request, MultipartFile[] files);
}
