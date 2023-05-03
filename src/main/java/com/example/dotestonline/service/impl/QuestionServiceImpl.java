package com.example.dotestonline.service.impl;

import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.dto.QuestionDTO;
import com.example.dotestonline.exception.ResourceNotFoundException;
import com.example.dotestonline.model.Answer;
import com.example.dotestonline.model.Question;
import com.example.dotestonline.repository.AnswerRepository;
import com.example.dotestonline.repository.QuestionRepository;
import com.example.dotestonline.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private ModelMapper modelMapper;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(ModelMapper modelMapper, QuestionRepository questionRepository) {
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDTO> getAllQuestion() {
        return null;
    }

    @Override
    public List<QuestionDTO> getQuestionWithShortDuration(String subject) {
        return questionRepository.getQuestionWithShortDuration(subject).stream().map(question -> mapToDTO(question)).toList();
    }

    @Override
    public List<QuestionDTO> getMultipleQuestionWithMultipleId(List<Long> id) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Long item : id
        ) {
            QuestionDTO questionDTO=mapToDTO(questionRepository.findById(item).orElseThrow(()->new ResourceNotFoundException("Question","id",String.valueOf(item))));
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    @Override
    public List<QuestionDTO> getQuestionWithMediumDuration(String subject) {
        return questionRepository.getQuestionWithMediumDuration(subject).stream().map(question -> mapToDTO(question)).toList();
    }

    @Override
    public List<QuestionDTO> getQuestionWithLongDuration(String subject) {
        return questionRepository.getQuestionWithLongDuration(subject).stream().map(question -> mapToDTO(question)).toList();
    }

    @Override
    public QuestionDTO getOneQuestion(Long id) {
        return null;
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        return null;
    }

    @Override
    public List<QuestionDTO> createMultipleQuestion(List<QuestionDTO> questionDTOS) {
        List<Question> questions = questionRepository.saveAll(mapMultipleToEntity(questionDTOS));
        return mapMultipleToDTO(questions);
    }

    @Override
    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        return null;
    }

    @Override
    public void deleteQuestion(Long id) {

    }

    private QuestionDTO mapToDTO(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }

    private Question mapToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }

    private List<Question> mapMultipleToEntity(List<QuestionDTO> questionDTOs) {
        List<Question> entitiesList = new ArrayList<>();
        for (QuestionDTO item : questionDTOs) {
            entitiesList.add(mapToEntity(item));
        }
        return entitiesList;
    }

    private List<QuestionDTO> mapMultipleToDTO(List<Question> questions) {
        List<QuestionDTO> dtoList = new ArrayList<>();
        for (Question item : questions) {
            dtoList.add(mapToDTO(item));
        }
        return dtoList;
    }
}
