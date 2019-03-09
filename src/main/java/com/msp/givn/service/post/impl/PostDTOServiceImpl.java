package com.msp.givn.service.post.impl;

import com.msp.givn.dao.post.PostDTODao;
import com.msp.givn.dto.PostDTO;
import com.msp.givn.service.post.PostDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDTOServiceImpl implements PostDTOService {

    @Autowired
    private PostDTODao postDTODao;

    @Override
    public List<PostDTO> findAll() {
        return postDTODao.findAll();
    }

    @Override
    public List<PostDTO> findAllWithOutContent() {
        return postDTODao.findAllWithOutContent();
    }

    @Override
    public List<PostDTO> findByTypeUrl(String url) {
        return postDTODao.findByTypeUrl(url);
    }

    @Override
    public PostDTO findByUrlName(String urlName) {
        return postDTODao.findByUrlName(urlName);
    }
}
