package com.carlsberg_stack.simonix_base_code.base.kisok;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.carlsberg_stack.simonix_base_code.R;
import com.carlsberg_stack.simonix_base_code.base.CarlsActivity;
import com.carlsberg_stack.simonix_base_code.helper.CarlsLogger;

public abstract class CarlsKisokActivity extends CarlsActivity {

    private DevicePolicyManager mDpm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        if (defaultPreference.getBoolean(getString(R.string.carls_kisok_mode), true)) {
            carls_enter_kisok_mode(carl_getPackageName());
        }
    }

    private void carls_enter_kisok_mode(String package_name) {
        try {
            if (package_name == null || !mDpm.isLockTaskPermitted(package_name))
                return;
            startLockTask();
        } catch (Exception e) {
            CarlsLogger.w(e.getMessage());
            // TODO: Log and handle appropriately
        }
    }

    protected abstract String carl_getPackageName();
}
