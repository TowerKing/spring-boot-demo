package io.github.towerking.springbootcomplexmvc.controller;


import io.github.towerking.springbootcomplexmvc.model.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("example")
@Slf4j
public class ExampleController {

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Example addExampleWithOUtBindingResult(@Valid Example example) {
        return example;
    }
}
