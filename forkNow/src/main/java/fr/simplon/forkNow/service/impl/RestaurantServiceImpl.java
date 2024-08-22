package fr.simplon.forknow.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.simplon.forknow.dto.RegisterRestaurantDto;
import fr.simplon.forknow.dto.RestaurantDto;
import fr.simplon.forknow.model.Restaurant;
import fr.simplon.forknow.repository.RestaurantRepository;
import fr.simplon.forknow.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;


    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void saveRestaurant(RestaurantDto restaurant) {
       Restaurant restaurantEntity = Restaurant.builder()
        .name(restaurant.getName())
        .build();
    restaurantRepository.save(restaurantEntity);
    }

    @Override
    public void saveRestaurant(RegisterRestaurantDto restaurant) {
        Restaurant restaurantEntity = Restaurant.builder()
                .name(restaurant.getName())
                .build();
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        return  restaurantRepository.findByName(name);
    }
    
}
