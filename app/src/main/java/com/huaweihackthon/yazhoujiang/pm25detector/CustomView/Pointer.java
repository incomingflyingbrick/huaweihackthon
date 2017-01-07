package com.huaweihackthon.yazhoujiang.pm25detector.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.huaweihackthon.yazhoujiang.pm25detector.DpUtil;

/**
 * Created by yazhoujiang on 07/01/2017.
 */

public class Pointer extends View {
    private Paint mPaint = new Paint();

    public Pointer(Context context) {
        super(context);
        init();
    }

    public Pointer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pointer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0,0,getMeasuredWidth(),getMeasuredHeight(), DpUtil.dpToPix(getContext(),5),DpUtil.dpToPix(getContext(),5),mPaint);
    }
}
