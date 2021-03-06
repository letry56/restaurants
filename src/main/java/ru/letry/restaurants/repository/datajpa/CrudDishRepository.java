package ru.letry.restaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.letry.restaurants.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Dish d SET d.enabled = FALSE WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.enabled = TRUE ORDER BY d.name ASC")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.enabled = TRUE AND d.date=:date ORDER BY d.name ASC")
    List<Dish> getAll(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);
}