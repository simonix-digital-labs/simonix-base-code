package com.carlsberg_stack.simonix_base_code.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.carlsberg_stack.simonix_base_code.R;

public class ToastMessage {

    private static Toast makeText(@NonNull Context context, int background_resource, int color_resource, @NonNull CharSequence text, int duration) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.carls_toast, null);
        TextView textview = layout.findViewById(R.id.text);
        textview.setText(text);
        textview.setBackgroundResource(background_resource);
        textview.setTextColor(context.getColor(color_resource));
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(layout);
        return toast;
    }

    public static Toast makeErrorToast(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_red, R.color.carls_color_red, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeWarningToast(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_yellow, R.color.carls_color_yellow, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeSuccessToast(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_green, R.color.carls_color_green, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeDebugToast(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_black, R.color.carls_color_chocorol, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeInfoToast(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_blue, R.color.carls_color_blue, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeSimpleToast(@NonNull Context context, @NonNull CharSequence text) {
        return Toast.makeText(context, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeAppToast(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_primary, R.color.colorAccent, text, Toast.LENGTH_SHORT);
    }

    public static Toast makeAppToastInverse(@NonNull Context context, @NonNull CharSequence text) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_accent, R.color.colorPrimary, text, Toast.LENGTH_SHORT);
    }

    /**/
    public static Toast makeErrorToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_red, R.color.carls_color_red, text, duration);
    }

    public static Toast makeWarningToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_yellow, R.color.carls_color_yellow, text, duration);
    }

    public static Toast makeSuccessToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_green, R.color.carls_color_green, text, duration);
    }

    public static Toast makeDebugToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_black, R.color.carls_color_chocorol, text, duration);
    }

    public static Toast makeInfoToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_trans_stroke_blue, R.color.carls_color_blue, text, duration);
    }

    public static Toast makeSimpleToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return Toast.makeText(context, text, duration);
    }

    public static Toast makeAppToast(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_primary, R.color.colorAccent, text, duration);
    }

    public static Toast makeAppToastInverse(@NonNull Context context, @NonNull CharSequence text, int duration) {
        return makeText(context, R.drawable.carls_rect_with_corner_fill_accent, R.color.colorPrimary, text, duration);
    }
}
