package ua.petproject.service.dish;


import ua.petproject.model.Dish;

import java.util.List;

public interface DishService {

    Dish get(int id);

    void delete(int id);

    List<Dish> getAll();

    void update(Dish dish);

    Dish create(Dish dish);

}
