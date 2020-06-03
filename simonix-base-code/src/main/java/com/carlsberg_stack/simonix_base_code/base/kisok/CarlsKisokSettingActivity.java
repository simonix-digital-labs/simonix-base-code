package com.carlsberg_stack.simonix_base_code.base.kisok;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.carlsberg_stack.simonix_base_code.base.fragment.CarlsFragmentActivity;
import com.carlsberg_stack.simonix_base_code.base.fragment.CarlsFragmentCommunicator;
import com.carlsberg_stack.simonix_base_code.helper.ToastMessage;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;
import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.CARLS_NONE;
import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.DEFAULT_PASSCODE;
import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.PARAM_INVALID_USER;
import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.PARAM_PASSCODE;

interface PasscodeCommonHandler extends CarlsFragmentCommunicator {
    void exit();
}

interface SecurityHandler extends PasscodeCommonHandler {
    boolean checkPasscode(String passcode);

    void onPasscodeError();

    void forgotPasscode();

    void onPasscodeSucceed();

    void updateAdminAuthorization(boolean status);

    boolean isValidAdmin();
}

interface ChangePasscodeHandler extends PasscodeCommonHandler {
    void changePasscode(String passcode);
}

public abstract class CarlsKisokSettingActivity extends CarlsFragmentActivity implements SecurityHandler, ChangePasscodeHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carls_showDialogFragment(new CarlsPasscodeFragment());
    }

    protected abstract String carls_getPackageName();

    @Override
    public boolean checkPasscode(String passcode) {
        return passcode.equals(defaultPreference.getString(PARAM_PASSCODE, DEFAULT_PASSCODE));
    }

    @Override
    public boolean isValidAdmin() {
        return !defaultPreference.getBoolean(PARAM_INVALID_USER, false);
    }

    @Override
    public void changePasscode(String passcode) {
        defaultPreference.edit().putString(PARAM_PASSCODE, passcode).apply();
        ToastMessage.makeSuccessToast(getApplicationContext(), "Passcode Changed").show();
    }

    @Override
    public void forgotPasscode() {
        defaultPreference.edit().remove(PARAM_PASSCODE).remove(PARAM_INVALID_USER).apply();
    }

    @Override
    public void onPasscodeSucceed() {
        if (carls_getPackageName() == null || carls_getPackageName().isEmpty())
            return;
        Bundle bundle = new Bundle();
        bundle.putString(PACKAGE_NAME, carls_getPackageName());
        getSupportFragmentManager().beginTransaction().replace(carls_getContainerViewId(CARLS_NONE), CarlsKisokSettingFragment.getInstance(bundle)).commit();
    }

    @Override
    public void updateAdminAuthorization(boolean status) {
        defaultPreference.edit().putBoolean(PARAM_INVALID_USER, status).apply();
    }

    @Override
    public void onPasscodeError() {

    }

    @Override
    public void exit() {
        onBackPressed();
    }
}
