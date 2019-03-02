package com.msp.givn.service.media;

import com.msp.givn.entity.Image;

import java.util.List;

public interface ImageService {

    List<Image> findAll();

    Image findById(int id);

    String findUrlById(int id);

    void save(Image image);

    void saveAll(List<String> urlList);

    void deleteById(int id);
}
