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
}
