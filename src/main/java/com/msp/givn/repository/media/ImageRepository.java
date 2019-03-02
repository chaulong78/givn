package com.msp.givn.repository.media;

import com.msp.givn.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>, ImageRepositoryCustom {

    void deleteById(int id);

    Image findById(int id);
}
