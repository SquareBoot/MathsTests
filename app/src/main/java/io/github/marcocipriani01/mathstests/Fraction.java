package io.github.marcocipriani01.mathstests;

import android.util.Log;

@SuppressWarnings("unused")
public class Fraction {

    private static final String TAG = "Fractions - ";

    private int fractionNumerator;
    private int fractionDenominator;


    public Fraction(int numerator, int denominator) throws ArithmeticException {
        this(numerator, denominator, true);
    }

    public Fraction(int numerator, int denominator, boolean reduce) throws ArithmeticException {
        if (denominator == 0) {
            throw (new ArithmeticException("Invalid fraction! Denominator can't be 0!"));
        }
        fractionNumerator = numerator;
        fractionDenominator = denominator;

        if (reduce) {
            reduce();
        }
    }

    public Fraction(int number) {
        fractionNumerator = number;
        fractionDenominator = 1;
    }

    public Fraction() {
        this(0);
    }

    public Fraction(Fraction other) throws ArithmeticException {
        this(other.getNumerator(), other.getDenominator());
    }

    /* STATIC methods */
    public static Fraction valueOf(int number) {
        return new Fraction(number);
    }

    public static Fraction valueOf(String number) {
        return new Fraction(Integer.parseInt(number));
    }

    public static Fraction pow(Fraction fraction, int y) throws ArithmeticException {
        String tempTag = "(" + fraction.toString() + ")^" + String.valueOf(y) + " = ";

        if (!fraction.isZero()) {
            Fraction newFraction = new Fraction((int) Math.pow(fraction.getNumerator(), y), (int) Math.pow(fraction.getDenominator(), y));
            Log.e(TAG + "pow", tempTag + newFraction.toString());
            return newFraction;
        }
        Log.e(TAG + "pow", tempTag + "error -> fraction = 0");
        return new Fraction();
    }

    public static Fraction addiction(Fraction a, Fraction b) throws ArithmeticException {
        if ((a.isZero()) && (b.isZero())) {
            return new Fraction();

        } else if (a.isZero()) {
            return b;

        } else if (b.isZero()) {
            return a;

        } else {
            int currentLCM = MoreMaths.LCM(a.getDenominator(), b.getDenominator());
            return new Fraction((a.getNumerator() * (currentLCM / a.getDenominator())) + (b.getNumerator() * (currentLCM / b.getDenominator())), currentLCM);
        }
    }

    public static Fraction subtraction(Fraction a, Fraction b) throws ArithmeticException {
        if ((a.isZero()) && (b.isZero())) {
            return new Fraction();

        } else if (a.isZero()) {
            return b;

        } else if (b.isZero()) {
            return a;

        } else {
            int currentLCM = MoreMaths.LCM(a.getDenominator(), b.getDenominator());
            return new Fraction((a.getNumerator() * (currentLCM / a.getDenominator())) - (b.getNumerator() * (currentLCM / b.getDenominator())), currentLCM);
        }
    }

    public static Fraction multiplication(Fraction a, Fraction b) throws ArithmeticException {
        if ((a.isZero()) || (b.isZero())) {
            return new Fraction();

        } else {
            return new Fraction(a.getNumerator() * b.getNumerator(), a.getDenominator() * b.getDenominator());
        }
    }

    public static Fraction division(Fraction a, Fraction b) throws ArithmeticException {
        if ((a.isZero()) || (b.isZero())) {
            return new Fraction();

        } else {
            return new Fraction(a.getNumerator() * b.getDenominator(), a.getDenominator() * b.getNumerator());
        }
    }

    public int getNumerator() {
        return fractionNumerator;
    }

    public void setNumerator(int numerator) {
        fractionNumerator = numerator;
    }

    public int getDenominator() {
        return fractionDenominator;
    }

    public void setDenominator(int denominator) {
        fractionDenominator = denominator;
    }

    public Fraction reduce() {
        String tempTag = "Reducing " + toString() + ", now ";

        if (isZero()) {
            fractionDenominator = 1;
            fractionNumerator = 0;
            Log.e(TAG + "log", toString() + " = 0!");
            return this;

        } else {
            int currentGCD = MoreMaths.GCD(fractionNumerator, fractionDenominator);

            while (currentGCD != 1) {
                fractionNumerator = fractionNumerator / currentGCD;
                fractionDenominator = fractionDenominator / currentGCD;
                currentGCD = MoreMaths.GCD(fractionNumerator, fractionDenominator);
            }
        }

        if (((fractionNumerator) < 0) && (fractionDenominator < 0) || (fractionDenominator < 0)) {
            reverse();
        }

        Log.e(TAG + "log", tempTag + toString());
        return this;
    }

