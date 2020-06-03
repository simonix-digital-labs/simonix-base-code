package com.carlsberg_stack.simonix_base_code.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.carlsberg_stack.simonix_base_code.R;
import com.carlsberg_stack.simonix_base_code.helper.ToastMessage;

import java.util.List;

import static com.carlsberg_stack.simonix_base_code.helper.CarlsConstant.CARLS_NONE;

public abstract class CarlsActivity extends AppCompatActivity implements CarlsCommunicator {


    protected SharedPreferences defaultPreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carls_configure();
        setContentView(carls_getContentView());
        carls_bindViews();
        defaultPreference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    protected void carls_configure() {

    }

    protected abstract int carls_getContentView();

    protected abstract void carls_bindViews();

    /*activities*/
    protected void carls_startActivity(Class<? extends CarlsActivity> activityClass) {
        carls_startActivity(activityClass, null, CARLS_NONE);
    }

    protected void carls_startActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle) {
        carls_startActivity(activityClass, bundle, CARLS_NONE);
    }

    protected void carls_startActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags) {
        Intent intent = new Intent(this, activityClass);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /*fragments*/
    protected void carls_replaceFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment).commit();
    }

    protected void carls_replaceFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, tag).addToBackStack(tag).commit();
    }

    protected void carls_addFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).addToBackStack(tag).commit();
    }

    protected void carls_addFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment) {
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).commit();
    }

    protected void carls_showDialogFragment(@NonNull CarlsDialogFragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(tag);
        fragment.show(ft, tag);
    }

    protected void carls_showDialogFragment(@NonNull CarlsDialogFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        fragment.show(ft, fragment.getClass().getName());
    }

    /*without style*/
    protected void carls_showDialog(boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carls_showDialog(CARLS_NONE, isCancelable, title, message, postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    protected void carls_showDialog(boolean isCancelable, int title, String message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carls_showDialog(CARLS_NONE, isCancelable, title, message, postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    /*with style*/
    protected void carls_showDialog(int style, boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carls_showDialog(style, isCancelable, title, getString(message), postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    protected void carls_showDialog(int style, boolean isCancelable, int title, String message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(this, style == CARLS_NONE ? R.style.Carls_Appearance_App_Dialog : style)
                .setCancelable(isCancelable)
                .setTitle(title == CARLS_NONE ? R.string.carls_alert : title)
                .setMessage(message)
                .setNegativeButton(negativeTextId, negativeListener)
                .setPositiveButton(postiveTextId, positiveListener)
                .create().show();
    }


    /*Confirm Dialog*/
    protected void carls_showConfirmDialog(int message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog(int message, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog(int style, int message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog_(int style, int message, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog(String message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog(String message, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog(int style, String message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carls_showConfirmDialog_(int style, String message, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    /*alerts*/
    protected void carls_showAlert(int title, int message) {
        carls_showDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert(int message) {
        carls_showDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert(int style, int title, int message) {
        carls_showDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert_(int style, int message) {
        carls_showDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert(int title, String message) {
        carls_showDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert(String message) {
        carls_showDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert(int style, int title, String message) {
        carls_showDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carls_showAlert_(int style, String message) {
        carls_showDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    /*list dialog*/
    protected void carls_showListDialog(int items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(CARLS_NONE, true, CARLS_NONE, items, listener);
    }

    protected void carls_showListDialog(int style, int items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(style, true, CARLS_NONE, items, listener);
    }

    protected <T> void carls_showListDialog(List<T> items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(CARLS_NONE, CARLS_NONE, true, CARLS_NONE, items, listener);
    }

    protected <T> void carls_showListDialog(int style, List<T> items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(style, CARLS_NONE, true, CARLS_NONE, items, listener);
    }

    protected <T> void carls_showListDialog(int style, int resource, boolean isCancelable, int title, List<T> items, DialogInterface.OnClickListener listener) {
        final ArrayAdapter<T> arrayAdapter = new ArrayAdapter<>(this, resource == CARLS_NONE ? R.layout.carls_textview : resource, items);
        new AlertDialog.Builder(this, style == CARLS_NONE ? R.style.Carls_Appearance_App_Dialog : style)
                .setCancelable(isCancelable)
                .setTitle(title == CARLS_NONE ? R.string.carls_select : title)
                .setAdapter(arrayAdapter, listener)
                .create().show();
    }

    protected void carls_showListDialog(int style, boolean isCancelable, int title, int items, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this, style == CARLS_NONE ? R.style.Carls_Appearance_App_Dialog : style)
                .setCancelable(isCancelable)
                .setTitle(title == CARLS_NONE ? R.string.carls_select : title)
                .setItems(items, listener)
                .create().show();
    }

    /*toast*/
    protected void carls_showToast(String msg) {
        ToastMessage.makeSimpleToast(getApplicationContext(), msg);
    }

    protected void carls_showToast(int msg) {
        carls_showToast(getString(msg));
    }

    /*network connectivity*/
    protected boolean isInternetAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    @Override
    public void frg_startActivity(Class<? extends CarlsActivity> activityClass) {
        carls_startActivity(activityClass);
    }

    @Override
    public void frg_startActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle) {
        carls_startActivity(activityClass, bundle);
    }

    @Override
    public void frg_startActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags) {
        carls_startActivity(activityClass, bundle, flags);
    }

    @Override
    public void frg_showToast(String msg) {
        carls_showToast(msg);
    }

    @Override
    public void frg_showToast(int msg) {
        carls_showToast(msg);
    }

    @Override
    public boolean frg_isInternetAvailable() {
        return isInternetAvailable();
    }

    @Override
    public void frg_showListDialog(int items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(items, listener);
    }

    @Override
    public void frg_showListDialog(int style, int items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(style, items, listener);
    }

    @Override
    public <T> void frg_showListDialog(List<T> items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(items, listener);
    }

    @Override
    public <T> void frg_showListDialog(int style, List<T> items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(style, items, listener);
    }

    @Override
    public <T> void frg_showListDialog(int style, int resource, boolean isCancelable, int title, List<T> items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(style, resource, isCancelable, title, items, listener);
    }

    @Override
    public void frg_showListDialog(int style, boolean isCancelable, int title, int items, DialogInterface.OnClickListener listener) {
        carls_showListDialog(style, isCancelable, title, items, listener);
    }

    @Override
    public void frg_showDialog(boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carls_showDialog(CARLS_NONE, isCancelable, title, getString(message), postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    @Override
    public void frg_showDialog(int style, boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carls_showDialog(style, isCancelable, title, getString(message), postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    @Override
    public void frg_showConfirmDialog(int message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog(int message, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog(int style, int message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog_(int style, int message, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog(String message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog(String message, DialogInterface.OnClickListener listener) {
        carls_showDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog(int style, String message, int textId, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showConfirmDialog_(int style, String message, DialogInterface.OnClickListener listener) {
        carls_showDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert(int title, int message) {
        carls_showDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert(int message) {
        carls_showDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert(int style, int title, int message) {
        carls_showDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert_(int style, int message) {
        carls_showDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert(int title, String message) {
        carls_showDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert(String message) {
        carls_showDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert(int style, int title, String message) {
        carls_showDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showAlert_(int style, String message) {
        carls_showDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frg_showDialogFragment(@NonNull CarlsDialogFragment fragment, String tag) {
        carls_showDialogFragment(fragment, tag);
    }

    @Override
    public void frg_showDialogFragment(@NonNull CarlsDialogFragment fragment) {
        carls_showDialogFragment(fragment);
    }

    @Override
    public void setFragmentTitle(String title) {

    }

    @Override
    public void setFragmentTitle(int title) {

    }
}
