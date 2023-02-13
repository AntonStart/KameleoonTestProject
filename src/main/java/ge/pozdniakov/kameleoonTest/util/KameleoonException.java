package ge.pozdniakov.kameleoonTest.util;

public class KameleoonException extends RuntimeException {

    public KameleoonException() {
        super("Not found!");
    }

    public KameleoonException(String message) {
        super(message);
    }
}
