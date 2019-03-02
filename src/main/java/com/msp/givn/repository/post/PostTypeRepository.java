package com.msp.givn.repository.post;

import com.msp.givn.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Integer> {

    void deleteById(int id);

    PostType findById(int id);

    PostType findByName(String name);
}
