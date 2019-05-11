package ua.petproject.web.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.petproject.model.User;
import ua.petproject.service.user.UserService;

import java.util.List;

import static ua.petproject.util.ValidationUtil.assureIdConsistent;
import static ua.petproject.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/user";

    @Autowired
    private UserService service;

    @GetMapping("/allVotedForRestaurant/{id}")
    public List<User> getAllVotedForRestaurant(@PathVariable int id) {
        return service.getAllVotedForRestaurant(id);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get user {}",id);
        return service.get(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("deleting user {}",id);
        service.delete(id);
    }


    @GetMapping
    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        log.info("update {} with id={}", user,user.getId());
        assureIdConsistent(user, user.getId());
        service.update(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

}
