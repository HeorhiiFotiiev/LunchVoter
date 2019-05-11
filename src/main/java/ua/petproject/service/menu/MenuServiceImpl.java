package ua.petproject.service.menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.petproject.model.Menu;
import ua.petproject.repository.menu.MenuRepository;

import java.util.List;

import static ua.petproject.util.ValidationUtil.checkNotFoundWithId;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;

    @Autowired
    public MenuServiceImpl(MenuRepository repository){
        this.repository = repository;
    }

    @Override
    public Menu get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Menu menu) {
        Assert.notNull(menu,"menu must not be null");
        checkNotFoundWithId(repository.save(menu), menu.getId());
    }

    @Override
    public Menu create(Menu menu) {
        Assert.notNull(menu,"menu must not be null");
        return repository.save(menu);
    }
}
