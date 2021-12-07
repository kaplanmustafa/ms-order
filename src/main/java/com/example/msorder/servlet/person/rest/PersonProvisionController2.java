package com.example.msorder.servlet.person.rest;

import com.example.msorder.models.Person;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person/provision")
public class PersonProvisionController2 {

    @PostMapping
    public String add(final Person person) {
        return "OK";
    }

    @DeleteMapping
    public String deactivate(@PathVariable("pid") final Long personId) {
        return "OK";
    }

    @PutMapping
    public String activate(@PathVariable("pid") final Long personId) {
        return "OK";
    }

    @PostMapping("/test")
    public String test(final Person person) {
        return "OK";
    }
}
