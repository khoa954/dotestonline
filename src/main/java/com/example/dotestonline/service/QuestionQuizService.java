package com.example.dotestonline.service;

import com.example.dotestonline.dto.QuizQuestionDTO;

import java.util.List;

public interface QuestionQuizService {
    List<QuizQuestionDTO> getAllQuizQuestion();
    QuizQuestionDTO getOneQuizQuestion(Long id);
    QuizQuestionDTO createQuizQuestion(QuizQuestionDTO quizQuestionDTO);
    List<QuizQuestionDTO> createMultipleQuizQuestion(List<QuizQuestionDTO> quizQuestionDTO);
    QuizQuestionDTO updateQuizQuestion(QuizQuestionDTO quizQuestionDTO);
    void deleteQuizQuestion(Long id);
}
