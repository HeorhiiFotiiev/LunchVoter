package ua.petproject.web.controllers.dish;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.petproject.model.Dish;
import ua.petproject.repository.dish.DishRepository;
import ua.petproject.web.controllers.AbstractControllerTest;
import ua.petproject.web.json.JsonUtil;

import java.util.List;


import static ua.petproject.TestUtil.readFromJson;
import static ua.petproject.TestUtil.readFromJsonMvcResult;
import static ua.petproject.testdata.DishTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.REST_URL + '/';

    @Autowired
    private DishRepository repository;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Dish.class), HAMBURGER));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID))
                .andExpect(status().isNoContent());
        assertMatch(repository.getAll(), GOHAN, SELYANSKI_POTATO, CRAB_SALAD,
                TEMPURA_SHRIMPS, BBQ_WINGS, MISO_SOUP, PAELLA, SPINACH_RICE, UDON_SOUP);
    }

    @Test
    void testUpdate() throws Exception {
        Dish updated = getUpdated();

        mockMvc.perform(put(REST_URL + DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(repository.get(DISH1_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Dish returned = readFromJson(action, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(repository.getAll(), created, HAMBURGER, GOHAN, SELYANSKI_POTATO, CRAB_SALAD,
                TEMPURA_SHRIMPS, BBQ_WINGS, MISO_SOUP, PAELLA, SPINACH_RICE, UDON_SOUP);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.getAll()));
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
