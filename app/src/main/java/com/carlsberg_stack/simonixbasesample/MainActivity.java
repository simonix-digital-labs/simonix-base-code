package com.carlsberg_stack.simonixbasesample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.carlsberg_stack.simonix_base_code.base.kisok.CarlsKisokActivity;
import com.carlsberg_stack.simonix_base_code.helper.ToastMessage;


public class MainActivity extends CarlsKisokActivity implements MainFragmentInterface {

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Override
    public void call() {
        ToastMessage.makeDebugToast(getApplicationContext(), "call").show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String carl_getPackageName() {
        return getPackageName();
    }

    @Override
    protected int carls_getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void carls_bindViews() {

    }

    public void toast(View view) {
        switch (view.getId()) {
            case R.id.simple_toast:
                ToastMessage.makeSimpleToast(getApplicationContext(), getString(R.string.app_name)).show();
                break;
            case R.id.app_toast:
                ToastMessage.makeAppToast(getApplicationContext(), getString(R.string.app_name)).show();
                break;
            case R.id.warning_toast:
                ToastMessage.makeWarningToast(getApplicationContext(), getString(R.string.app_name)).show();
                break;
            case R.id.error_toast:
                ToastMessage.makeErrorToast(getApplicationContext(), getString(R.string.app_name)).show();
                break;
            case R.id.success_toast:
                ToastMessage.makeSuccessToast(getApplicationContext(), getString(R.string.app_name)).show();
                break;
            case R.id.info_toast:
                ToastMessage.makeInfoToast(getApplicationContext(), getString(R.string.app_name)).show();
                break;
            case R.id.show_list:
                carls_showListDialog(R.array.test, null);
                break;
            case R.id.show_alert:
                carls_showAlert(R.string.app_name);
                break;
            case R.id.show_confirm:
                carls_showConfirmDialog(R.string.app_name, null);
                break;
        }
    }

    public void perform(View view) {
        switch (view.getId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }

}
