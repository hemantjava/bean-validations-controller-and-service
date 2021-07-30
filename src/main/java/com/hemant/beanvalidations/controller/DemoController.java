package com.hemant.beanvalidations.controller;

import com.hemant.beanvalidations.bean.Demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DemoController {

    @PostMapping("/validate")
    public ResponseEntity<Object> getData(@RequestBody @Valid Demo demo){

        return new ResponseEntity<>(demo,HttpStatus.OK);
    }
}
