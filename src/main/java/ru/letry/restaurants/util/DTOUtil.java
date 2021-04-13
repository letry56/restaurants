package ru.letry.restaurants.util;

import org.springframework.util.Assert;
import ru.letry.restaurants.dto.RestaurantDTO;
import ru.letry.restaurants.dto.UserDTO;
import ru.letry.restaurants.model.Restaurant;
import ru.letry.restaurants.model.User;
import ru.letry.restaurants.model.Vote;

import java.util.*;

public class DTOUtil {
    public static List<RestaurantDTO> getRestaurantDTOs(Collection<Restaurant> restaurants, Map<Integer, Integer> results) {
        Assert.notNull(restaurants, "restaurants must not be null");
        Assert.notNull(results, "results must not be null");
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDTOs.add(createRestaurantDTO(restaurant, results.get(restaurant.id())));
        }
        return restaurantDTOs;
    }

    private static RestaurantDTO createRestaurantDTO(Restaurant restaurant, Integer votes) {
        Assert.notNull(restaurant, "restaurant must not be null");
        Assert.notNull(votes, "votes must not be null");
        return new RestaurantDTO(restaurant.id(), restaurant.getName(), new HashSet<>(restaurant.getDishes()), votes);
    }

    public static UserDTO getUserDTO(User user, Vote vote) {
        Assert.notNull(user, "user must not be null");
        return vote != null ? new UserDTO(user.id(), vote.getRestaurant().id(), vote.getRestaurant().getName()) :
                new UserDTO(user.id(), 0, "you did not vote today");
    }
}