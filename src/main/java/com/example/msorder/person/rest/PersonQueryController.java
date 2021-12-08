package com.example.msorder.person.rest;

import com.example.msorder.mappers.PersonMapper;
import com.example.msorder.person.rest.models.PersonRestObj;
import com.example.msorder.person.services.PersonQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person/query")
public class PersonQueryController {

    @Autowired
    private PersonQueryService pqs;

    @GetMapping("/get/one/{number}")
    public PersonRestObj getOne(@PathVariable("number") final String number) {
        return PersonMapper.toPersonRest(this.pqs.getPerson(number));
    }

    @GetMapping("/get/all")
    public List<PersonRestObj> getAll() {
        return PersonMapper.toListPersonRest(this.pqs.getAll());
    }
}
