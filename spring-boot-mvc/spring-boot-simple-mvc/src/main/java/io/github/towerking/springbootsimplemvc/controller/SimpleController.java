package io.github.towerking.springbootsimplemvc.controller;

import io.github.towerking.springbootsimplemvc.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("simple")
@Slf4j
public class SimpleController {

    @GetMapping("hello")
    public String hello(String name) {
        return "hello: " + name;
    }

    @GetMapping("collection")
    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "TowerKing");
        map.put("createTime", new Date());
        return map;
    }

    @GetMapping("exception")
    public void returnHttpCode() {
        throw new MyException();
    }

}
