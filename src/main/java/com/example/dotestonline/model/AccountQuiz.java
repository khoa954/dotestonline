package com.example.dotestonline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "score",nullable = true)
    private String score;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;


}
