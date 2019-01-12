package edu.nju.stories.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("注册表单")
public class RegisterForm {

    @ApiModelProperty("注册用邮箱")
    private String email;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("注册验证码，暂时不用")
    private String registerCode;

}
