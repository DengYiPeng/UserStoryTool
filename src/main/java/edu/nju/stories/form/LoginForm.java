package edu.nju.stories.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录表单")
public class LoginForm {

    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户密码")
    private String password;
}
