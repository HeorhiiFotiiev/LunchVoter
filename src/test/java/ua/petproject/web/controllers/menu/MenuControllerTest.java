package ua.petproject.web.controllers.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.petproject.model.Menu;
import ua.petproject.repository.menu.MenuRepository;
import ua.petproject.web.controllers.AbstractControllerTest;
import ua.petproject.web.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.petproject.TestUtil.readFromJson;
import static ua.petproject.TestUtil.readFromJsonMvcResult;
import static ua.petproject.testdata.MenuTestData.*;

public class MenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuController.REST_URL + '/';

    @Autowired
    private MenuRepository repository;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Menu.class), SUMMER_MENU));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID))
                .andExpect(status().isNoContent());
        assertMatch(repository.getAll(), KIEV_MENU,CANTEEN_MENU,DRINKS_MENU,PIZZA_MENU,SALADS_MENU,SUSHI_MENU);
    }

    @Test
    void testUpdate() throws Exception {
        Menu updated = getUpdated();

        mockMvc.perform(put(REST_URL + MENU1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(repository.get(MENU1_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Menu created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Menu returned = readFromJson(action, Menu.class);
        created.setId(returned.getId());

        assertMatch(returned, created);

        assertMatch(repository.getAll(), created, SUMMER_MENU,KIEV_MENU,
                CANTEEN_MENU,DRINKS_MENU,PIZZA_MENU,SALADS_MENU,SUSHI_MENU);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.getAll()));
    }


    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
