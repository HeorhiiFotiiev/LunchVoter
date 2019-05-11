package ua.petproject.service.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.petproject.model.Restaurant;
import ua.petproject.repository.restaurant.RestaurantRepository;

import java.util.List;

import static ua.petproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository){
        this.repository=repository;
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant,"restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant,"restaurant must not be null");
        return repository.save(restaurant);
    }
}
