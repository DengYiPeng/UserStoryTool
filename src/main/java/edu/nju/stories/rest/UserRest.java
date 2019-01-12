package edu.nju.stories.rest;

import edu.nju.stories.form.RegisterForm;
import edu.nju.stories.service.UserService;
import edu.nju.stories.vo.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRest {

    @Autowired
    UserService userService;

    @GetMapping(value = "/hello")
    public SimpleResponse test(@RequestParam("name") String name){
        return SimpleResponse.OK("Test1, " + name);
    }

    @PostMapping(value = "/register")
    public SimpleResponse register(@RequestBody RegisterForm form){
        String token = userService.register(form.getEmail(), form.getRegisterCode(), form.getPassword());
        return SimpleResponse.OK(token);
    }
}
