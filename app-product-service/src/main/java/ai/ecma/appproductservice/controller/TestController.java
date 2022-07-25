package ai.ecma.appproductservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config/test")
public class TestController {
//    @Value("${b5}")
//    private String key;
    @Value("${b9}")
    private String kalit;


    @GetMapping
    public String test(){
        return kalit;
    }
}
