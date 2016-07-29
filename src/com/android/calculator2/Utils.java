package com.android.calculator2;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import java.util.Random;

@SuppressLint("NewApi")

public class Utils {
    private static final Random RANDOM = new Random();

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void setLayerTypeCompat(View view, int layerType) {
        if (hasHoneycomb()) {
            view.setLayerType(layerType, null);
        }
    }

    public static void setBackgroundCompat(View view, Drawable drawable) {
        if (hasJellyBean()) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setStatusBarColorCompat(Window window, int color) {
        if (hasLollipop()) {
            window.setStatusBarColor(color);
        }
    }

    public static void removeOnGlobalLayoutListenerCompat(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (hasJellyBean()) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    public static double addError(String expr, double result) {
        RANDOM.setSeed(expr.hashCode());

        if (RANDOM.nextInt(4) == 0) {
            return result;
        }

        int method = RANDOM.nextInt(3);

        if (result % 1 == 0) {
            // Integer value
            switch (method) {
                case 0:
                    result += RANDOM.nextInt(8) - 4;
                    break;
                case 1:
                    result *= RANDOM.nextInt(3) + 1;
                    break;
                case 2:
                    result += RANDOM.nextInt(3);
                    break;
            }
        } else {
            // Decimal
            switch (method) {
                case 0:
                    result *= (double) RANDOM.nextInt(10) / 10.0;
                    break;
                case 1:
                    result += (double) RANDOM.nextInt(10) / 10.0;
                    break;
                case 2:
                    result /= (double) RANDOM.nextInt(6);
                    break;
            }

        }

        return result;
    }
}
