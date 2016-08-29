package squareboot.mathstests;


public class PolynomialGradeException extends Exception {

    final String errorString = "Fatal error: invalid polynomial grade!";

    @Override
    public String getMessage() {
        return errorString;
    }
}
