package com.huaweihackthon.yazhoujiang.pm25detector.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.huaweihackthon.yazhoujiang.pm25detector.DpUtil;

/**
 * Created by yazhoujiang on 07/01/2017.
 */

public class SemiWatch extends View {

    private Paint mPaint = new Paint();
    private Paint mPaintCircle = new Paint();
    private Paint mPaintPointer = new Paint();

    private float degree =0;
    private int number =0;

    private RectF mRectF = new RectF(0,0,0,0);

    private void init() {
        // draw paint
        mPaintPointer.setStyle(Paint.Style.FILL);
        mPaintPointer.setColor(Color.WHITE);
        //drawArc
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DpUtil.dpToPix(getContext(),5));
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        //circle
        mPaintCircle.setColor(Color.WHITE);
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
        //Log.d("view", "measuring semi watch");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d("view", "drawing semi watch" + getLeft() + " " + getTop() + " " + getRight() + " " + getBottom() + " x:" + getX() + " y:" + getY());
        // draw arc
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        canvas.drawArc(getPaddingLeft(), getPaddingTop(), getMeasuredWidth()-getPaddingRight(), getMeasuredHeight(), 180,degree,false, mPaint);
        //draw circle
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 10, mPaintCircle);

    }

    public void setSweepAngle(float degree){
        this.degree = degree;
        invalidate();
        requestLayout();
    }



}
