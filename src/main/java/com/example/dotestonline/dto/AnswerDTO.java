package com.example.dotestonline.dto;

import com.example.dotestonline.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private Long id;
    private String answerContent;
    @JsonIgnore
    private boolean correct;
    private QuestionDTO question;

    public AnswerDTO(String answerContent, boolean correct, QuestionDTO question) {
        this.answerContent = answerContent;
        this.correct = correct;
        this.question = question;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
