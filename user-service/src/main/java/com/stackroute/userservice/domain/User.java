package com.stackroute.userservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User
{
    @Id
    private int userId;
    private String userName;
    private String userPassword;
}
