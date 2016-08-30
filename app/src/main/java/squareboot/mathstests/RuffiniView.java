package squareboot.mathstests;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

//@SuppressWarnings("all")
public class RuffiniView extends View {

    private static final String TAG = "RuffiniView - ";

    final int ERROR_TEXT_SIZE = 45;
    final int TEXT_SIZE = 40;

    final int CELL_WIDTH = 100;
    final int CELL_HEIGHT = 80;
    final int CELL_SPACE = 30;
    final int PADDING = 50;

    /*  Ruffini's rule table rectangles:
    *
    *       0           1
    *      ||           ||
    *      ||           ||
    *    __||___________||__ 2
    *      ||           ||
    * */
    ArrayList<ShapeDrawable> shapes = new ArrayList<>();
    Paint paint;
    Paint errorPaint;

    ArrayList<ArrayList<String>> table = new ArrayList<>();

    int grade;


    @SuppressWarnings("deprecation")
    public RuffiniView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.BLACK);
        paint.setTextSize(TEXT_SIZE);

        errorPaint = new Paint();
        errorPaint.setTextAlign(Paint.Align.CENTER);
        errorPaint.setColor(Color.WHITE);
        errorPaint.setTextSize(ERROR_TEXT_SIZE);
    }

    void setEntries(Fraction zero, ArrayList<Integer> polynomial) throws PolynomialGradeException {
        try {
            restoreTable();
            shapes.clear();

            grade = polynomial.size() - 1;

            //#0
            shapes.add(createNewShape(CELL_WIDTH + CELL_SPACE, 0, 2, 3 * CELL_HEIGHT + 2 * CELL_SPACE));
            //#1
            shapes.add(createNewShape(CELL_WIDTH + 2 * CELL_SPACE + grade * (CELL_SPACE + CELL_WIDTH), 0, 2, 3 * CELL_HEIGHT + 2 * CELL_SPACE));
            //#2
            shapes.add(createNewShape(0, 2 * (CELL_HEIGHT + CELL_SPACE), 2 * CELL_WIDTH + 3 * CELL_SPACE + grade * (CELL_SPACE + CELL_WIDTH), 2));

            for (int index = grade; index >= 0; index--) {
                setItem((grade + 1) - index, 2, polynomial.get(index));
            }
            setItem(0, 1, zero);
            setItem(1, 0, polynomial.get(grade));

            Fraction temp = new Fraction(polynomial.get(grade));

            for (int index = (grade - 1); index >= 0; index--) {
                temp = Fraction.multiplication(temp, zero);
                setItem((grade + 1) - index, 1, temp);
                temp = Fraction.addiction(new Fraction(polynomial.get(index)), temp);
                setItem((grade + 1) - index, 0, temp);
            }

        } catch (Exception e) {
            //Error log
            Log.e(TAG + "log", "► Error:");
            Log.e(TAG + "log", e.getMessage());
            Log.e(TAG + "log", "End error ◄");

            grade = -1;
            table.clear();
            shapes.clear();
        }

        invalidate();
    }

    ShapeDrawable createNewShape(int x, int y, int width, int height) {
        ShapeDrawable temp = new ShapeDrawable(new RectShape());
        temp.getPaint().setColor(Color.DKGRAY);
        temp.setBounds(PADDING + x, PADDING + y, PADDING + x + width, PADDING + y + height);
        return temp;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        try {
            if (shapes.size() != 0) {
                for (int index = 0; index < 3; index++) {
                    shapes.get(index).draw(canvas);
                }
                setBackgroundDrawable(null);

            } else {
                canvas.drawText("Table not available.", getWidth() / 2, getHeight() / 2, errorPaint);
                setBackgroundResource(R.drawable.blue_fade);
            }

            if (table.size() != 0) {
                placeTxt(0, 1, CELL_SPACE, 2 * CELL_HEIGHT + CELL_SPACE, canvas);
                placeTxt(1, 2, CELL_WIDTH + 2 * CELL_SPACE, PADDING, canvas);
                placeTxt(1, 0, CELL_WIDTH + 2 * CELL_SPACE, 3 * CELL_HEIGHT + 2 * CELL_SPACE, canvas);

                for (int index = 2; index <= grade; index++) {
                    placeTxt(index, 2, index * CELL_WIDTH + (index + 1) * CELL_SPACE, PADDING, canvas);
                    placeTxt(index, 1, index * CELL_WIDTH + (index + 1) * CELL_SPACE, 2 * CELL_HEIGHT + CELL_SPACE, canvas);
                    placeTxt(index, 0, index * CELL_WIDTH + (index + 1) * CELL_SPACE, 3 * CELL_HEIGHT + 2 * CELL_SPACE, canvas);
                }

                placeTxt(grade + 1, 2, (grade + 1) * CELL_WIDTH + (grade + 3) * CELL_SPACE, PADDING, canvas);
                placeTxt(grade + 1, 1, (grade + 1) * CELL_WIDTH + (grade + 3) * CELL_SPACE, 2 * CELL_HEIGHT + CELL_SPACE, canvas);
                placeTxt(grade + 1, 0, (grade + 1) * CELL_WIDTH + (grade + 3) * CELL_SPACE, 3 * CELL_HEIGHT + 2 * CELL_SPACE, canvas);

            } else {
                canvas.drawText("Table not available.", getWidth() / 2, getHeight() / 2, errorPaint);
                setBackgroundResource(R.drawable.blue_fade);
            }

        } catch (Exception e) {

            canvas.restore();
            setBackgroundDrawable(null);
            canvas.drawText("Table not available.", getWidth() / 2, getHeight() / 2, errorPaint);
            setBackgroundResource(R.drawable.blue_fade);

            //Error log
            Log.e(TAG + "log", "► Error:");
            Log.e(TAG + "log", e.getMessage());
            Log.e(TAG + "log", "End error ◄");
        }
    }

    void placeTxt(int tableX, int tableY, int x, int y, Canvas canvas) {
        canvas.drawText(table.get(tableX).get(tableY), PADDING + x, PADDING + y, paint);
    }

    void restoreTable() {
        table.clear();

        for (int i = 0; i <= 7; i++) {
            //NOTE: do not put the following lines of code before the for statement
            ArrayList<String> temp = new ArrayList<>();
            for (int index = 0; index <= 2; index++) {
                temp.add("");
            }
            //Why? All the ArrayLists in table will be "temp": referring to one of them will change all the lists in the table.

            table.add(temp);
        }
    }

    ArrayList<ArrayList<String>> getTable() {
        return table;
    }

    void setItem(int x, int y, String value) {
        table.get(x).set(y, value);
    }

    void setItem(int x, int y, int value) {
        setItem(x, y, String.valueOf(value));
    }

    void setItem(int x, int y, Fraction value) {
        setItem(x, y, value.toString());
    }
}