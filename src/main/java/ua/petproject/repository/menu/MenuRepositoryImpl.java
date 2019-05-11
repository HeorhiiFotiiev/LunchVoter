package ua.petproject.repository.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.petproject.model.Menu;

import java.util.List;

import static ua.petproject.model.AbstractNamedEntity.SORT_BY_NAME;

@Repository
@Transactional(readOnly = true)
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Override
    public Menu save(Menu menu) {
        if (!menu.isNew() && get(menu.getId()) == null) {
            return null;
        }
        else return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return crudMenuRepository.delete(id)!=0;
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.findById(id).orElse(null);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll(SORT_BY_NAME);
    }
}
