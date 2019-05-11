package ua.petproject.util;

import ua.petproject.model.Vote;

import java.time.LocalTime;

public class VoteUtil {

    public static boolean checkVotingTime(Vote vote){
        return vote.getDateTime().toLocalTime().isBefore(LocalTime.of(11,0));
    }
}
