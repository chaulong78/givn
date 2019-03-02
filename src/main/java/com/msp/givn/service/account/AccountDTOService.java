package com.msp.givn.service.account;

import com.msp.givn.dto.AccountDTO;

import java.util.List;

public interface AccountDTOService {

    List<AccountDTO> findAll();

    AccountDTO findById(int id);
}
