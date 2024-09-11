package com.example.demo.services;

import com.example.demo.models.UserAccount;
import com.example.demo.repositories.UserAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserAccountsService {

    private final UserAccountsRepository userAccountsRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountsService(UserAccountsRepository userAccountsRepository, PasswordEncoder passwordEncoder) {
        this.userAccountsRepository = userAccountsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerNewUserAccount(UserAccount userAccount){
        userAccount.setRole("ROLE_USER");
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccountsRepository.save(userAccount);
    }

}
