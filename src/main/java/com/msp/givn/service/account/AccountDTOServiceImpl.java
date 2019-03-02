package com.msp.givn.service.account;

import com.msp.givn.dao.user.AccountDTODao;
import com.msp.givn.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDTOServiceImpl implements AccountDTOService{

    @Autowired
    private AccountDTODao accountDTODao;

    @Override
    public List<AccountDTO> findAll() {
        return accountDTODao.findAll();
    }

    @Override
    public AccountDTO findById(int id) {
        return accountDTODao.findById(id);
    }
}
