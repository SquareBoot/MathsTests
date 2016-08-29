package squareboot.mathstests;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

@SuppressWarnings("unused")
public class GameView extends View {

    final static String TAG = "GameView";
    final int SHAPE_SIZE = 100;

    private ShapeDrawable shape;
    private Paint paint;
    private Paint numberPaint;
    private boolean ifPrime = false;

    private long reactionTime = 0;
    private long startingTime = 0;
    private boolean isGameRunning = false;

    private int width = 0;
    private int height = 0;


    @SuppressWarnings("all")
    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setTextSize(40);

        numberPaint = new Paint();
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextSize(40);

        Log.e(TAG, "New game initialized.");

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isGameRunning = true;
                createShape();
                startingTime = System.currentTimeMillis();
                Log.e(TAG, "Game started.");

                setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.e(TAG, "Touched.");

                        if (shapeTouched((int) event.getX(), (int) event.getY())) {
                            if (!ifPrime) {
                                reactionTime = System.currentTimeMillis() - startingTime;
                                createShape();
                                startingTime = System.currentTimeMillis();
                                setBackgroundColor(Color.TRANSPARENT);
                                ifPrime = false;

                            } else {
                                int test = 1 / 0;
                            }

                        } else {
                            if (ifPrime) {
                                setBackgroundColor(Color.DKGRAY);
                                createShape();

                            } else {
                                int test = 1 / 0;
                            }
                        }

                        return false;
                    }
                });
                return false;
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void createShape() {
        shape = new ShapeDrawable(new OvalShape());
        shape.getPaint().setColor(getResources().getColor(R.color.colorAccent));

        int x = MoreMaths.random(0, width - SHAPE_SIZE);
        int y = MoreMaths.random(0, height - SHAPE_SIZE);

        shape.setBounds(x, y, x + SHAPE_SIZE, y + SHAPE_SIZE);
        invalidate();
    }

    public boolean shapeTouched(int x, int y) {
        if (shape == null) {
            return false;
        }
        Rect shapeBounds = shape.getBounds();
        return shapeBounds.contains(x, y);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isGameRunning) {
            width = getWidth();
            height = getHeight();
            canvas.drawText("Touch to start", width / 2, height / 2, paint);

        } else {
            if (shape != null) {
                shape.draw(canvas);
                int number = MoreMaths.random(2, 99);
                ifPrime = MoreMaths.isPrime(number);
                canvas.drawText(String.valueOf(number), shape.getBounds().centerX(), shape.getBounds().centerY() + 10, numberPaint);
                canvas.drawText("Reaction time: " + reactionTime + " ms", width / 2, 0.05f * height, paint);
            }
        }
    }
}