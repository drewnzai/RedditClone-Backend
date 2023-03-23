package com.drew.Reddit.api;

import com.drew.Reddit.dto.AuthenticationResponse;
import com.drew.Reddit.dto.LoginRequest;
import com.drew.Reddit.dto.RefreshTokenRequest;
import com.drew.Reddit.dto.RegisterRequest;
import com.drew.Reddit.services.AuthService;
import com.drew.Reddit.services.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

/*
* This API endpoint handles all authentication: signup, login, and logout.
* The signup function takes a registration request and returns a 200 value once
*   completed and sends an email to the given email for verification.
*
* It handles the verification once the email link is entered.
*
* Login takes a request, checks whether the user exists, and returns a JWT and
*   Refresh Token to refresh the JWT once it has expired.
*
* Logout invalidates and deletes the generated JWT and Refresh token.
* */

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
