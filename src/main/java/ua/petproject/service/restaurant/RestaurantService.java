package ua.petproject.service.restaurant;


import ua.petproject.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    void delete(int id);

    List<Restaurant> getAll();

    void update(Restaurant restaurant);

    Restaurant create(Restaurant restaurant);
}
