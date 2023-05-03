package com.example.dotestonline.dto;

import com.example.dotestonline.model.Answer;
import com.example.dotestonline.model.QuizQuestion;
import lombok.Data;

import java.util.Collection;

@Data
public class QuestionDTO {
    private Long id;
    private String questionText;
    private String questionType;
    private Collection<AnswerDTO> answers;
    private Collection<QuizQuestionDTO> quizQuestions;

}
