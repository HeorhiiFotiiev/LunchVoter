package ua.petproject.service.vote;


import ua.petproject.model.Vote;

public interface VoteService {

    Vote get(int id, int userId);

    Integer getSameRestaurantVotesAmount(int restaurantId);

    void update(Vote vote, int userId);

    Vote create(Vote vote, int userId);

    void delete(int id);
}
