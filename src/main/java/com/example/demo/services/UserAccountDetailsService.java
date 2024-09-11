package com.example.demo.services;

import com.example.demo.repositories.UserAccountsRepository;
import com.example.demo.security.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    private final UserAccountsRepository userAccountsRepository;

    @Autowired
    public UserAccountDetailsService(UserAccountsRepository userAccountsRepository) {
        this.userAccountsRepository = userAccountsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserAccountDetails(userAccountsRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with this username does not exist")));
    }
}
