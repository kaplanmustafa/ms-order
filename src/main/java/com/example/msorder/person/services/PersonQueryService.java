package com.example.msorder.person.services;

import com.example.msorder.data.PersonDAO;
import com.example.msorder.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonQueryService {

    @Autowired
    private PersonDAO personDAO;

    public Person getPerson(final String number) {
        return this.personDAO.getPerson(number);
    }

    public List<Person> getAll() {
        return this.personDAO.getAll();
    }
}
