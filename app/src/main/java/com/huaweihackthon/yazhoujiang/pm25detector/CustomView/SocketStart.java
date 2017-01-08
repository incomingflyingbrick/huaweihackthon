package com.huaweihackthon.yazhoujiang.pm25detector.CustomView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.List;

public class SocketStart {

    public static SocketStart start = new SocketStart();

    public static Handler mHandler = null;

    private SocketStart() {

    }

    public static synchronized SocketStart getInstance() {
        if (start == null) {
            start = new SocketStart();
        }
        return start;
    }

    public void RunServer() {

        ServerSocket server = null;
        try {

            server = new ServerSocket();
            String hostname = getIPAddress(true);
            server.bind(new InetSocketAddress(hostname, 65534));
        } catch (IOException e) {
        }
        while (true) {

            Socket socket = null;
            try {
                socket = server.accept();
                Log.d("new",socket.toString());
                MsgThread mgThread = new MsgThread(socket, mHandler);
                Log.d("new",mgThread.toString());
                mgThread.start();
            } catch (Exception e) {
                //System.out.println("Error." + e);
            }

        }
    }


    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

}
