package ua.petproject.repository.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.User;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private static final Sort SORT_BY_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public User save(User user) {
        if (!user.isNew() && get(user.getId()) == null) {
            return null;
        } else return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(Integer id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public User get(Integer id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_BY_NAME_EMAIL);
    }

    @Override
    public List<User> getAllVotedForRestaurant(Integer restaurantId) {
        return crudUserRepository.getAllVotedForRestaurant(restaurantId);
    }

    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }
}
