package com.example.dotestonline.service.impl;

import com.example.dotestonline.dto.AccountQuizDTO;
import com.example.dotestonline.dto.QuizDTO;
import com.example.dotestonline.model.AccountQuiz;
import com.example.dotestonline.model.Quiz;
import com.example.dotestonline.repository.QuizRepository;
import com.example.dotestonline.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizServiceImpl implements QuizService {
    private ModelMapper modelMapper;
    private QuizRepository quizRepository;
@Autowired
    public QuizServiceImpl(ModelMapper modelMapper, QuizRepository quizRepository) {
        this.modelMapper = modelMapper;
        this.quizRepository = quizRepository;
    }

    @Override
    public List<QuizDTO> getAllQuiz() {
        return null;
    }

    @Override
    public QuizDTO getOneQuiz(Long id) {
        return null;
    }

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {

        Quiz quizDTO1=quizRepository.save(mapToEntity(quizDTO));
        return mapToDTO(quizDTO1);
    }

    @Override
    public QuizDTO updateQuiz(QuizDTO quizDTO) {
        return null;
    }

    @Override
    public void deleteQuiz(Long id) {

    }

    private QuizDTO mapToDTO(Quiz quiz){
        return modelMapper.map(quiz,QuizDTO.class);
    }
    private Quiz mapToEntity(QuizDTO quizDTO){
        return modelMapper.map(quizDTO,Quiz.class);
    }
}
