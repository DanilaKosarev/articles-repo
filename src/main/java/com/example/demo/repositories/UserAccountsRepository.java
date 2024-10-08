package com.example.demo.repositories;

import com.example.demo.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccount, Integer> {

    Optional<UserAccount> findByUsername(String name);

}
