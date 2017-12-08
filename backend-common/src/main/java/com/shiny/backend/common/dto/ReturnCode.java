package com.shiny.backend.common.dto;

/**
 * @author shiny
 **/
public enum ReturnCode {
    /**
     * 成功
     */
    SUCCESS("200","请求成功"),
    FAIL("500","请求失败");

    private String code;
    private String msg;

    private ReturnCode(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
