package com.msp.givn.dao.post;

import com.msp.givn.dto.PostDTO;

import java.util.List;

public interface PostDTODao {

    List<PostDTO> findAll();
}
