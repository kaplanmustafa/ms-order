package com.example.msorder.servlet.person.rest;

import com.example.msorder.models.Person;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person/provision")
public class PersonProvisionController {

    @PostMapping("/add")
    public String add(@RequestBody final Person person) {
        return "OK";
    }

    @GetMapping("/deactivate/{pid}")
    public String deactivate(@PathVariable("pid") final Long personId) {
        return "OK";
    }

    @GetMapping("/activate/{pid}")
    public String activate(@PathVariable("pid") final Long personId) {
        return "OK";
    }
}
