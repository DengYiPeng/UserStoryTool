package edu.nju.stories.rest;

import edu.nju.stories.form.LoginForm;
import edu.nju.stories.form.RegisterForm;
import edu.nju.stories.service.UserService;
import edu.nju.stories.vo.LoginResult;
import edu.nju.stories.vo.SimpleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRest {

    @Autowired
    UserService userService;


    @ApiOperation(value = "注册账号", response = LoginResult.class, notes = "若注册成功，则自动登录，返回值是token")
    @PostMapping(value = "/register")
    public SimpleResponse register(@RequestBody RegisterForm form){
        LoginResult result = userService.register(form.getUsername(), form.getEmail(), form.getRegisterCode(), form.getPassword());
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "登录", response = LoginResult.class, notes = "返回值是token")
    @PostMapping(value = "/login")
    public SimpleResponse login(@RequestBody LoginForm form){
        LoginResult result = userService.login(form.getEmail(), form.getPassword());
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "登出", response = Boolean.class, notes = "")
    @PostMapping(value = "/logout")
    public SimpleResponse logout(@RequestHeader("token") String token){
        boolean result = userService.logout(token);
        return SimpleResponse.OK(result);
    }
}
