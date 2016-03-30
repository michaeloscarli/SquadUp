package com.example.michaelli.squadup;

/**
 * Created by Jason on 3/30/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class CourtView extends ImageView implements View.OnTouchListener{
    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int dotRadius;

    public CourtView(Context context) {
        super(context);
        initDotsView();
    }

    public CourtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDotsView();
    }

    public CourtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDotsView();
    }

    public CourtView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDotsView();
    }

    private void initDotsView() {
        mPaint = new Paint();
        dotRadius = 10;
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }


    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        int index = event.getActionIndex();
        float x = event.getX(index);
        float y = event.getY(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mCanvas.drawCircle(x, y, dotRadius, mPaint);
                invalidate();
                break;
            default:
                break;

        }
        return true;
    }
}
