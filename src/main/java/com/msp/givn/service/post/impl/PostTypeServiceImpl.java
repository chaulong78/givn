package com.msp.givn.service.post.impl;

import com.msp.givn.entity.PostType;
import com.msp.givn.repository.post.PostTypeRepository;
import com.msp.givn.service.post.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostTypeServiceImpl implements PostTypeService {

    @Autowired
    private PostTypeRepository typeRepository;

    @Override
    @Transactional
    public PostType save(PostType type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        typeRepository.deleteById(id);
    }

    @Override
    public List<PostType> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public PostType findById(int id) {
        return typeRepository.findById(id);
    }

    @Override
    public PostType findByName(String name) {
        return typeRepository.findByName(name);
    }
}
