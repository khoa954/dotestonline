package com.example.dotestonline.dto;

import com.example.dotestonline.model.Question;
import com.example.dotestonline.model.Quiz;
import lombok.Data;

@Data
public class QuizQuestionDTO {
    private Long id;
    private QuizDTO quizQ;
    private QuestionDTO question;

}
