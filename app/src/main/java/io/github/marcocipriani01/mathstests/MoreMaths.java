package io.github.marcocipriani01.mathstests;

import android.util.Log;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class MoreMaths {

    public static final String TAG = "MoreMaths - ";

    public static int GCD(int a, int b) {
        String TMPLog = "GCD(" + String.valueOf(a) + ", " + String.valueOf(b) + ") = ";

        a = Math.abs(a);
        b = Math.abs(b);

        if ((a == 0) && (b == 0)) {
            return 0;
        }
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if ((a == 1) || (b == 1)) {
            return 1;
        }

        while (a != b) {
            if (a > b ) {
                a = a - b;

            } else {
                b = b - a;
            }
        }

        Log.e(TAG + "basics", TMPLog + String.valueOf(a));
        return a;
    }

    public static int LCM(int a, int b) {
        if ((a == 0) || (b == 0)) {
            return 0;
        }

        int TMP = Math.abs(a * b) / GCD(a, b);

        Log.e(TAG + "basics", "LCM(" + String.valueOf(a) + ", " + String.valueOf(b) + ") = " + String.valueOf(TMP));
        return TMP;
    }

    public static ArrayList<Integer> findDividers(int number) {
        ArrayList<Integer> dividers = new ArrayList<>();

        if (number == 0) {
            dividers.add(1);
            return dividers;
        }

        for (int index = 1; index <= Math.abs(number); index++) {
            //For each number from 1 to the number...

            if ((number % index) == 0) {
                //...if that number is a divider, add it to the list
                dividers.add(index);
                //Also add the reverse
                dividers.add(0 - index);
            }
        }

        return dividers;
    }
    public static ArrayList<Integer> findDividers(int number, String logcatTag) {
        logcatTag = "@" + logcatTag + " with n = " + String.valueOf(number) + " -> ";
        ArrayList<Integer> dividers = new ArrayList<>();

        if (number == 0) {
            dividers.add(1);
            return dividers;
        }

        for (int index = 1; index <= Math.abs(number); index++) {
            //For each number from 1 to the number...

            if ((number % index) == 0) {
                //...if that number is a divider, add it to the list
                dividers.add(index);
                //Also add the reverse
                dividers.add(0 - index);
                //Print the new divider to the computer
                Log.e(TAG + "divider", logcatTag + "±" + String.valueOf(index));
            }
        }

        return dividers;
    }

    public static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        if ((n % 2) == 0) {
            return false;
        }

        for (int i = 3; i*i <= n; i += 2) {
            if ((n % i) == 0) {
                return false;
            }
        }

        return true;
    }

    public static int random(int min, int max) {
        return (int) (min + (max - min) * Math.random());
    }
}