package com.msp.givn.service.post.impl;

import com.msp.givn.entity.Post;
import com.msp.givn.repository.post.PostRepository;
import com.msp.givn.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByAuthorId(int id) {
        return postRepository.findByAuthorId(id);
    }

    @Override
    public List<Post> findByTypeId(int id) {
        return postRepository.findByTypeId(id);
    }

    @Override
    public List<Post> getNewestPost() {
        return postRepository.getNewestPost();
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void updateList(List<Post> list) {
        postRepository.updateList(list);
    }

    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public String findNameById(int id) {
        return null;
    }
}
