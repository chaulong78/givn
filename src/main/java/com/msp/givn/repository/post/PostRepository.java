package com.msp.givn.repository.post;

import com.msp.givn.entity.Post;
import com.msp.givn.repository.post.custom.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {

    Post findById(int id);

    List<Post> findByAuthorId(int id);

    List<Post> findByTypeId(int id);

    void deleteById(int id);
}
