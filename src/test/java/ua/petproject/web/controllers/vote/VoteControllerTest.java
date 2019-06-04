package ua.petproject.web.controllers.vote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;
import ua.petproject.model.Vote;
import ua.petproject.repository.vote.VoteRepository;
import ua.petproject.web.controllers.AbstractControllerTest;
import ua.petproject.web.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.petproject.TestUtil.readFromJson;
import static ua.petproject.TestUtil.userHttpBasic;
import static ua.petproject.testdata.RestaurantTestData.SCHOOL_CANTEEN;
import static ua.petproject.testdata.UserTestData.ADMIN;
import static ua.petproject.testdata.UserTestData.USER;
import static ua.petproject.testdata.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    private static final String ADMIN_ACCESS = "admin/";

    @Autowired
    private VoteRepository repository;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL+VOTE_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.get(VOTE_ID,USER.getId())));
    }

    @Test
    void testGetSameRestaurantVotesAmount() throws Exception{
        mockMvc.perform(get(REST_URL +"restaurantVotes/"+ SCHOOL_CANTEEN.getId())
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(Integer.parseInt(result.getResponse().getContentAsString()),
                        repository.getSameRestaurantVotesAmount(SCHOOL_CANTEEN.getId())));
    }

    @Test
    void testUpdateSuccessful() throws Exception{
        Vote updated = getUpdated();
        mockMvc.perform(put(REST_URL+VOTE_ID)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(repository.get(VOTE_ID,USER.getId()), updated);
    }

    @Test
    void TestUpdateTooLate() throws Exception{
        Vote updated = getUpdatedTooLate();
        Assertions.assertThrows(NestedServletException.class, () -> mockMvc.perform(put(REST_URL+VOTE_ID)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk()));
    }

    @Test
    void testCreate() throws Exception{
        Vote created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());
        created.setUser(USER);

        assertMatch(returned, created);

        assertMatch(repository.getAll(),VOTE1, created,VOTE2,VOTE3);
    }

    @Test
    void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception{
        mockMvc.perform(delete(REST_URL + ADMIN_ACCESS + VOTE_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(repository.getAll(), VOTE2,VOTE3);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
