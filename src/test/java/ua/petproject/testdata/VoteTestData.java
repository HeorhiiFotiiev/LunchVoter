package ua.petproject.testdata;

import org.springframework.test.web.servlet.ResultMatcher;
import ua.petproject.model.Vote;

import java.time.LocalDateTime;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static ua.petproject.TestUtil.readListFromJsonMvcResult;
import static  ua.petproject.testdata.UserTestData.*;
import static  ua.petproject.testdata.RestaurantTestData.*;
import static ua.petproject.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {


    public static final int VOTE_ID = START_SEQ+27;

    public static final Vote VOTE1 = new Vote(VOTE_ID,
            LocalDateTime.of(2018,9,29,10,0),SCHOOL_CANTEEN);
    public static final Vote VOTE2 = new Vote(VOTE_ID+1,
            LocalDateTime.of(2018,9,29,11,0),SCHOOL_CANTEEN);
    public static final Vote VOTE3 = new Vote(VOTE_ID+2,
            LocalDateTime.of(2018,9,29,9,0),KIEVSKY_RESTAURANT);

    public static Vote getCreated(){
        return new Vote(null, LocalDateTime.now(),SCHOOL_CANTEEN);
    }
    public static Vote getUpdated(){
        return new Vote(VOTE_ID, LocalDateTime.of(2019,04,25,10,00),SOLO_PIZZA);
    }
    public static Vote getUpdatedTooLate(){
        return new Vote(VOTE_ID, LocalDateTime.of(2019,04,25,13,00),SOLO_PIZZA);
    }

    public static ResultMatcher contentJson(Vote... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<Vote> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Vote.class)).isEqualTo(expected);
    }

}
