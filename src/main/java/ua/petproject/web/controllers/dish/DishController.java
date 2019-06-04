package ua.petproject.web.controllers.dish;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.petproject.model.Dish;
import ua.petproject.service.dish.DishService;

import java.net.URI;
import java.util.List;

import static ua.petproject.util.ValidationUtil.assureIdConsistent;
import static ua.petproject.util.ValidationUtil.checkNew;


@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController{

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/dishes";

    @Autowired
    private DishService service;

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish {}",id);
        return service.get(id);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("deleting dish {}",id);
        service.delete(id);
    }

    @GetMapping
    public List<Dish> getAll() {
        log.info("getSameRestaurantVotesAmount");
        return service.getAll();
    }

    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish) {
        log.info("update {} with id={}", dish,dish.getId());
        assureIdConsistent(dish, dish.getId());
        service.update(dish);
    }

    @PostMapping(value = "/admin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        log.info("create {}", dish);
        checkNew(dish);
        Dish created = service.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
