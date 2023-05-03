package com.example.dotestonline.service;

import com.example.dotestonline.dto.QuizDTO;

import java.util.List;

public interface QuizService {
    List<QuizDTO> getAllQuiz();
    QuizDTO getOneQuiz(Long id);
    QuizDTO createQuiz(QuizDTO quizDTO);
    QuizDTO updateQuiz(QuizDTO quizDTO);
    void deleteQuiz(Long id);

}
