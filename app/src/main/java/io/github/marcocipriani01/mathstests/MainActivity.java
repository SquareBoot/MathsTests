package io.github.marcocipriani01.mathstests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int clickCount = 0;
    final String MATHS_SETTINGS = "MathsTests_settings";
    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(MATHS_SETTINGS, 0);
    }

    public void trinomialFactoring(View v) {
        Intent intent = new Intent(this, TrinomialFactoringActivity.class);
        startActivity(intent);
    }

    public void ruffini(View v) {
        Intent intent = new Intent(this, RuffiniActivity.class);
        startActivity(intent);
    }

    public void startGame(View v) {
        if (!getSetting()) {
            if (clickCount != 7) {
                clickCount++;

            } else {
                editSettings();
                Toast.makeText(this, "Easter egg unlocked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
            }

        } else {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }

    void editSettings() {
        preferencesEditor = preferences.edit();
        preferencesEditor.putBoolean("Egg_unlocked", true);
        preferencesEditor.apply();
    }

    boolean getSetting() {
        return preferences.getBoolean("Egg_unlocked", false);
    }
}