    public Fraction reverse() {
        fractionNumerator = 0 - fractionNumerator;
        fractionDenominator = 0 - fractionDenominator;
        Log.e(TAG + "log", "Signs adjusted: " + toString());
        return this;
    }

    public Fraction reverseNumerator() {
        fractionNumerator = 0 - fractionNumerator;
        return this;
    }

    public Fraction reciprocal() {
        if ((fractionNumerator == 0) || (fractionDenominator == 0)) {
            return new Fraction();
        }

        int temp = fractionNumerator;
        fractionNumerator = fractionDenominator;
        fractionDenominator = temp;
        return this;
    }

    @Override
    public String toString() {
        if (fractionDenominator == 1) {
            return String.valueOf(fractionNumerator);

        } else {
            return (String.valueOf(fractionNumerator) + " / " + String.valueOf(fractionDenominator));
        }
    }

    public int toInt() throws ArithmeticException {
        if (fractionDenominator == 0) {
            throw (new ArithmeticException("Invalid fraction! Denominator can't be 0!"));
        }

        if ((fractionNumerator % fractionDenominator) == 0) {
            Log.e(TAG + "toInt()", "ToInt() - OK.");

        } else {
            Log.e(TAG + "toInt()", "ToInt() - not an integer!");
        }
        return (fractionNumerator / fractionDenominator);
    }

    public float toFloat() throws ArithmeticException {
        if (fractionDenominator == 0) {
            throw (new ArithmeticException("Invalid fraction! Denominator can't be 0!"));
        }
        return ((float) fractionNumerator / (float) fractionDenominator);
    }

    public boolean isZero() {
        return ((fractionNumerator == 0) || (fractionDenominator == 0));
    }

    public boolean isEqual(Fraction other) {
        reduce();
        other.reduce();
        return ((fractionNumerator == other.getNumerator()) && (fractionDenominator == other.getDenominator()));
    }

    Fraction primitiveAdd(Fraction other) {
        if (fractionDenominator == other.getDenominator()) {
            fractionNumerator = fractionNumerator + other.getNumerator();
            return this;
        }

        reduce();
        other.reduce();

        if ((isZero()) && (other.isZero())) {
            return new Fraction();

        } else if (isZero()) {
            return other;

        } else if (other.isZero()) {
            return this;

        } else {
            int currentLCM = MoreMaths.LCM(fractionDenominator, other.getDenominator());
            fractionNumerator = (fractionNumerator * (currentLCM / fractionDenominator)) + (other.getNumerator() * (currentLCM / other.getDenominator()));
            fractionDenominator = currentLCM;
            return reduce();
        }
    }

    public Fraction add(Fraction... args) {
        String tempTag = "(" + toString() + ")";
        for (Fraction arg : args) {
            primitiveAdd(arg);
            tempTag = tempTag + " + (" + arg.toString() + ")";
        }
        Log.e(TAG + "log", tempTag + " = (" + toString() + ")");
        return this;
    }

    public Fraction subtract(Fraction... args) {
        for (Fraction arg : args) {
            primitiveAdd(arg.reverseNumerator());
        }
        return this;
    }

    Fraction primitiveMultiplication(Fraction other) {
        if ((isZero()) || (other.isZero())) {
            return new Fraction();

        } else {
            fractionNumerator = fractionNumerator * other.getNumerator();
            fractionDenominator = fractionDenominator * other.getDenominator();
            return reduce();
        }
    }

    public Fraction multiply(Fraction... args) {
        String tempTag = "(" + toString() + ")";
        for (Fraction arg : args) {
            primitiveMultiplication(arg);
            tempTag = tempTag + " * (" + arg.toString() + ")";
        }
        Log.e(TAG + "log", tempTag + " = (" + toString() + ")");
        return this;
    }

    public Fraction divide(Fraction... args) {
        for (Fraction arg : args) {
            primitiveMultiplication(arg.reciprocal());
        }
        return this;
    }

    public Fraction pow(int y) {
        String tempTag = "(" + toString() + ")^" + String.valueOf(y) + " = ";
        fractionNumerator = ((int) Math.pow(fractionNumerator, y));
        fractionDenominator = (int) Math.pow(fractionDenominator, y);
        reduce();
        Log.e(TAG + "pow", tempTag + toString());
        return this;
    }
}