package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class Controller {
    @RequestMapping("/welcome")
    public String hello() { return "hello world";}

}
