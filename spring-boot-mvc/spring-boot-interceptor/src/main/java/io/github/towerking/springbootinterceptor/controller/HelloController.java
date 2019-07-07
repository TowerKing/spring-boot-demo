package io.github.towerking.springbootinterceptor.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("name")
    public String getName(@RequestParam  String name) throws InterruptedException {
        Thread.sleep(1000);
        return name;
    }
}
