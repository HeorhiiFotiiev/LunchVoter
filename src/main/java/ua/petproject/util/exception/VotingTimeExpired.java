package ua.petproject.util.exception;

public class VotingTimeExpired extends RuntimeException {
    public VotingTimeExpired(String message) {
        super(message);
    }
}
