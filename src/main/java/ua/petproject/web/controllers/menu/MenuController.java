package ua.petproject.web.controllers.menu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.petproject.model.Menu;
import ua.petproject.service.menu.MenuService;

import java.util.List;

import static ua.petproject.util.ValidationUtil.assureIdConsistent;
import static ua.petproject.util.ValidationUtil.checkNew;


@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/menus";

    @Autowired
    private MenuService service;

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        log.info("get menu {}",id);
        return service.get(id);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("deleting menu {}",id);
        service.delete(id);
    }

    @GetMapping
    public List<Menu> getAll() {
        log.info("getSameRestaurantVotesAmount");
        return service.getAll();
    }

    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu) {
        log.info("update {} with id={}", menu,menu.getId());
        assureIdConsistent(menu, menu.getId());
        service.update(menu);
    }

    @PostMapping(value = "/admin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu create(@RequestBody Menu menu) {
        log.info("create {}", menu);
        checkNew(menu);
        return service.create(menu);
    }
}
