package com.msp.givn.repository.post.custom;

import com.msp.givn.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {

    void updateList(List<Post> list);

    List<Post> getNewestPost();
}
