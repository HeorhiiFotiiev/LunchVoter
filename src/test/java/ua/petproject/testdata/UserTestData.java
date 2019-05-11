package ua.petproject.testdata;

import org.springframework.test.web.servlet.ResultMatcher;
import ua.petproject.model.Role;
import ua.petproject.model.User;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static ua.petproject.TestUtil.readFromJsonMvcResult;
import static ua.petproject.TestUtil.readListFromJsonMvcResult;
import static ua.petproject.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 3;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User JON = new User(USER_ID+1, "Jon", "Jon@gmail.com", "JonDoe12", Role.ROLE_USER);
    public static final User PETER = new User(USER_ID+2, "Peter", "Peter@gmail.com", "Peter12345", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static User getCreated(){
        return new User(null, "created user","someEmail@gmail","wordpass",Role.ROLE_USER);
    }
    public static User getUpdated(){
        return new User(USER_ID, "User+","user@yandex.ru","password",Role.ROLE_USER);
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "meals").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static ResultMatcher contentJson(Iterable<User> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, User.class)).isEqualTo(expected);
    }



}
