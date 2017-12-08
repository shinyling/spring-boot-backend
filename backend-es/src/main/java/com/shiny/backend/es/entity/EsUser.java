package com.shiny.backend.es.entity;

import com.shiny.backend.common.base.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author DELL
 */
@Document(indexName="userindex",type = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsUser extends BaseDomain {

    private String id;

    private String username;

    private String password;

    private String email;
}
