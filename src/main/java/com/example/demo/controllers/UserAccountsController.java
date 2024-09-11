package com.example.demo.controllers;

import com.example.demo.DTO.UserAccountDTO;
import com.example.demo.models.UserAccount;
import com.example.demo.security.JWTUtil;
import com.example.demo.services.UserAccountsService;
import com.example.demo.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class UserAccountsController {

    private final UserAccountsService userAccountsService;

    private final JWTUtil jwtUtil;

    private final Mapper mapper;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserAccountsController(UserAccountsService userAccountsService, JWTUtil jwtUtil, Mapper mapper, AuthenticationManager authenticationManager) {
        this.userAccountsService = userAccountsService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> performRegistration(@RequestBody @Valid UserAccountDTO userAccountDTO){
        UserAccount userAccount = mapper.convertUserAccountDtoToUserAccount(userAccountDTO);

        userAccountsService.registerNewUserAccount(userAccount);

        return Map.of("jwt-token", jwtUtil.generateToken(userAccount.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> performLogin(@RequestBody @Valid UserAccountDTO userAccountDTO){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userAccountDTO.getUsername(),
                userAccountDTO.getPassword()
        );

        try{
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(Map.of("message", "Incorrect credentials"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Map.of("jwt-token", jwtUtil.generateToken(userAccountDTO.getUsername())), HttpStatus.OK);
    }
}
