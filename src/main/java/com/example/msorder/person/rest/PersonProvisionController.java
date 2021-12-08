package com.example.msorder.person.rest;

import com.example.msorder.mappers.PersonMapper;
import com.example.msorder.person.rest.models.PersonRestObj;
import com.example.msorder.person.services.PersonProvisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/person/provision")
@Validated
public class PersonProvisionController {

    @Autowired
    private PersonProvisionService pps;

    @PostMapping("/add")
    public String add(@Validated @RequestBody final PersonRestObj person) {
        if ("unknown".equals(person.getName())) {
            throw new IllegalArgumentException("Unknown olamaz!");
        }
        this.pps.add(PersonMapper.toPerson(person));
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
