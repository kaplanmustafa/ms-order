package com.example.msorder.order.integrations.models;

import com.example.msorder.order.rest.models.Meal;

import java.util.List;

public class Menu {

    private String menuName;

    private List<Meal> meals;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
