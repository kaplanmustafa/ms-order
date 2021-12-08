package com.example.msorder.person.services;

import com.example.msorder.data.PersonDAO;
import com.example.msorder.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonProvisionService {

    @Autowired
    private PersonDAO personDAO;

    public String add(final Person personParam) {
        this.personDAO.add(personParam);
        return "OK";
    }
}
