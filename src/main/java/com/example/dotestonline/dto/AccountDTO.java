package com.example.dotestonline.dto;

import com.example.dotestonline.model.AccountQuiz;
import lombok.Data;

import java.util.Collection;

@Data
public class AccountDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String Role;
    private Collection<AccountQuizDTO> quizAccount;

}
