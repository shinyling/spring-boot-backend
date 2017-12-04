package com.shiny.backend.exception.controller;

import com.shiny.backend.exception.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiny
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("exception")
    public String testException() throws Exception{
        throw new Exception("gg");
    }

    @RequestMapping("myException")
    public String testMyException() throws MyException {
        throw new MyException("你大爷");
    }
}
