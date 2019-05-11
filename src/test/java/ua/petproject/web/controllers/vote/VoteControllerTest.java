package ua.petproject.web.controllers.vote;

import com.fasterxml.jackson.core.json.JsonWriteContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ua.petproject.model.User;
import ua.petproject.model.Vote;
import ua.petproject.repository.vote.VoteRepository;
import ua.petproject.web.controllers.AbstractControllerTest;
import ua.petproject.web.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.petproject.TestUtil.readFromJson;
import static ua.petproject.testdata.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL+VOTE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.get(VOTE_ID)));
    }

    @Test
    void testGetAll() throws Exception{
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(repository.getAll()));
    }

    @Test
    void testUpdate() throws Exception{
        Vote updated = getUpdated();
        mockMvc.perform(put(REST_URL+VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(repository.get(VOTE_ID), updated);
    }

    @Test
    void testCreate() throws Exception{
        Vote created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.writeValue(created)));

        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);

        assertMatch(repository.getAll(),VOTE1, created,VOTE2,VOTE3);
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
