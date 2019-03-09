package com.msp.givn.service.post;

import com.msp.givn.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    List<Post> findByAuthorId(int id);

    List<Post> findByTypeId(int id);

    List<Post> getNewestPost();

    Post findById(int id);

    String findNameById(int id);

    Post save(Post post);

    void updateList(List<Post> list);

    void delete(int id);
}
