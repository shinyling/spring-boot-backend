package com.shiny.backend.exception.core;

import com.shiny.backend.common.dto.ResponseDto;
import com.shiny.backend.common.dto.ReturnCode;
import com.shiny.backend.exception.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shiny
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseDto<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ResponseDto<String> r = new ResponseDto<>();
        r.setMsg(e.getMessage());
        r.setCode(ReturnCode.FAIL.getCode());
        r.setData(ReturnCode.FAIL.getMsg());
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
