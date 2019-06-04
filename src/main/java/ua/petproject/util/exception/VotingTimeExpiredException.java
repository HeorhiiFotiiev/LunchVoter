package ua.petproject.util.exception;

public class VotingTimeExpiredException extends RuntimeException {
    public VotingTimeExpiredException(String message) {
        super(message);
    }
}
