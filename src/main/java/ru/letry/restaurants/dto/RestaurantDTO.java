package ru.letry.restaurants.dto;

import ru.letry.restaurants.model.Dish;

import java.util.Set;

public class RestaurantDTO {
    private final Integer id;

    private final String name;

    private final Set<Dish> dishes;

    private final int votes;

    public RestaurantDTO(Integer id, String name, Set<Dish> dishes, int votes) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                ", votes=" + votes +
                '}';
    }
}