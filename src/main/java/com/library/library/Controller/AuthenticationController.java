package com.library.library.Controller;


import com.library.library.Models.AuthRequest;
import com.library.library.Models.AuthResponse;
import com.library.library.Models.SignUpRequest;
import com.library.library.Models.SignUpResponse;
import com.library.library.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register-lib")
    public ResponseEntity<String> registerLibrarian(@RequestBody SignUpRequest user){
        SignUpResponse response = this.authenticationService.registerLibrarian(user);
        if(response.isDone()){
            return new ResponseEntity<>(response.getMsg(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response.getMsg(), HttpStatus.CONFLICT);
    }

    @PostMapping("/login-lib")
    public ResponseEntity<String> loginLibrarian(@RequestBody AuthRequest credentials){
        System.out.println("A7a");
        AuthResponse response = this.authenticationService.loginLibrarian(credentials);
        if(response.getDone()){
            return new ResponseEntity<>(response.getToken(),HttpStatus.OK);
        }
        return new ResponseEntity<>(" Invalid User Credentials ",HttpStatus.UNAUTHORIZED);

    }
}
