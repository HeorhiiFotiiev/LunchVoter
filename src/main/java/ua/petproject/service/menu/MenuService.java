package ua.petproject.service.menu;


import ua.petproject.model.Menu;

import java.util.List;

public interface MenuService {

    Menu get(int id);

    void delete(int id);

    List<Menu> getAll();

    void update(Menu menu);

    Menu create(Menu menu);
}
