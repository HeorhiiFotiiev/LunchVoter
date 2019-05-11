package ua.petproject.testdata;

import org.springframework.test.web.servlet.ResultMatcher;
import ua.petproject.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ua.petproject.TestUtil.readListFromJsonMvcResult;
import static ua.petproject.model.AbstractBaseEntity.START_SEQ;
import static ua.petproject.testdata.MenuTestData.*;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ + 21;

    public static final Restaurant SCHOOL_CANTEEN = new Restaurant(RESTAURANT1_ID,"School canteen","Florida, st.Paola street 17",CANTEEN_MENU);
    public static final Restaurant KIEVSKY_RESTAURANT = new Restaurant(RESTAURANT1_ID+1,"Kievsky restaurant","Kiev, Horiva street 1",KIEV_MENU);
    public static final Restaurant SALATERIA = new Restaurant(RESTAURANT1_ID+2,"Salateria","Kiev, Shevchenka street 5",SALADS_MENU);
    public static final Restaurant EVRASIA = new Restaurant(RESTAURANT1_ID+3,"Evrasia","Kiev, Bolshoi Val street 22",SUSHI_MENU);
    public static final Restaurant SOLO_PIZZA = new Restaurant(RESTAURANT1_ID+4,"Solo Pizza","Kiev, Petra Sagaidachnogo street 17",PIZZA_MENU);

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "School canteen+", SCHOOL_CANTEEN.getAdress(),CANTEEN_MENU);
    }
    public static Restaurant getCreated() {
        return new Restaurant(null, "Created restaurant", "Some address",CANTEEN_MENU);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<Restaurant> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Restaurant.class)).isEqualTo(expected);
    }
}
