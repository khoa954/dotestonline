package com.example.dotestonline.repository;

import com.example.dotestonline.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select a from Question a where a.questionType=:subject ORDER BY RAND(10)")
    List<Question> getQuestionWithShortDuration(String subject);
    @Query("select a from Question a where a.questionType=:subject ORDER BY RAND(20)")
    List<Question> getQuestionWithMediumDuration(String subject);
    @Query("select a from Question a where a.questionType=:subject ORDER BY RAND(30)")
    List<Question> getQuestionWithLongDuration(String subject);
}
