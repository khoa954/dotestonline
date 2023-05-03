package com.example.dotestonline.service;

import com.example.dotestonline.dto.AccountQuizDTO;

import java.util.List;

public interface AccountQuizService {
    List<AccountQuizDTO> getAll();
    AccountQuizDTO getOne(Long id);
    AccountQuizDTO createAccountQuiz(AccountQuizDTO accountQuizDTO);
    AccountQuizDTO findAccountQuizByQuizIdAndAccountId(Long quizId,Long accountId);
    AccountQuizDTO updateAccountQuiz(AccountQuizDTO accountQuizDTO);
    void deleteAccountQuiz(Long id);
}
