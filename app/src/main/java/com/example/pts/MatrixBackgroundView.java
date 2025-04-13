package com.example.pts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.Random;

public class MatrixBackgroundView extends View {
    private Paint paint = new Paint();
    private int[] numbers;
    private int[] positions;
    private int[] speeds;
    private Random random = new Random();
    private int textSize = 30;
    private int columns = 20;

    // Required constructors for XML inflation
    public MatrixBackgroundView(Context context) {
        super(context);
        init();
    }

    public MatrixBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(Color.GREEN);
        paint.setTextSize(textSize);
        updateNumberArrays();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateNumberArrays();
    }

    private void updateNumberArrays() {
        int rows = (int) (getHeight() / textSize) + 1;
        int total = columns * rows;
        numbers = new int[total];
        positions = new int[total];
        speeds = new int[total];

        for (int i = 0; i < total; i++) {
            numbers[i] = random.nextInt(2);
            positions[i] = -random.nextInt(1000);
            speeds[i] = 5 + random.nextInt(10);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int columnWidth = width / columns;

        for (int i = 0; i < numbers.length; i++) {
            int x = (i % columns) * columnWidth + columnWidth/2;
            int y = positions[i];

            int alpha = 255;
            if (y > getHeight() - 200) {
                alpha = (int) (255 * (1 - (float)(y - (getHeight() - 200))/200));
            }
            paint.setAlpha(alpha);

            canvas.drawText(String.valueOf(numbers[i]), x, y, paint);

            positions[i] += speeds[i];
            if (positions[i] > getHeight()) {
                positions[i] = -random.nextInt(100);
                numbers[i] = random.nextInt(2);
            }
        }

        invalidate();
    }
}