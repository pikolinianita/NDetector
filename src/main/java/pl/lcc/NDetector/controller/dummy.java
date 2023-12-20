package pl.lcc.NDetector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dummy {
    @GetMapping
    String dummy(){
        return "welcome";
    }

}
