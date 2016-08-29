package squareboot.mathstests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FractionsActivity extends AppCompatActivity {

    public static final String TAG = "Maths - ";

    //  fractionsViews list:
    // 0 = Fractions_first_numerator
    // 1 = Fractions_first_denominator
    // 2 = Fractions_second_numerator
    // 3 = Fractions_second_denominator
    // 4 = Fractions_result_numerator
    // 5 = Fractions_result_denominator

    ArrayList<EditText> fractionsViews = new ArrayList<>();

    Spinner spinner;


    @Override
    @SuppressWarnings("all")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fractions);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinner = (Spinner) findViewById(R.id.FractionsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        fractionsViews.add((EditText) findViewById(R.id.Fractions_first_numerator));
        fractionsViews.add((EditText) findViewById(R.id.Fractions_first_denominator));
        fractionsViews.add((EditText) findViewById(R.id.Fractions_second_numerator));
        fractionsViews.add((EditText) findViewById(R.id.Fractions_second_denominator));
        fractionsViews.add((EditText) findViewById(R.id.Fractions_result_numerator));
        fractionsViews.add((EditText) findViewById(R.id.Fractions_result_denominator));
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

    public void calculateFraction(View v) {
        try {
            String operation = spinner.getSelectedItem().toString();
            Log.e(TAG + "spinner", operation);

            Fraction first = new Fraction(getEditTextValue(0), getEditTextValue(1));
            Log.e(TAG + "first", first.toString());
            Fraction second = new Fraction(getEditTextValue(2), getEditTextValue(3));
            Log.e(TAG + "second", second.toString());

            switch (operation) {
                case "+": {
                    first.add(second);
                }

                case "-": {
                    first.subtract(second);
                }

                case "*": {
                    first.multiply(second);
                }

                case "/": {
                    first.divide(second);
                }
            }

            Log.e(TAG + "result", first.toString());
            fractionsViews.get(4).setText(String.valueOf(first.getNumerator()));
            fractionsViews.get(5).setText(String.valueOf(first.getDenominator()));

        } catch (Exception e) {
            //Error log
            Log.e(TAG + "log", "► Error:");
            Log.e(TAG + "log", e.getMessage());
            Log.e(TAG + "log", "End error ◄");
            Toast.makeText(this, "Error during calculations!", Toast.LENGTH_SHORT).show();
        }
    }

    int getEditTextValue(int arrayIndex) {
        String text = fractionsViews.get(arrayIndex).getText().toString();

        if (text.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(text);
        }
    }

    public void clearFractions(View v) {
        fractionsViews.get(0).setText("");
        fractionsViews.get(1).setText("");
        fractionsViews.get(2).setText("");
        fractionsViews.get(3).setText("");
        fractionsViews.get(4).setText("");
        fractionsViews.get(5).setText("");
    }
}
