package ua.petproject.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Dish;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Query("DELETE FROM Dish d WHERE d.id=:id")
    @Modifying
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish item);

    @Override
    Optional<Dish> findById(Integer id);

}
