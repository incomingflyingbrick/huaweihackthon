package com.huaweihackthon.yazhoujiang.pm25detector;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by yazhoujiang on 07/01/2017.
 */

public class DpUtil {
    public static float dpToPix(Context context, int dp){
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }


}
