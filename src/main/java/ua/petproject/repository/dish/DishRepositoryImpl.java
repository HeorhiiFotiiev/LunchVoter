package ua.petproject.repository.dish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Dish;

import java.util.List;

import static ua.petproject.model.AbstractNamedEntity.SORT_BY_NAME;

@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository crudMealRepository;

    @Override
    @Transactional
    @CacheEvict(value = "dishes", allEntries = true)
    public Dish save(Dish dish) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        return crudMealRepository.save(dish);
    }

    @Override
    @Transactional
    @CacheEvict(value = "dishes", allEntries = true)
    public boolean delete(int id) {
        return crudMealRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudMealRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable("dishes")
    public List<Dish> getAll() {
        return crudMealRepository.findAll(SORT_BY_NAME);
    }
}
