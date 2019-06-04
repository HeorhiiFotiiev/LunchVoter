package ua.petproject.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Menus")
public class Menu extends AbstractNamedEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Menus_Dishes", joinColumns = @JoinColumn(name = "Menu_id"),
            inverseJoinColumns = @JoinColumn(name = "Dish_id"))
    private Set<Dish> Dishes;

    public Menu(Integer id, String name, Set dishes) {
        super(id, name);
        Dishes = dishes;
    }

    public Menu() {
    }

    public Set getDishes() {
        return Dishes;
    }

    public void setDishes(Set dishes) {
        Dishes = dishes;
    }
}
