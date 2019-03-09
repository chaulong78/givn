package com.msp.givn.service.post;

import com.msp.givn.dto.PostDTO;

import java.util.List;

public interface PostDTOService {

    List<PostDTO> findAll();

    List<PostDTO> findAllWithOutContent();

    List<PostDTO> findByTypeUrl(String url);

    PostDTO findByUrlName(String urlName);
}
