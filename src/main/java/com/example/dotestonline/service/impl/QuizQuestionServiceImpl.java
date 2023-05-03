package com.example.dotestonline.service.impl;

import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.dto.QuizQuestionDTO;
import com.example.dotestonline.model.Answer;
import com.example.dotestonline.model.QuizQuestion;
import com.example.dotestonline.repository.QuizQuestionRepository;
import com.example.dotestonline.service.QuestionQuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizQuestionServiceImpl implements QuestionQuizService {
    private ModelMapper modelMapper;
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    public QuizQuestionServiceImpl(ModelMapper modelMapper, QuizQuestionRepository quizQuestionRepository) {
        this.modelMapper = modelMapper;
        this.quizQuestionRepository = quizQuestionRepository;
    }

    @Override
    public List<QuizQuestionDTO> getAllQuizQuestion() {
        return null;
    }

    @Override
    public QuizQuestionDTO getOneQuizQuestion(Long id) {
        return null;
    }

    @Override
    public QuizQuestionDTO createQuizQuestion(QuizQuestionDTO quizQuestionDTO) {
        return null;
    }

    @Override
    public List<QuizQuestionDTO> createMultipleQuizQuestion(List<QuizQuestionDTO> quizQuestionDTO) {
        return mapToDTOMultiple(quizQuestionRepository.saveAll(mapToEntityMultiple(quizQuestionDTO)));
    }

    @Override
    public QuizQuestionDTO updateQuizQuestion(QuizQuestionDTO quizQuestionDTO) {
        return null;
    }

    @Override
    public void deleteQuizQuestion(Long id) {

    }

    private List<QuizQuestionDTO> mapToDTOMultiple(List<QuizQuestion> quizQuestion) {
        List<QuizQuestionDTO> quizQuestionDTOList = new ArrayList<>();
        for (QuizQuestion item : quizQuestion
        ) {
            QuizQuestionDTO questionDTO = mapToDTO(item);
            quizQuestionDTOList.add(questionDTO);
        }
        return quizQuestionDTOList;
    }

    private QuizQuestionDTO mapToDTO(QuizQuestion quizQuestion) {
        return modelMapper.map(quizQuestion, QuizQuestionDTO.class);
    }

    private List<QuizQuestion> mapToEntityMultiple(List<QuizQuestionDTO> quizQuestionDTO) {
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        for (QuizQuestionDTO item : quizQuestionDTO
        ) {
            QuizQuestion question=mapToEntity(item);
            quizQuestions.add(question);
        }
        return quizQuestions;
    }

    private QuizQuestion mapToEntity(QuizQuestionDTO quizQuestionDTO) {
        return modelMapper.map(quizQuestionDTO, QuizQuestion.class);
    }
}
