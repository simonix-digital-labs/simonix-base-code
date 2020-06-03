package com.carlsberg_stack.simonix_base_code.base.kisok;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

import com.carlsberg_stack.simonix_base_code.R;
import com.carlsberg_stack.simonix_base_code.base.CarlsPreferenceFragment;
import com.carlsberg_stack.simonix_base_code.helper.CarlsLogger;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;
import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.CARLS_ACTION.ACTION_ADMIN_RECEIVER;

public class CarlsKisokSettingFragment extends CarlsPreferenceFragment implements Preference.OnPreferenceClickListener {


    private DevicePolicyManager mDpm;
    private ComponentName deviceAdminComponent;
    private String package_name;

    public static Fragment getInstance(Bundle bundle) {
        CarlsKisokSettingFragment carlsKisokSettingFragment = new CarlsKisokSettingFragment();
        carlsKisokSettingFragment.setArguments(bundle);
        return carlsKisokSettingFragment;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            carls_setupKisokMode();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTheme(R.style.Appearance_App_Setting_Theme);
        package_name = getArguments().getString(PACKAGE_NAME, null);
        mDpm = (DevicePolicyManager) getActivity().getSystemService(Context.DEVICE_POLICY_SERVICE);
        deviceAdminComponent = new ComponentName(getActivity(), CarlsAdminReceiver.class);
        carls_setupKisokMode();
    }


    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter(ACTION_ADMIN_RECEIVER));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Preference carls_remove_device_owner = findPreference(getString(R.string.carls_remove_device_owner));
        Preference carls_change_passcode = findPreference(getString(R.string.carls_change_passcode));
        SwitchPreference carls_kisok_mode = findPreference(getString(R.string.carls_kisok_mode));
        carls_remove_device_owner.setOnPreferenceClickListener(this);
        carls_change_passcode.setOnPreferenceClickListener(this);
        carls_kisok_mode.setOnPreferenceClickListener(this);
        carls_kisok_mode.setSummary("For enable kisok mode -> adb shell dpm set-device-owner " + package_name + "/"+CarlsAdminReceiver.class.getName());
    }

    @Override
    protected int getPreferencesFromResource() {
        return R.xml.carls_kisok_settings;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "Remove Device Ownership":
                communicator.frg_showConfirmDialog(R.string.carls_remove_device_owner_msg, R.string.carls_remove_device_owner, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mDpm.isAdminActive(deviceAdminComponent))
                            return;
                        mDpm.clearDeviceOwnerApp(getActivity().getPackageName());
                    }
                });
                return true;
            case "Kisok Mode":
                ((SwitchPreference) preference).setChecked(carls_enter_kisok_mode(((SwitchPreference) preference).isChecked()));
                return true;
            case "Change Passcode":
                communicator.frg_showDialogFragment(new CarlsChangePasscodeFragment());
                return true;

        }
        return false;
    }

    private void carls_setupKisokMode() {
        if (mDpm.isDeviceOwnerApp(package_name)) {
            if (!mDpm.isLockTaskPermitted(package_name))
                mDpm.setLockTaskPackages(deviceAdminComponent, new String[]{package_name});
        } else {
            CarlsLogger.w("It is not owner app");
        }
    }

    private boolean carls_enter_kisok_mode(boolean task) {
        try {
            if (!mDpm.isLockTaskPermitted(package_name))
                return false;
            if (task) {
                getActivity().startLockTask();
                return true;
            } else {
                getActivity().stopLockTask();
            }
        } catch (Exception e) {
            CarlsLogger.w(e.getMessage());
            // TODO: Log and handle appropriately
        }
        return false;

    }
}
