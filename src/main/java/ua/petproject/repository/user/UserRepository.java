package ua.petproject.repository.user;


import ua.petproject.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(Integer id);

    User get(Integer id);

    List<User> getAll();

    List<User> getAllVotedForRestaurant(Integer restaurantId);
}
