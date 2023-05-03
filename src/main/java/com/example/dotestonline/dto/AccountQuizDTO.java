package com.example.dotestonline.dto;

import com.example.dotestonline.model.Account;
import com.example.dotestonline.model.Quiz;
import lombok.Data;

@Data
public class AccountQuizDTO {
    private Long id;
    private AccountDTO account;
    private QuizDTO quiz;
    private String score;

}
