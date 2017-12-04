package com.shiny.backend.restdocs.controller;

import com.alibaba.fastjson.JSON;
import com.shiny.backend.common.entity.User;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @org.junit.Test
    public void register() throws Exception {
        User user=new User();
        user.setUsername("shiny");
        user.setPassword("123");
        String requestJson= JSON.toJSONString(user);
        mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("用户(用户注册接口)",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                //headerWithName("access_token").description("Basic auth credentials"),
                                //headerWithName("user_uuid").description("User Uuid Key")
                        ),requestParameters(
                                //parameterWithName("age").description("年龄")
                        ),responseFields(
                                //fieldWithPath("code").description("0.失败 1.成功").type(JsonFieldType.NUMBER),
                                //fieldWithPath("message").description("提示消息"),
                                fieldWithPath("id").description("用户UUid"),
                                fieldWithPath("username").description("姓名"),
                                fieldWithPath("password").description("用户密码"),
                                fieldWithPath("email").description("邮件"),
                                fieldWithPath("locked").description("是否锁定").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("lastPasswordResetDate").description("上次密码修改时间"),
                                fieldWithPath("roles[].id").description("角色id"),
                                fieldWithPath("roles[].roleName").description("角色名称"),
                                fieldWithPath("roleStrings").description("roleName字符串")
                        ))
                );
    }

    @After
    public void adocBuild() throws IOException {
        String appDir = System.getProperty("user.dir");
        String adocPath = appDir + "/target/asciidoc/apiList.adoc";
        StringBuilder content = new StringBuilder();
        File apidirs = new File(appDir + "/target/generated-snippets");
        for (File apidir : apidirs.listFiles()) {
            String apiName = apidir.getName();
            content.append("=== " + apiName + "\n\n");
            fileAppend(content, apidir + "/request-headers.adoc", "request-headers 类型说明");
            fileAppend(content, apidir + "/http-request.adoc", "http-request(空字段和布尔值不传)");
            fileAppend(content, apidir + "/curl-request.adoc", "CURL request(空字段和布尔值不传)");
            fileAppend(content, apidir + "/request-parameters.adoc", "request-parameters类型说明");
            fileAppend(content, apidir + "/request-body.adoc", "request-body类型说明");
            fileAppend(content, apidir + "/response-fields.adoc", "response-fields 类型说明");
        }
        File file = new File(adocPath);
        FileUtils.writeStringToFile(file, content.toString(), "utf-8");
    }

    private void fileAppend(StringBuilder stringBuilder, String path, String title) {
        File file = new File(path);
        if (file.exists()) {
            stringBuilder.append("==== " + title + " \n\n");
            stringBuilder.append("include::" + file + "[]" + "\n\n");
        }
    }

}