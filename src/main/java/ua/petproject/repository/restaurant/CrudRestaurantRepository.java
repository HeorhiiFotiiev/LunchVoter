package ua.petproject.repository.restaurant;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    @Transactional
    @Modifying
    int delete(@Param("id") int id);

    @Override
    Restaurant save(Restaurant item);

    @Override
    List<Restaurant> findAll(Sort sort);

    @Override
    Optional<Restaurant> findById(Integer id);


}
