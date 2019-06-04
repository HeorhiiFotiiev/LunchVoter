package ua.petproject.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Vote;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("DELETE FROM Vote v WHERE v.id=:id")
    @Modifying
    @Transactional
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Vote save(Vote item);

    @Query("SELECT count(v) FROM Vote v WHERE v.restaurant.id=:restaurantId")
    @Transactional
    Integer getSameRestaurantVotes(@Param("restaurantId") int restaurantId);

    @Override
    Optional<Vote> findById(Integer id);

}
