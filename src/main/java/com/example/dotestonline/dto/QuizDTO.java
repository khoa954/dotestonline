package com.example.dotestonline.dto;

import com.example.dotestonline.model.QuizQuestion;
import lombok.Data;

import java.util.Collection;

@Data
public class QuizDTO {
    private Long id;
    private String name;
    private String duration;
    private String subject;
    private Collection<QuizQuestionDTO> quizQuestions;
    private Collection<AccountQuizDTO> quizAccount;

}
