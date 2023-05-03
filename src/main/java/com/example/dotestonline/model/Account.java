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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_name",nullable = false)
    private String name;

    @Basic
    @Column(name = "password",nullable = false)
    private String password;

    @Basic
    @Column(name = "email",nullable = false)
    private String email;

    @Basic
    @Column(name = "role",nullable = false)
    private String Role;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private Collection<AccountQuiz> quizAccount;
}
