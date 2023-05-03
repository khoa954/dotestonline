package com.example.dotestonline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "question_text",nullable = false)
    private String questionText;

    @Basic
    @Column(name = "question_type",nullable = false)
    private String questionType;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<Answer> answers;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<QuizQuestion> quizQuestions;

}
