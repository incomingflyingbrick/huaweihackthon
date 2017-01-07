package com.huaweihackthon.yazhoujiang.pm25detector.CustomView;

import android.content.Context;
import android.graphics.Canvas;
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

    private void init() {
        if (Build.VERSION.SDK_INT >= 23) {
            mPaint.setColor(getResources().getColor(android.R.color.holo_red_dark, null));
        } else {
            mPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
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
        Log.d("view", "drawing semi watch"+getLeft()+" "+getTop()+" "+getRight()+" "+getBottom()+" x:"+getX()+" y:"+getY());
        canvas.drawArc(getLeft(),getTop(),getRight(),getBottom(),0,180,true,mPaint);
    }

}
