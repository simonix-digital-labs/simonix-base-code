package com.carlsberg_stack.simonix_base_code.base.kisok;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.carlsberg_stack.simonix_base_code.R;
import com.carlsberg_stack.simonix_base_code.helper.CarlsLogger;

import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.CARLS_ACTION.ACTION_ADMIN_RECEIVER;

public class CarlsAdminReceiver extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent) {
//        Toast.makeText(context, context.getString(R.string.device_admin_enabled), Toast.LENGTH_SHORT).show();
        CarlsLogger.e("onEnabled");
        Intent intent1 = new Intent(ACTION_ADMIN_RECEIVER);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return context.getString(R.string.carls_device_admin_warning);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
//        Toast.makeText(context, context.getString(R.string.device_admin_disabled), Toast.LENGTH_SHORT).show();
        CarlsLogger.e("onDisabled");
    }

    @Override
    public void onLockTaskModeEntering(Context context, Intent intent, String pkg) {
        CarlsLogger.e("onLockTaskModeEntering");
//        Toast.makeText(context, context.getString(R.string.kiosk_mode_enabled), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLockTaskModeExiting(Context context, Intent intent) {
        CarlsLogger.e("onLockTaskModeExiting");
//        Toast.makeText(context, context.getString(R.string.kiosk_mode_disabled), Toast.LENGTH_SHORT).show();
    }
}