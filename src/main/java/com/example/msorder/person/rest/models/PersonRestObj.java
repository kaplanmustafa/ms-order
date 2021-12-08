package com.example.msorder.person.rest.models;

import com.example.msorder.validation.StartWith;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PersonRestObj {

    @NotEmpty
    @Size(min = 2, max = 20)
    @StartWith("n:")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 20)
    @StartWith("s:")
    private String surname;

    @Past
    private LocalDate birthdate;

    @Max(400)
    @Min(10)
    private int weight;

    @Max(400)
    @Min(50)
    private int height;

    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(final String numberParam) {
        this.number = numberParam;
    }
}
