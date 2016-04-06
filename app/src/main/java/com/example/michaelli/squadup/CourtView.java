package com.example.michaelli.squadup;

/**
 * Created by Jason on 3/30/2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;


import com.bailey.mobile.squadup.R;

public class CourtView extends ImageView implements View.OnTouchListener{
    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int dotRadius;

    public CourtView(Context context) {
        super(context);
        initCourtView();
    }

    public CourtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCourtView();
    }

    public CourtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCourtView();
    }

    public CourtView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initCourtView();
    }

    private void initCourtView()
    {
        this.setBackgroundResource(R.drawable.testcourt);
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        setOnTouchListener(this);
        setDotRadius(20);
        setColor(Color.GREEN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    public void setDotRadius(int r) {
        dotRadius = r;
        mPaint.setStrokeWidth((float) dotRadius);
    }

    public void setColor(int c) {
        mPaint.setColor(c);
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        CourtActivity activity = (CourtActivity) getContext();
        Resources res = getResources();

        int action = event.getActionMasked();
        float x = event.getX();
        float y = event.getY();

        Bitmap b = ((BitmapDrawable)this.getDrawable()).getBitmap();
        int currPixel = b.getPixel((int) x, (int) y);
        boolean isThree = isThreePointer(currPixel);

        boolean madeShot = ((RadioButton) activity.findViewById(R.id.madeShot)).isChecked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                activity.shotTaken(madeShot, isThree);
                if (madeShot)
                {
                    setColor(Color.GREEN);
                    if (isThree)
                    {
                        activity.updateInternalScores(true, res.getInteger(R.integer.threePointer));
                    }
                    else
                    {
                        activity.updateInternalScores(true, res.getInteger(R.integer.twoPointer));
                    }
                }
                else
                {
                    setColor(Color.RED);
                }

                activity.updateDatabase();
                mCanvas.drawCircle(x, y, dotRadius, mPaint);
                invalidate();
                break;
            default:
                break;

        }
        return false;
    }

    public boolean isThreePointer(int pixel)
    {
        int red = Color.red(pixel);
        int green = Color.green(pixel);
        int blue = Color.blue(pixel);

        if (red == 195 && green == 195 && blue == 195)
        {
            return true;
        }
        return false;
    }
}
