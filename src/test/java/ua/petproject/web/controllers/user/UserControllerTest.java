package ua.petproject.web.controllers.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.petproject.model.User;
import ua.petproject.repository.user.UserRepository;
import ua.petproject.web.controllers.AbstractControllerTest;
import ua.petproject.web.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.petproject.TestUtil.*;
import static ua.petproject.testdata.UserTestData.*;

public class UserControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserController.REST_URL + '/';

    @Autowired
    private UserRepository repository;

    @Test
    void testGetAllVotedForRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL+"allVotedForRestaurant/"+100021)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.getAllVotedForRestaurant(100021)));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, User.class), USER));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(repository.getAll(), ADMIN,JON,NEW_USER,PETER);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = getUpdated();

        mockMvc.perform(put(REST_URL + USER_ID)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(repository.get(USER_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        User created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        User returned = readFromJson(action, User.class);
        created.setId(returned.getId());

        assertMatch(returned, created);

        assertMatch(repository.getAll(),ADMIN,JON,NEW_USER,PETER,USER, created);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.getAll()));
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
