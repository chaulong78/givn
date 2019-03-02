package com.msp.givn.service.post;

import com.msp.givn.entity.PostType;

import java.util.List;

public interface PostTypeService {

    PostType save(PostType type);

    void deleteById(int id);

    List<PostType> findAll();

    PostType findById(int id);

    PostType findByName(String name);
}
