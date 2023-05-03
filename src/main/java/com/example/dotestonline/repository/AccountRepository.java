package com.example.dotestonline.repository;

import com.example.dotestonline.dto.AccountDTO;
import com.example.dotestonline.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.email=:email or a.name=:userName")
    Optional<Account> findByUserNameOrEmail(String userName, String email);
}
