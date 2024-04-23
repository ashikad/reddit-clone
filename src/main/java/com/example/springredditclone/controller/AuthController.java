package com.example.springredditclone.controller;

import com.example.springredditclone.dto.AuthenticationResponse;
import com.example.springredditclone.dto.LoginResponse;
import com.example.springredditclone.dto.LoginRequest;
import com.example.springredditclone.dto.RegisterRequest;
import com.example.springredditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest){
        authService.signUp(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);

    }

    @GetMapping("/accountVerification")
    public ResponseEntity<String> verifyAccount(String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
