package squareboot.mathstests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RuffiniActivity extends AppCompatActivity {

    public static final String TAG = "Maths - ";

    // ruffiniViews ArrayList:
    // 0 = Zero_Ruffini
    // 1 = First_monomial_Ruffini
    // 2 = Second_monomial_Ruffini
    // 3 = Third_monomial_Ruffini
    // 4 = Fourth_monomial_Ruffini
    // 5 = Fifth_monomial_Ruffini
    // 6 = Sixth_monomial_Ruffini
    // 7 = PolynomialZeroRuffini
    // 8 = ResultRuffini
    ArrayList<EditText> ruffiniViews = new ArrayList<>();
    TextView zeroView;
    TextView resultView;

    @Override
    @SuppressWarnings("all")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruffini);

        try {
            //Back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
        }

        //Views
        ruffiniViews.add((EditText) findViewById(R.id.Zero_Ruffini));
        ruffiniViews.add((EditText) findViewById(R.id.First_monomial_Ruffini));
        ruffiniViews.add((EditText) findViewById(R.id.Second_monomial_Ruffini));
        ruffiniViews.add((EditText) findViewById(R.id.Third_monomial_Ruffini));
        ruffiniViews.add((EditText) findViewById(R.id.Fourth_monomial_Ruffini));
        ruffiniViews.add((EditText) findViewById(R.id.Fifth_monomial_Ruffini));
        ruffiniViews.add((EditText) findViewById(R.id.Sixth_monomial_Ruffini));
        zeroView = (TextView) findViewById(R.id.PolynomialZeroRuffini);
        resultView = (TextView) findViewById(R.id.ResultRuffini);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void calculateRuffini(View v) {
        try {
            int grade;
            ArrayList<Integer> maxDividers;
            ArrayList<Integer> minDividers;

            ArrayList<Integer> polynomial = new ArrayList<>();

            if (!isEmpty(6)) {
                maxDividers = MoreMaths.findDividers(getEditTextValue(6), "maxDividers");
                Log.e(TAG + "grade", "6");
                grade = 6;

            } else if (!isEmpty(5)) {
                maxDividers = MoreMaths.findDividers(getEditTextValue(5), "maxDividers");
                Log.e(TAG + "grade", "5");
                grade = 5;

            } else if (!isEmpty(4)) {
                maxDividers = MoreMaths.findDividers(getEditTextValue(4), "maxDividers");
                Log.e(TAG + "grade", "4");
                grade = 4;

            } else if (!isEmpty(3)) {
                maxDividers = MoreMaths.findDividers(getEditTextValue(3), "maxDividers");
                Log.e(TAG + "grade", "3");
                grade = 3;

            } else if (!isEmpty(2)) {
                maxDividers = MoreMaths.findDividers(getEditTextValue(2), "maxDividers");
                Log.e(TAG + "grade", "2");
                grade = 2;

            } else if (!isEmpty(1)) {
                maxDividers = MoreMaths.findDividers(getEditTextValue(1), "maxDividers");
                Log.e(TAG + "grade", "1");
                grade = 1;

            } else {
                Toast.makeText(this, "Please, enter a polynomial.", Toast.LENGTH_SHORT).show();
                Log.e(TAG + "factorizing", "Please, enter a polynomial.");
                return;
            }

            if (isEmpty(0)) {
                Toast.makeText(this, "First, try taking the common factors.", Toast.LENGTH_SHORT).show();
                Log.e(TAG + "factorizing", "First, try taking the common factors.");
                return;
            }

            for (int index = 0; index <= grade; index++) {
                polynomial.add(getEditTextValue(index));
            }

            minDividers = MoreMaths.findDividers(getEditTextValue(0), "minDividers");

            for (int index = 0; index < minDividers.size(); index++) {

                for (int secondIndex = 0; secondIndex < maxDividers.size(); secondIndex++) {
                    Fraction temp = new Fraction(minDividers.get(index), maxDividers.get(secondIndex));
                    Log.e(TAG + "processing m/n", temp.toString());

                    Fraction currentResult;

                    switch (grade) {
                        case 6: {
                            Log.e(TAG + "log", "calculating currentResult, 6th grade");
                            currentResult = fromText(6).multiply(Fraction.pow(temp, 6)).add(
                                    fromText(5).multiply(Fraction.pow(temp, 5)),
                                    fromText(4).multiply(Fraction.pow(temp, 4)),
                                    fromText(3).multiply(Fraction.pow(temp, 3)),
                                    fromText(2).multiply(Fraction.pow(temp, 2)),
                                    fromText(1).multiply(temp),
                                    fromText(0));
                            Log.e(TAG + "log", "TMP = " + temp.toString());
                            break;
                        }

                        case 5: {
                            Log.e(TAG + "log", "calculating currentResult, 5th grade");
                            currentResult = fromText(5).multiply(Fraction.pow(temp, 5)).add(
                                    fromText(4).multiply(Fraction.pow(temp, 4)),
                                    fromText(3).multiply(Fraction.pow(temp, 3)),
                                    fromText(2).multiply(Fraction.pow(temp, 2)),
                                    fromText(1).multiply(temp),
                                    fromText(0));
                            Log.e(TAG + "log", "TMP = " + temp.toString());
                            break;
                        }

                        case 4: {
                            Log.e(TAG + "log", "calculating currentResult, 4th grade");
                            currentResult = fromText(4).multiply(Fraction.pow(temp, 4)).add(
                                    fromText(3).multiply(Fraction.pow(temp, 3)),
                                    fromText(2).multiply(Fraction.pow(temp, 2)),
                                    fromText(1).multiply(temp),
                                    fromText(0));
                            Log.e(TAG + "log", "TMP = " + temp.toString());
                            break;
                        }

                        case 3: {
                            Log.e(TAG + "log", "calculating currentResult, 3rd grade");
                            currentResult = fromText(3).multiply(Fraction.pow(temp, 3)).add(
                                    fromText(2).multiply(Fraction.pow(temp, 2)),
                                    fromText(1).multiply(temp),
                                    fromText(0));
                            Log.e(TAG + "log", "TMP = " + temp.toString());
                            break;
                        }

                        case 2: {
                            Log.e(TAG + "log", "calculating currentResult, 2nd grade");
                            currentResult = fromText(2).multiply(Fraction.pow(temp, 2)).add(
                                    fromText(1).multiply(temp),
                                    fromText(0));
                            Log.e(TAG + "log", "TMP = " + temp.toString());
                            break;
                        }

                        case 1: {
                            Log.e(TAG + "log", "calculating currentResult, 1st grade");
                            currentResult = fromText(1).multiply(temp).add(
                                    fromText(0));
                            Log.e(TAG + "log", "TMP = " + temp.toString());
                            break;
                        }

                        default: {
                            throw (new NullPointerException("Fatal error: invalid polynomial!"));
                        }
                    }
                    Log.e(TAG + "result", currentResult.toString());

                    if (currentResult.isZero()) {
                        zeroView.setText(temp.toString());
                        Toast.makeText(this, "Zero found.", Toast.LENGTH_SHORT).show();
                        Log.e(TAG + "factorizing", "Zero found: " + temp.toString());

                        RuffiniView table = (RuffiniView) findViewById(R.id.ruffini_table);
                        table.setEntries(temp, polynomial);

                        resultView.setText(table.getResult());

                        return;
                    }
                }
            }

            Toast.makeText(this, "Nothing found...", Toast.LENGTH_SHORT).show();
            Log.e(TAG + "factorizing", "Error -> nothing found!");

        } catch (Exception e) {
            //Error log
            Log.e(TAG + "log", "► Error:");
            Log.e(TAG + "log", e.getMessage());
            Log.e(TAG + "log", "End error ◄");
            Toast.makeText(this, "Error during calculations!", Toast.LENGTH_SHORT).show();
        }
    }

    Boolean isEmpty(int arrayIndex) {
        return ((ruffiniViews.get(arrayIndex).getText().toString().equals("")) || (getEditTextValue(arrayIndex) == 0));
    }

    int getEditTextValue(int arrayIndex) {
        String text = ruffiniViews.get(arrayIndex).getText().toString();

        if (text.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(text);
        }
    }

    Fraction fromText(int arrayIndex) {
        return new Fraction(getEditTextValue(arrayIndex));
    }

    public void clearRuffini(View v) {
        for (int index = 0; index <= 7; index++) {
            ruffiniViews.get(index).setText("");
        }
    }
}