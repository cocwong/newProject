package com.example.cocwong.test.util;

import android.content.Context;
import android.util.TypedValue;

public class DensityHelper {
    public static float dp2Px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
