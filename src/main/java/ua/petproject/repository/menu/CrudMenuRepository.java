package ua.petproject.repository.menu;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Menu;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Query("DELETE FROM Menu m WHERE m.id=:id")
    @Modifying
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu item);

    @Override
    List<Menu> findAll(Sort sort);

    @Override
    Optional<Menu> findById(Integer id);
}