package ua.petproject.service.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.petproject.model.Vote;
import ua.petproject.repository.vote.VoteRepository;
import ua.petproject.util.VoteUtil;

import static ua.petproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository){
        this.repository=repository;
    }

    @Override
    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId),id);
    }

    @Override
    public Integer getSameRestaurantVotesAmount(int restaurantId) {
        return repository.getSameRestaurantVotesAmount(restaurantId);
    }

    @Override
    public void update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        VoteUtil.checkVotingTime(vote);
        checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    @Override
    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "user must not be null");
        return repository.save(vote, userId);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }
}
