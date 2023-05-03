package com.example.dotestonline.service;

import com.example.dotestonline.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccount();
    AccountDTO getOneAccount(Long id);
    AccountDTO getOneAccountByUserName(String userName);

    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO updateAccount(AccountDTO accountDTO);
    void deleteAccount(Long id);
}
