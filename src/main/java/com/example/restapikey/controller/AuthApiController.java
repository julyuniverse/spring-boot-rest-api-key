package com.example.restapikey.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lee Taesung
 * @since 2022/10/11
 */

@RestController
@RequestMapping("/api")
public class AuthApiController {
    @GetMapping("check")
    public ResponseEntity<Object> check() {
        String str = "hello";

        return ResponseEntity.status(HttpStatus.OK).body(str);
    }
}
