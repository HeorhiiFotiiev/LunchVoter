package ua.petproject.testdata;

import org.springframework.test.web.servlet.ResultMatcher;
import ua.petproject.model.Menu;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.petproject.TestUtil.readListFromJsonMvcResult;
import static ua.petproject.model.AbstractBaseEntity.START_SEQ;
import static ua.petproject.testdata.DishTestData.*;

public class MenuTestData {
    public static final int MENU1_ID = START_SEQ + 14;

    public static final Menu SUMMER_MENU = new Menu(MENU1_ID,"Летнее Меню", Set.of(PAELLA, SELYANSKI_POTATO));
    public static final Menu CANTEEN_MENU = new Menu(MENU1_ID+1,"Меню столовой", Set.of());
    public static final Menu KIEV_MENU = new Menu(MENU1_ID+2,"Киевское", Set.of());
    public static final Menu SALADS_MENU = new Menu(MENU1_ID+3,"Салаты", Set.of());
    public static final Menu SUSHI_MENU = new Menu(MENU1_ID+4,"Суши", Set.of(MISO_SOUP, UDON_SOUP));
    public static final Menu PIZZA_MENU = new Menu(MENU1_ID+5,"Пицца", Set.of());
    public static final Menu DRINKS_MENU = new Menu(MENU1_ID+6,"Напитки", Set.of());

    public static final List<Menu> MENUS = List.of(SUMMER_MENU, SUSHI_MENU, CANTEEN_MENU,
            DRINKS_MENU, PIZZA_MENU, SALADS_MENU, KIEV_MENU);

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, "Летнее меню+", Set.of(PAELLA, SELYANSKI_POTATO,HAMBURGER));
    }
    public static Menu getCreated() {
        return new Menu(null, "Created Test menu",Set.of(CRAB_SALAD, PAELLA, GOHAN));
    }

    public static ResultMatcher contentJson(Menu... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<Menu> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Menu.class)).isEqualTo(expected);
    }
}
