package com.huaweihackthon.yazhoujiang.pm25detector.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yazhoujiang on 07/01/2017.
 */

public class SemiWatch extends View {

    private Paint mPaint = new Paint();
    private Paint mPaintCircle = new Paint();
    private int degree =0;

    private void init() {

        //drawArc
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        //circle
        mPaintCircle.setColor(Color.BLACK);
        mPaintCircle.setStyle(Paint.Style.FILL);
    }

    public SemiWatch(Context context) {
        super(context);
        init();
    }

    public SemiWatch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SemiWatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        Log.d("view", "measureing semi watch");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("view", "drawing semi watch" + getLeft() + " " + getTop() + " " + getRight() + " " + getBottom() + " x:" + getX() + " y:" + getY());
        canvas.drawArc(getLeft(), getTop(), getRight(), getTop()+getMeasuredHeight()/2, 180, 45,false, mPaint);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 10, mPaintCircle);
    }

    public void setSweepAngle(int degree){

    }

}
