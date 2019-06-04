package ua.petproject.web.controllers.restaurant;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.petproject.model.Restaurant;
import ua.petproject.service.restaurant.RestaurantService;

import java.util.List;

import static ua.petproject.util.ValidationUtil.assureIdConsistent;
import static ua.petproject.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant {}",id);
        return service.get(id);
    }


    @DeleteMapping("/admin/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("deleting restaurant {}",id);
        service.delete(id);
    }


    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getSameRestaurantVotesAmount");
        return service.getAll();
    }


    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        log.info("update {} with id={}", restaurant,restaurant.getId());
        assureIdConsistent(restaurant, restaurant.getId());
        service.update(restaurant);
    }

    @PostMapping(value = "/admin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }
}
