package ua.petproject.web.controllers.vote;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.petproject.model.Vote;
import ua.petproject.service.vote.VoteService;

import java.util.List;

import static ua.petproject.util.SecurityUtil.authUserId;
import static ua.petproject.util.ValidationUtil.assureIdConsistent;
import static ua.petproject.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/votes";

    @Autowired
    private VoteService service;

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {}",id);
        return service.get(id,userId);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        log.info("delete vote {}",id);
        service.delete(id);
    }

    @GetMapping("/restaurantVotes/{restaurantId}")
    public Integer getSameRestaurantVotesAmount(@PathVariable int restaurantId) {
        log.info("getSameRestaurantVotesAmount");
        return service.getSameRestaurantVotesAmount(restaurantId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote) {
        int userId = authUserId();
        log.info("update {} with id={}", vote,vote.getId());
        assureIdConsistent(vote, vote.getId());
        service.update(vote,userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@RequestBody Vote vote) {
        int userId = authUserId();
        log.info("create {}", vote);
        checkNew(vote);
        return service.create(vote,userId);
    }
}
