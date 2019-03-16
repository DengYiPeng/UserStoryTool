package edu.nju.stories.vo;

import edu.nju.stories.models.UserModel;
import lombok.Data;

@Data
public class UserVO {
    private String userId;
    private String username;
    private String email;
    private String mobile;

    public UserVO(){

    }

    public UserVO(UserModel userModel){
        this.userId = userModel.get_id();
        this.username = userModel.getUsername();
        this.email = userModel.getEmail();
        this.mobile = userModel.getMobile();
    }

}
