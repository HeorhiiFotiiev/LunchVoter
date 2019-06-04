package ua.petproject.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.petproject.AuthorizedUser;
import ua.petproject.model.User;
import ua.petproject.repository.user.UserRepository;

import java.util.List;
import static ua.petproject.util.UserUtil.prepareToSave;
import static ua.petproject.util.ValidationUtil.checkNotFoundWithId;

@Service("UserService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository repository,PasswordEncoder passwordEncoder){
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
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
        checkNotFoundWithId(repository.save(prepareToSave(user,passwordEncoder)), user.getId());
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user,passwordEncoder));
    }

    @Override
    public List<User> getAllVotedForRestaurant(int restaurantId) {
        return repository.getAllVotedForRestaurant(restaurantId);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public void enable(int id, boolean enabled) {
        repository.enable(id,enabled);
    }


}
