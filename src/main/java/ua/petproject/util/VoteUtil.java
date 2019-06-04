package ua.petproject.util;

import ua.petproject.model.Vote;
import ua.petproject.util.exception.VotingTimeExpiredException;

import java.time.LocalTime;

public class VoteUtil {

    public static boolean checkVotingTime(Vote vote){
        if (vote.getDateTime().toLocalTime().isBefore(LocalTime.of(11,0))) {
            return true;
        }
        else {
            throw new VotingTimeExpiredException("You can't change your vote after 11.00, it's "+vote.getDateTime().toLocalTime()+" already");
        }
    }
}
