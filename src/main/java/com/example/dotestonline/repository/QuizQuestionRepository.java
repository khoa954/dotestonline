package com.example.dotestonline.repository;

import com.example.dotestonline.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long> {
}
