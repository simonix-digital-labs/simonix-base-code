package com.carlsberg_stack.simonix_base_code.helper;

import android.util.Log;

import com.carlsberg_stack.simonix_base_code.BuildConfig;


public class CarlsLogger {

    private final static String TAG = "CarlsLogger";

    /*E*/
    public static void e(String error) {
        e(TAG, error, null);
    }

    public static void e(String tag, String error) {
        e(tag, error, null);
    }

    public static void e(String tag, String error, Exception e) {
        if (BuildConfig.DEBUG)
            Log.e(tag, error, e);
    }

    /*D*/
    public static void d(String error) {
        d(TAG, error, null);
    }

    public static void d(String tag, String error) {
        d(tag, error, null);
    }

    public static void d(String tag, String error, Exception e) {
        if (BuildConfig.DEBUG)
            Log.d(tag, error, e);
    }

    /*W*/
    public static void w(String error) {
        w(TAG, error, null);
    }

    public static void w(String tag, String error) {
        w(tag, error, null);
    }

    public static void w(String tag, String error, Exception e) {
        if (BuildConfig.DEBUG)
            Log.w(tag, error, e);
    }

    /*I*/
    public static void i(String error) {
        i(TAG, error, null);
    }

    public static void i(String tag, String error) {
        i(tag, error, null);
    }

    public static void i(String tag, String error, Exception e) {
        if (BuildConfig.DEBUG)
            Log.i(tag, error, e);
    }

    /*V*/
    public static void v(String error) {
        v(TAG, error, null);
    }

    public static void v(String tag, String error) {
        v(tag, error, null);
    }

    public static void v(String tag, String error, Exception e) {
        if (BuildConfig.DEBUG)
            Log.v(tag, error, e);
    }

    /*PRINT*/
    public static void print(Exception e) {
        if (BuildConfig.DEBUG && e != null)
            e.printStackTrace();
    }

    public static void print(Throwable exception) {
        if (BuildConfig.DEBUG && exception != null)
            exception.printStackTrace();
    }

}
