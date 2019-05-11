package ua.petproject.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.petproject.model.User;
import ua.petproject.repository.user.UserRepository;

import java.util.List;

import static ua.petproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository=repository;
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public List<User> getAllVotedForRestaurant(int restaurantId) {
        return repository.getAllVotedForRestaurant(restaurantId);
    }


}
