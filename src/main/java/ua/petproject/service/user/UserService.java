package ua.petproject.service.user;


import ua.petproject.model.User;

import java.util.List;

public interface UserService {

    User get(int id);

    void delete(int id);

    List<User> getAll();

    void update(User user);

    User create(User user);

    List<User> getAllVotedForRestaurant(int restaurantId);
}
