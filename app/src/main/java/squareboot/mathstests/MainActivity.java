package squareboot.mathstests;

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
    public void fractions(View v) {
        Intent intent = new Intent(this, FractionsActivity.class);
        startActivity(intent);
    }

    public void startGame(View v) {
        if (!getSetting("Egg_unlocked", false)) {
            if (clickCount != 7) {
                clickCount++;

            } else {
                editSettings("Egg_unlocked", true);
                Toast.makeText(this, "Easter egg unlocked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
            }

        } else {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }

    void editSettings(String tag, boolean value) {
        preferencesEditor = preferences.edit();
        preferencesEditor.putBoolean(tag, value);
        preferencesEditor.apply();
    }
    boolean getSetting(String tag, boolean defValue) {
        return preferences.getBoolean(tag, defValue);
    }
}