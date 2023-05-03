package com.example.dotestonline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name",nullable = false)
    private String name;

    @Basic
    @Column(name = "duration",nullable = false)
    private String duration;

    @Basic
    @Column(name = "subject",nullable = false)
    private String subject;


    @OneToMany(mappedBy = "quizQ",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<QuizQuestion> quizQuestions;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<AccountQuiz> quizAccount;
}
