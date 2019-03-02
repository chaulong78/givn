package com.msp.givn.service.media.impl;

import com.msp.givn.entity.Image;
import com.msp.givn.repository.media.ImageRepository;
import com.msp.givn.service.media.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(int id) {
        return imageRepository.findById(id);
    }

    @Override
    public String findUrlById(int id) {
        return imageRepository.findUrlById(id);
    }

    @Override
    @Transactional
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void saveAll(List<String> urlList) {
        imageRepository.saveAllImage(urlList);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        imageRepository.deleteById(id);
    }
}
