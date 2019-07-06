package io.github.towerking.springbootcomplexmvc.controller;


import io.github.towerking.springbootcomplexmvc.model.Example;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("example")
@Slf4j
public class ExampleController {

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Example addExampleWithoutBindingResult(@Valid Example example) {
        return example;
    }

    @PostMapping(path = "/binding", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Example addExampleWithBindingResult(@Valid Example example, BindingResult result) {
        if (result.hasErrors()) {
            log.error("Binding errors: {}", result);
            return null;
        }
        return example;
    }

    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<Example> batchAddExamples(@RequestParam("file") MultipartFile file) {
        List<Example> examples = new ArrayList<>();
        if (file.isEmpty())
            return examples;

        BufferedReader reader = null;
        try {
            reader =new BufferedReader(new InputStreamReader(file.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                String[] arr = StringUtils.split(str, ",");
                if (arr != null && arr.length == 2) {
                    examples.add(new Example(Integer.parseInt(arr[0]), arr[1], new Date()));
                }
            }
        } catch (IOException e) {
            log.error("exception", e);
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return examples;
    }
}
