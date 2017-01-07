package com.huaweihackthon.yazhoujiang.pm25detector;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.huaweihackthon.yazhoujiang.pm25detector.CustomView.SemiWatch;
import com.huaweihackthon.yazhoujiang.pm25detector.CustomView.SocketStart;

public class MainActivity extends AppCompatActivity {

    private SemiWatch mSemiWatch;

    //private Pointer mPointer;

    private TextView mTextClock;

    private int colorPrimary = 0;

    private int colorPrimaryDark = 0;

    public MyHandler h ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h = new MyHandler(this);
        mSemiWatch = (SemiWatch) findViewById(R.id.semi_watch);
        //mPointer = (Pointer) findViewById(R.id.pointer_view);
        mTextClock = (TextView) findViewById(R.id.text_clock);
        mSemiWatch.setSweepAngle(converToDegree(0));
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
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("socket", "socket is running");
                SocketStart socketService = SocketStart.getInstance();
                socketService.mHandler = h;
                socketService.RunServer();
            }
        });
        th.start();
//        MyTask myTask = new MyTask();
//        myTask.execute(0);
    }

    private class MyTask extends AsyncTask<Integer, Integer, Integer> {

        MyTask() {

        }


        @Override
        protected Integer doInBackground(Integer... params) {

            //Log.d("socket", "socket is running");
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
        // Log.d("rate", number + " degrees");
        return number;
    }

    private void upDateView(float number) {
        float degree = converToDegree(number);

        //mPointer.rotate(degree);
        mTextClock.setText("PM 2.5: " + number);

        mSemiWatch.setSweepAngle(degree);

        int base = 255 - Color.red(colorPrimary);
        int baseDark = 255 - Color.red(colorPrimaryDark);

        int calculatedColor = (int) (number / 1000) * base + base;
        int calculatedColorDark = (int) (number / 1000) * baseDark + baseDark;


    }

    private static class MyHandler extends Handler {

        MainActivity mainActivity;
        MyHandler(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mainActivity.upDateView(msg.arg1);
        }
    }


}
