package com.example.msorder.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class MyRest {

    @GetMapping("/hello")
    public String hello(@RequestParam("name") final String name) {
        return "Hello GET " + name;
    }

    @PostMapping("/hello")
    public String helloPost(@RequestParam("name") final String name) {
        return "Hello POST " + name;
    }

    @PutMapping("/hello")
    public String helloPut(@RequestParam("name") final String name) {
        return "Hello PUT " + name;
    }

    @PatchMapping("/hello")
    public String helloPatcht(@RequestParam("name") final String name) {
        return "Hello PATCH " + name;
    }

    @DeleteMapping("/hello")
    public String helloDelete(@RequestParam("name") final String name) {
        return "Hello DELETE " + name;
    }
}
