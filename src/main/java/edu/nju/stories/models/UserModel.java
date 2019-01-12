package edu.nju.stories.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "project")
public class UserModel {

    @Id
    private String _id;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String registerCode;
    private String token;

    @CreatedDate
    private Date createTime = new Date(System.currentTimeMillis());
    @LastModifiedDate
    private Date modifyTime = new Date(System.currentTimeMillis());

    public static final String TOKEN = "token";
}
