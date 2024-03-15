package com.exas.crm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.jsonwebtoken.lang.Arrays;
import lombok.Builder;
import lombok.Data;

@Document
@Data
@Builder
public class User {

	@Id
    private String id;

    private String name;

    private String email;

    private String imageUrl;

    private AuthProvider provider;

	/* private String providerId; */
}



