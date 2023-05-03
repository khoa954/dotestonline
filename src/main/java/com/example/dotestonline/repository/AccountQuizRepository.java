package com.example.dotestonline.repository;

import com.example.dotestonline.model.AccountQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountQuizRepository extends JpaRepository<AccountQuiz,Long> {
    @Query("select a from AccountQuiz a where a.account.id=:accountId and a.quiz.id=:quizId")
    public AccountQuiz findAccountQuizByQuizIdAndAccountId(Long quizId,Long accountId);
}
