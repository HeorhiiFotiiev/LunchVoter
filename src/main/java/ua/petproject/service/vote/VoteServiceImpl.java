package ua.petproject.service.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.petproject.model.Vote;
import ua.petproject.repository.vote.VoteRepository;

import java.util.List;

import static ua.petproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository){
        this.repository=repository;
    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "user must not be null");
        return repository.save(vote);
    }
}
