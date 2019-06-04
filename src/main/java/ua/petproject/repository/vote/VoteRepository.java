package ua.petproject.repository.vote;


import ua.petproject.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId);

    boolean delete(int id);

    Vote get(int id, int userId);

    Integer getSameRestaurantVotesAmount(int restaurantId);

    List<Vote> getAll();
}
