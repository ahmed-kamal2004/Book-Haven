package com.library.security.secure.controller;


import com.library.security.secure.dto.ResponseDto;
import com.library.security.secure.dto.UserDto;
import com.library.security.secure.services.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/auth/")
@RequiredArgsConstructor
public class UserController {

    private final UserModelService userModelService;

    @PostMapping("sign-up")
    public ResponseEntity<String> singUp(@RequestHeader HttpHeaders httpHeaders, @RequestBody  UserDto userDto){
//        String token = this.userModelService.signup(UserDto);
        System.out.println(httpHeaders.get("connection"));
        return ResponseEntity.ok().body(httpHeaders.get("connection").toString());
    }

}
