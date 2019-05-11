package ua.petproject.service.vote;


import ua.petproject.model.Vote;

import java.util.List;

public interface VoteService {

    Vote get(int id);

    List<Vote> getAll();

    void update(Vote vote);

    Vote create(Vote vote);
}
