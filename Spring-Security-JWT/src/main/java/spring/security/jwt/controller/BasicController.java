package spring.security.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/response/{name}")
    public String getResponse(@PathVariable("name") String name){
        return "Hi! "+name;
    }
}
