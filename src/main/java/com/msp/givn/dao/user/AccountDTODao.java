package com.msp.givn.dao.user;

import com.msp.givn.dto.AccountDTO;

import java.util.List;

public interface AccountDTODao {

    List<AccountDTO> findAll();

    AccountDTO findById(int id);
}
