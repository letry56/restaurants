package ru.letry.restaurants.web.restaurant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.letry.restaurants.dto.RestaurantDTO;
import ru.letry.restaurants.model.Dish;
import ru.letry.restaurants.model.Restaurant;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestAdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestAdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/admin/restaurants";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody RestaurantDTO restaurant) {
        Restaurant created = super.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody RestaurantDTO restaurant, @PathVariable int id) {
        super.update(restaurant, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDishWithLocation(@RequestBody Dish dish, @PathVariable int id) {
        Dish created = super.createDish(dish, id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + id + "/dishes" + "/{dishId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/{restaurantId}/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable int restaurantId, @PathVariable int dishId) {
        super.deleteDish(dishId, restaurantId);
    }

    @GetMapping("/{restaurantId}/dishes")
    public List<Dish> getDishesByDay(@PathVariable int restaurantId,
                                     @Nullable @RequestParam("date")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        //todo SoapUI, curl
        return super.getAllDishes(restaurantId, date);
    }

    @PutMapping(value = "/{restaurantId}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDish(@RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int dishId) {
        //todo SoapUI, curl
        dish.setId(dishId);
        super.updateDish(dish, restaurantId);
    }
}
