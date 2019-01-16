package edu.nju.stories.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class LoginResult {
    private String token;
    private String userId;

    public LoginResult(){

    }

    public LoginResult(String token, String userId){
        this.token = token;
        this.userId = userId;
    }
}
