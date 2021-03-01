package com.study.homeui.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {

    public static void showGreenToast(Context context, CharSequence message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundResource(android.R.color.holo_green_light);
        toast.setView(view);
        toast.show();
    }

    public static void showRedTextToast(Context context, CharSequence message) {
        Toast toast = new Toast(context);
        TextView view = new TextView(context);
        view.setBackgroundResource(android.R.color.holo_green_light);
        view.setTextColor(Color.RED);
        view.setText(message);
        view.setPadding(10, 10, 10, 10);
        toast.setGravity(Gravity.CENTER, 0, 40);
        toast.setView(view);
        toast.show();
    }
}
