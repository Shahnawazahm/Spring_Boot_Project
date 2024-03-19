package com.shahprojects.journal.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
@GetMapping("/check-kr")
public String check(){
    return "OK, All set";
}
}
