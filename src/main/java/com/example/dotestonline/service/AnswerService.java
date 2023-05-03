package com.example.dotestonline.service;

import com.example.dotestonline.dto.AnswerDTO;
import com.example.dotestonline.model.Answer;

import java.util.List;

public interface AnswerService {
    List<AnswerDTO> getAllAnswer();
    AnswerDTO getOneAnswer(Long id);
    AnswerDTO createAnswer(AnswerDTO id);
    AnswerDTO updateAnswer(AnswerDTO id);
    void deleteAnswer(Long id);

}
