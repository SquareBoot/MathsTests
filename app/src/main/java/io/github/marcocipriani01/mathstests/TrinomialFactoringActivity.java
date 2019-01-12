package io.github.marcocipriani01.mathstests;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TrinomialFactoringActivity extends AppCompatActivity {

    EditText sumView;
    EditText productView;
    EditText aView;
    EditText bView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinomial_factoring);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        sumView = findViewById(R.id.Sum_Trinomial);
        productView = findViewById(R.id.Product_Trinomial);
        aView = findViewById(R.id.aTextTrinomial);
        bView = findViewById(R.id.bTextTrinomial);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void calculateTrinomial(View v) {
        int a = Integer.parseInt(sumView.getText().toString()),
                b = Integer.parseInt(productView.getText().toString());
        int delta = a * a - 4 * b;

        if (delta >= 0) {
            aView.setText(String.valueOf((int) (-a + Math.sqrt(delta)) / -2));
            bView.setText(String.valueOf((int) (-a - Math.sqrt(delta)) / -2));

        } else {
            Toast.makeText(this, "No results found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void reverseTrinomial(View v) {
        try {
            sumView.setText(String.valueOf(Integer.valueOf(aView.getText().toString()) + Integer.valueOf(bView.getText().toString())));
            productView.setText(String.valueOf(Integer.valueOf(aView.getText().toString()) * Integer.valueOf(bView.getText().toString())));

        } catch (Exception e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearTrinomial(View v) {
        sumView.setText("");
        productView.setText("");
        aView.setText("");
        bView.setText("");
    }
}