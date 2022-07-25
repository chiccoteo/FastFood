package ai.ecma.appbranchservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config/test")
public class TestController {
//    @Value("${b5}")
//    private String key;

    @GetMapping
    public String test(){
        return "key";
    }
}
