package com.example.dotestonline.service.impl;

import com.example.dotestonline.dto.AccountQuizDTO;
import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.model.AccountQuiz;
import com.example.dotestonline.model.Answer;
import com.example.dotestonline.repository.AccountQuizRepository;
import com.example.dotestonline.service.AccountQuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountQuizServiceImpl implements AccountQuizService {
    private ModelMapper modelMapper;
    private AccountQuizRepository accountQuizRepository;

    @Autowired
    public AccountQuizServiceImpl(ModelMapper modelMapper, AccountQuizRepository accountQuizRepository) {
        this.modelMapper = modelMapper;
        this.accountQuizRepository = accountQuizRepository;
    }

    @Override
    public List<AccountQuizDTO> getAll() {
        return null;
    }

    @Override
    public AccountQuizDTO getOne(Long id) {
        return null;
    }

    @Override
    public AccountQuizDTO createAccountQuiz(AccountQuizDTO accountQuizDTO) {
        return mapToDTO(accountQuizRepository.save(mapToEntity(accountQuizDTO)));
    }

    @Override
    public AccountQuizDTO findAccountQuizByQuizIdAndAccountId(Long quizId, Long accountId) {
        return mapToDTO(accountQuizRepository.findAccountQuizByQuizIdAndAccountId(quizId,accountId));
    }


    @Override
    public AccountQuizDTO updateAccountQuiz(AccountQuizDTO accountQuizDTO) {
        accountQuizRepository.save(mapToEntity(accountQuizDTO));
        return accountQuizDTO;
    }

    @Override
    public void deleteAccountQuiz(Long id) {

    }

    private AccountQuizDTO mapToDTO(AccountQuiz accountQuiz) {
        return modelMapper.map(accountQuiz, AccountQuizDTO.class);
    }



    private AccountQuiz mapToEntity(AccountQuizDTO accountQuizDTO) {
        return modelMapper.map(accountQuizDTO, AccountQuiz.class);
    }


}
