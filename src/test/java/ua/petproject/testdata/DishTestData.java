package ua.petproject.testdata;


import org.springframework.test.web.servlet.ResultMatcher;
import ua.petproject.model.Dish;

import java.math.BigDecimal;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static ua.petproject.TestUtil.readListFromJsonMvcResult;
import static ua.petproject.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 4;


    public static final Dish HAMBURGER = new Dish(DISH1_ID,"Гамбургер", BigDecimal.valueOf(60.75), 650);
    public static final Dish CRAB_SALAD = new Dish(DISH1_ID + 1, "Крабовый салат", BigDecimal.valueOf(100.50), 435);
    public static final Dish PAELLA = new Dish(DISH1_ID + 2, "Паэлья", BigDecimal.valueOf(100.50), 795.5);
    public static final Dish GOHAN = new Dish(DISH1_ID + 3, "Гохан", BigDecimal.valueOf(100.50), 420);
    public static final Dish SELYANSKI_POTATO = new Dish(DISH1_ID + 4, "Картошка по-селянски", BigDecimal.valueOf(100.50), 600);
    public static final Dish SPINACH_RICE = new Dish(DISH1_ID + 5, "Рис со шпинатом", BigDecimal.valueOf(100.50), 388);
    public static final Dish BBQ_WINGS = new Dish(DISH1_ID + 6, "Крылышки BBQ", BigDecimal.valueOf(100.50), 422);
    public static final Dish TEMPURA_SHRIMPS = new Dish(DISH1_ID + 7, "Креветки в темпуре", BigDecimal.valueOf(100.50), 524);
    public static final Dish MISO_SOUP = new Dish(DISH1_ID + 8, "Мисо суп", BigDecimal.valueOf(100.50), 345);
    public static final Dish UDON_SOUP = new Dish(DISH1_ID + 9, "Удон суп", BigDecimal.valueOf(100.50), 480);

    public static final List<Dish> DISHES = List.of(HAMBURGER, CRAB_SALAD, PAELLA, GOHAN,
            SELYANSKI_POTATO, SPINACH_RICE, BBQ_WINGS, TEMPURA_SHRIMPS, MISO_SOUP, UDON_SOUP);

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Гамбургер++", HAMBURGER.getPrice(), HAMBURGER.getCalories());
    }
    public static Dish getCreated() {
        return new Dish(null, "Created dish",BigDecimal.valueOf(55),555);
    }

    public static ResultMatcher contentJson(Dish... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<Dish> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Dish.class)).isEqualTo(expected);
    }

}
