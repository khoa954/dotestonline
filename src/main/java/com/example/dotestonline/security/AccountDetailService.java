package com.example.dotestonline.security;

import com.example.dotestonline.dto.AccountDTO;
import com.example.dotestonline.model.Account;
import com.example.dotestonline.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Service
public class AccountDetailService implements UserDetailsService {
    private AccountRepository accountRepository;

    public AccountDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Account account=accountRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User name or email not found: "+usernameOrEmail));


        Set<GrantedAuthority> authorities= Collections.singleton(new SimpleGrantedAuthority(account.getRole()));


        return new org.springframework.security.core.userdetails.User(account.getName(),account.getPassword(),authorities);
    }
}
