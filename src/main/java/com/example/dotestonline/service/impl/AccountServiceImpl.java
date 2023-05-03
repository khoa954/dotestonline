package com.example.dotestonline.service.impl;

import com.example.dotestonline.dto.AccountDTO;
import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.exception.ResourceNotFoundException;
import com.example.dotestonline.model.Account;
import com.example.dotestonline.model.Answer;
import com.example.dotestonline.repository.AccountRepository;
import com.example.dotestonline.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    private ModelMapper modelMapper;
    private AccountRepository accountRepository;
@Autowired
    public AccountServiceImpl(ModelMapper modelMapper, AccountRepository accountRepository) {
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDTO> getAllAccount() {
        return null;
    }

    @Override
    public AccountDTO getOneAccount(Long id) {
        return null;
    }

    @Override
    public AccountDTO getOneAccountByUserName(String userName) {
        return mapToDTO(accountRepository.findByUserNameOrEmail(userName,userName).orElseThrow(()-> new ResourceNotFoundException("Account","Username",userName)));
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {

    }
    private AccountDTO mapToDTO(Account account){
        return modelMapper.map(account,AccountDTO.class);
    }
    private Account mapToEntity(AccountDTO accountDTO){
        return modelMapper.map(accountDTO,Account.class);
    }

}
