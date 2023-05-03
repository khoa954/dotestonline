package com.example.dotestonline.service.impl;

import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.model.Answer;
import com.example.dotestonline.repository.AnswerRepository;
import com.example.dotestonline.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {
    private ModelMapper modelMapper;
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(ModelMapper modelMapper, AnswerRepository answerRepository) {
        this.modelMapper = modelMapper;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<AnswerDTO> getAllAnswer() {
        return null;
    }

    @Override
    public AnswerDTO getOneAnswer(Long id) {
        return null;
    }

    @Override
    public AnswerDTO createAnswer(AnswerDTO id) {
        return null;
    }

    @Override
    public AnswerDTO updateAnswer(AnswerDTO id) {
        return null;
    }

    @Override
    public void deleteAnswer(Long id) {

    }

    private AnswerDTO mapToDTO(Answer answer){
        return modelMapper.map(answer,AnswerDTO.class);
    }
    private Answer mapToEntity(AnswerDTO answerDTO){
        return modelMapper.map(answerDTO,Answer.class);
    }
}
