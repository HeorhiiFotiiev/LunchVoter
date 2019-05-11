package ua.petproject.repository.vote;


import ua.petproject.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    List<Vote> getAll();
}
