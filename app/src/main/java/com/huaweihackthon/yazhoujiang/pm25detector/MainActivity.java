package com.huaweihackthon.yazhoujiang.pm25detector;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.IntegerRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextClock;
import android.widget.TextView;

import com.huaweihackthon.yazhoujiang.pm25detector.CustomView.Pointer;
import com.huaweihackthon.yazhoujiang.pm25detector.CustomView.SemiWatch;

public class MainActivity extends AppCompatActivity {

    private SemiWatch mSemiWatch;
    private Pointer mPointer;
    private TextView mTextClock;
    private Handler mHandler = new Handler();
    private int colorPrimary = 0;
    private int colorPrimaryDark = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSemiWatch = (SemiWatch) findViewById(R.id.semi_watch);
        mPointer = (Pointer) findViewById(R.id.pointer_view);
        mTextClock = (TextView) findViewById(R.id.text_clock);
        mSemiWatch.setSweepAngle(converToDegree(500));
        if (Build.VERSION.SDK_INT >= 23) {
            colorPrimary = Color.red(getResources().getColor(R.color.colorPrimary, null));
            colorPrimaryDark = Color.red(getResources().getColor(R.color.colorPrimaryDark, null));
        } else {
            colorPrimary = Color.red(getResources().getColor(R.color.colorPrimary));
            colorPrimaryDark = Color.red(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyTask myTask = new MyTask();
        myTask.execute(0);
    }

    private class MyTask extends AsyncTask<Integer, Integer, Integer> {

        MyTask() {

        }


        @Override
        protected Integer doInBackground(Integer... params) {
            for (int i = 0; i <= 1000; i++) {
                Log.d("data", "Data:" + i);
                publishProgress(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Log.d("data", e.getMessage());
                }
                if (i == 1000) {
                    i = 0;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            upDateView((float) values[0]);
        }
    }

    private float converToDegree(float rawData) {
        float number = 0;
        if (rawData < 0) {
            return 0f;
        }
        if (0 <= rawData && rawData <= 1000f) {
            float rawRate = rawData / 1000f;
            number = rawRate * 180f;

        }
        Log.d("rate", number + " degrees");
        return number;
    }

    private void upDateView(float number) {
        mTextClock.setText("PM 2.5: " + number);
        float degree = converToDegree(number);
        mSemiWatch.setSweepAngle(degree);

        int base = 255 - Color.red(colorPrimary);
        int baseDark = 255 - Color.red(colorPrimaryDark);

        int calculatedColor = (int) (number / 1000) * base + base;
        int calculatedColorDark = (int) (number / 1000) * baseDark + baseDark;

        //getWindow().setStatusBarColor(Color.rgb(Color.red(calculatedColorDark),Color.green(calculatedColorDark),Color.blue(calculatedColorDark)));
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            Log.d("action", "action");
            //ColorDrawable colorDrawable = new ColorDrawable(Color.rgb(Color.red(calculatedColor),Color.green(calculatedColor),Color.blue(calculatedColor)));
            //bar.setBackgroundDrawable(colorDrawable);
        }
        RotateAnimation animation = (RotateAnimation) AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        mPointer.startAnimation(animation);
    }


}
