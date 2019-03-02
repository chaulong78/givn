package com.msp.givn.dao.user;

import com.msp.givn.dto.ProfileDTO;

public interface ProfileDTODao {

    ProfileDTO findById(int id);
}
