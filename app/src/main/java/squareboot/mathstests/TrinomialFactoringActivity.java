package squareboot.mathstests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TrinomialFactoringActivity extends AppCompatActivity {

    public static final String TAG = "Maths - ";

    EditText sumView;
    EditText productView;
    EditText aView ;
    EditText bView;

    int sum;
    int product;

    @Override
    @SuppressWarnings("all")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinomial_factoring);

        try {
            //Back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {}

        sumView = (EditText) findViewById(R.id.Sum_Trinomial);
        productView = (EditText) findViewById(R.id.Product_Trinomial);
        aView = (EditText) findViewById(R.id.aTextTrinomial);
        bView = (EditText) findViewById(R.id.bTextTrinomial);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //You can use this like an onClickListener, but for menu items
        switch (item.getItemId()) {
            case android.R.id.home:
                //Back button
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void calculateTrinomial(View v) {
        try {
            //Start!
            Log.e(TAG + "log", "~~~ Starting ~~~");

            //Get the numbers from the EditTexts
            sum = Integer.parseInt(sumView.getText().toString());
            product = Integer.parseInt(productView.getText().toString());
            //Print the values
            Log.e(TAG + "Sum", String.valueOf(sum));
            Log.e(TAG + "Product", String.valueOf(product));

            //List of the dividers
            ArrayList<Integer> productDividers = MoreMaths.findDividers(product, "Trinomial factoring");

            Log.e(TAG + "array size", String.valueOf(productDividers.size()));

            for (int index = 0; index <  productDividers.size(); index++) {
                //For each divider

                //Store the dividers in a variable
                int firstDivider = productDividers.get(index);

                for (int secondIndex = 0; secondIndex <  productDividers.size(); secondIndex++) {
                    //Try to combine it with all the other ones

                    //Store the divider in a variable
                    int secondDivider = productDividers.get(secondIndex);
                    //Print the current dividers
                    Log.e(TAG + "Processing A", String.valueOf(firstDivider));
                    Log.e(TAG + "Processing B", String.valueOf(secondDivider));

                    //Sum the selected dividers
                    int TMPSum = firstDivider + secondDivider;
                    int TMPProduct = firstDivider * secondDivider;
                    //And print them
                    Log.e(TAG + "Sum found", String.valueOf(TMPSum));
                    Log.e(TAG + "Product found", String.valueOf(TMPProduct));

                    if ((TMPSum == sum) && (TMPProduct == product)) {
                        //If they can be accepted, show them on the screen
                        aView.setText(String.valueOf(firstDivider));
                        bView.setText(String.valueOf(secondDivider));

                        //Print the result
                        Log.e(TAG + "Result", String.valueOf(firstDivider) + " e " + String.valueOf(secondDivider));
                        Log.e(TAG + "log", "~~~ The end ~~~");

                        //Show a message
                        Toast.makeText(this, "Successfully found!", Toast.LENGTH_SHORT).show();
                        //Exit loop
                        return;
                    }
                }
            }
            //If no result found
            Log.e(TAG + "log", "~~~ The end ~~~");
            Toast.makeText(this, "No result found!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            //Error log
            Log.e(TAG + "log", "► Error:");
            Log.e(TAG + "log", e.getMessage());
            Log.e(TAG + "log", "End error ◄");
            Toast.makeText(this, "Error during calculations!", Toast.LENGTH_SHORT).show();
        }
    }

    public void reverseTrinomial(View v) {
        try {
            sumView.setText(String.valueOf(Integer.valueOf(aView.getText().toString()) + Integer.valueOf(bView.getText().toString())));
            productView.setText(String.valueOf(Integer.valueOf(aView.getText().toString()) * Integer.valueOf(bView.getText().toString())));

        } catch (Exception e) {
            //Error log
            Log.e(TAG + "log", "► Error:");
            Log.e(TAG + "log", e.getMessage());
            Log.e(TAG + "log", "End error ◄");
            Toast.makeText(this, "Error during calculations!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearTrinomial(View v) {
        sumView.setText("");
        productView.setText("");
        aView.setText("");
        bView.setText("");
    }
}
