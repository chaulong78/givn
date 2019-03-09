package com.msp.givn.repository.event;

import com.msp.givn.entity.Speaker;
import com.msp.givn.repository.event.custom.SpeakerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Integer>, SpeakerRepositoryCustom {

    List<Speaker> findAll();

    Speaker findById(int id);

    void deleteById(int id);
}
