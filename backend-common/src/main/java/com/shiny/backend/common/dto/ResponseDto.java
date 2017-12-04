package com.shiny.backend.common.dto;

import lombok.Data;

/**
 * @author shiny
 **/
@Data
public class ResponseDto<T> {

    private String code;

    private String msg;

    private String url;

    private T data;
}
