package com.huaweihackthon.yazhoujiang.pm25detector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.widget.TextClock;
import android.widget.TextView;

import com.huaweihackthon.yazhoujiang.pm25detector.CustomView.Pointer;
import com.huaweihackthon.yazhoujiang.pm25detector.CustomView.SemiWatch;

public class MainActivity extends AppCompatActivity {

    private SemiWatch mSemiWatch;

    private Pointer mPointer;

    private TextView mTextClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSemiWatch = (SemiWatch) findViewById(R.id.semi_watch);
        mPointer = (Pointer) findViewById(R.id.pointer_view);
        mTextClock = (TextView) findViewById(R.id.text_clock);
        mTextClock.setText("PM 2.5: "+0);
        mSemiWatch.setSweepAngle(converToDegree(500));
    }

    private float converToDegree(float rawData){
        float number = 0;
        if(rawData<0){
            return 0f;
        }
        if(0<=rawData && rawData<=1000f){
            float rawRate = rawData/1000f;
            number = rawRate*180f;

        }
        Log.d("rate",number+" degrees");
        return number;
    }

    private void upDateView(float number){
        mTextClock.setText("PM 2.5: "+number);
        mSemiWatch.setSweepAngle(converToDegree(number));
    }



}
