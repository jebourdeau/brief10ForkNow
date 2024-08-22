package fr.simplon.forknow.service;

import java.util.Optional;

import fr.simplon.forknow.dto.RegisterRestaurantDto;
import fr.simplon.forknow.dto.RestaurantDto;
import fr.simplon.forknow.model.Restaurant;

public interface  RestaurantService {
    void saveRestaurant(RestaurantDto restaurant);
    void saveRestaurant(RegisterRestaurantDto restaurantMapping);

    Optional<Restaurant>findByName(String name);
}
