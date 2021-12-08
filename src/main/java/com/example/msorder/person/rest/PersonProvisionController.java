package com.example.msorder.person.rest;

import com.example.msorder.models.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/person/provision")
@Validated
public class PersonProvisionController {

    @PostMapping("/add")
    public String add(@Validated @RequestBody final Person person) {
        if ("unknown".equals(person.getName())) {
            throw new IllegalArgumentException("Unknown olamaz!");
        }
        return "OK";
    }

    @GetMapping("/deactivate/{pid}")
    public String deactivate(@NotNull @PathVariable("pid") final Long personId) {
        return "OK";
    }

    @GetMapping("/activate/{pid}")
    public String activate(@NotNull @PathVariable("pid") final Long personId) {
        return "OK";
    }
}
