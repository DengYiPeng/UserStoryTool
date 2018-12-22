package edu.nju.stories.rest;

import edu.nju.stories.vo.SimpleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRest {

    @GetMapping(value = "/hello")
    public SimpleResponse test(@RequestParam("name") String name){
        return SimpleResponse.OK("Test, " + name);
    }
}
