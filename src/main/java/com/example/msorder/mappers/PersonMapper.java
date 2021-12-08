package com.example.msorder.mappers;

import com.example.msorder.models.Person;
import com.example.msorder.person.rest.models.PersonRestObj;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    public static Person toPerson(final PersonRestObj personParam) {
        Person personLoc = new Person();
        personLoc.setName(personParam.getName());
        personLoc.setSurname(personParam.getSurname());
        personLoc.setBirthdate(personParam.getBirthdate());
        personLoc.setHeight(personParam.getHeight());
        personLoc.setWeight(personParam.getWeight());
        personLoc.setNumber(personParam.getNumber());
        return personLoc;
    }

    public static PersonRestObj toPersonRest(final Person personParam) {
        PersonRestObj personLoc = new PersonRestObj();
        personLoc.setName(personParam.getName());
        personLoc.setSurname(personParam.getSurname());
        personLoc.setBirthdate(personParam.getBirthdate());
        personLoc.setHeight(personParam.getHeight());
        personLoc.setWeight(personParam.getWeight());
        personLoc.setNumber(personParam.getNumber());
        return personLoc;
    }

    public static List<PersonRestObj> toListPersonRest(final List<Person> allParam) {
        List<PersonRestObj> listLoc = new ArrayList<>();
        for (Person personLoc : allParam) {
            listLoc.add(toPersonRest(personLoc));
        }
        return listLoc;
    }
}
