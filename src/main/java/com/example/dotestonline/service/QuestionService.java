package com.example.dotestonline.service;

import com.example.dotestonline.dto.QuestionDTO;
import com.example.dotestonline.model.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> getAllQuestion();
    List<QuestionDTO> getQuestionWithShortDuration(String subject);
    List<QuestionDTO> getMultipleQuestionWithMultipleId(List<Long> id);

    List<QuestionDTO> getQuestionWithMediumDuration(String subject);
    List<QuestionDTO> getQuestionWithLongDuration(String subject);
    QuestionDTO getOneQuestion(Long id);
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    List<QuestionDTO> createMultipleQuestion(List<QuestionDTO> questionDTOS);
    QuestionDTO updateQuestion(QuestionDTO questionDTO);
    void deleteQuestion(Long id);
}
