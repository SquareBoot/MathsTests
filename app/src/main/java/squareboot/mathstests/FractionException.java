package squareboot.mathstests;

public class FractionException extends Exception {

    final String errorString = "Invalid fraction! Denominator can't be 0!";

    @Override
    public String getMessage() {
        return errorString;
    }
}