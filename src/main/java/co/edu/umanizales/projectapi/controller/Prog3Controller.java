package co.edu.umanizales.projectapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prog3")

public class Prog3Controller {
    @GetMapping("/hello")
    public String getHello()
    {
        return "Hello Campeones y campeonas";
    }
}
