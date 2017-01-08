package com.huaweihackthon.yazhoujiang.pm25detector.CustomView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by yazhoujiang on 07/01/2017.
 */

public class MsgThread extends Thread {
    Handler h;
    private Socket s = null;

    public MsgThread(Socket s, Handler handler) {
        h = handler;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                Log.d("receiver",line);
                float data = Float.parseFloat(line);
                Message msg = Message.obtain();
                msg.arg1 = (int) data;
                Bundle b = new Bundle();
                b.putLong("time", System.currentTimeMillis());
                msg.setData(b);
                if (h != null) {
                    if (data >= 0 && data <= 1000) {
                        Log.d("msg", data + " for post");
                        h.sendMessage(msg);
                    }
                }
            }
        } catch (IOException e) {

        }

    }
}
