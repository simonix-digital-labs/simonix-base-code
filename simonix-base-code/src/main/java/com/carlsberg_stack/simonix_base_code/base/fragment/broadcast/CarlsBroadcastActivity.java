package com.carlsberg_stack.simonix_base_code.base.fragment.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.carlsberg_stack.simonix_base_code.base.fragment.CarlsFragmentActivity;
import com.carlsberg_stack.simonix_base_code.helper.BroadcastReceiverModel;
import com.carlsberg_stack.simonix_base_code.helper.CarlsLogger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CarlsBroadcastActivity extends CarlsFragmentActivity implements CarlsBroadcastCommunicator {

    private boolean regitrationAllowed = true;
    private Map<String, BroadcastReceiverModel> carls_broadcastReceiverMap = new HashMap<>();
    private Set<String> carls_onCreateList = new HashSet<>();
    private Set<String> carls_onStartList = new HashSet<>();
    private Set<String> carls_onResumeList = new HashSet<>();
    private Set<String> carls_onPauseList = new HashSet<>();
    private Set<String> carls_onStopList = new HashSet<>();
    private Set<String> carls_onDestroyList = new HashSet<>();

    protected abstract void carls_indexBroadcastReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carls_indexBroadcastReceiver();
        regitrationAllowed = false;
        carls_register(carls_onCreateList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        carls_register(carls_onStartList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carls_register(carls_onResumeList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        carls_unregister(carls_onPauseList);
    }

    @Override
    protected void onStop() {
        super.onStop();
        carls_unregister(carls_onStopList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carls_unregister(carls_onDestroyList);
        carls_broadcastReceiverMap = null;
        carls_onCreateList = null;
        carls_onStartList = null;
        carls_onResumeList = null;
        carls_onPauseList = null;
        carls_onStopList = null;
        carls_onDestroyList = null;
    }

    protected void carls_registerLocalBroadcastReceiver(RegisterBroadcastAction register, UnregisterBroadcastAction unregister, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        String name = broadcastReceiver.getClass().getName();
        if (carls_canRegister(name)) {
            carls_broadcastReceiverMap.put(name, BroadcastReceiverModel.getInstance(broadcastReceiver, intentFilter, register, unregister, true));
            carls_register(register, broadcastReceiver, intentFilter, name);
            carls_unregister(unregister, name);
            CarlsLogger.i("Registration Successful");
        }
    }

    protected void carls_registerBroadcastReceiver(RegisterBroadcastAction register, UnregisterBroadcastAction unregister, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        String name = broadcastReceiver.getClass().getName();
        if (carls_canRegister(name)) {
            carls_broadcastReceiverMap.put(name, BroadcastReceiverModel.getInstance(broadcastReceiver, intentFilter, register, unregister, false));
            carls_register(register, broadcastReceiver, intentFilter, name);
            carls_unregister(unregister, name);
            CarlsLogger.i("Registration Successful");
        }
    }


    /*register and unregister*/
    protected void carls_registerLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    protected void carls_unRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    protected void carls_registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        registerReceiver(broadcastReceiver, intentFilter);
    }

    protected void carls_unRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        unregisterReceiver(broadcastReceiver);
    }

    private boolean carls_canRegister(String name) {
        if (carls_broadcastReceiverMap.containsKey(name)) {
            CarlsLogger.w("Broadcast receiver is already added, Please add action in intenet filter");
            return false;
        } else if (!regitrationAllowed) {
            CarlsLogger.w("Can't Register. Please add broadcast receiver on carls_indexBroadcastReceiver() method");
            return false;
        }
        return true;
    }

    private void carls_register(RegisterBroadcastAction register, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String name) {
        switch (register) {
            case CARLS_ON_CREATE:
                carls_onCreateList.add(name);
                break;
            case CARLS_ON_START:
                carls_onStartList.add(name);
                break;
            case CARLS_ON_RESUME:
                carls_onResumeList.add(name);
                break;
        }
    }

    private void carls_register(Set<String> receivers) {
        if (receivers.isEmpty())
            return;
        BroadcastReceiverModel broadcastReceiverModel;
        for (String receiver :
                receivers) {
            broadcastReceiverModel = carls_broadcastReceiverMap.get(receiver);
            if (broadcastReceiverModel.isLocal())
                carls_registerLocalBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver(), broadcastReceiverModel.getIntentFilter());
            else
                carls_registerBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver(), broadcastReceiverModel.getIntentFilter());
        }
    }

    private void carls_unregister(UnregisterBroadcastAction unregister, String name) {
        switch (unregister) {
            case CARLS_ON_PAUSE:
                carls_onPauseList.add(name);
                break;
            case CARLS_ON_STOP:
                carls_onStopList.add(name);
                break;
            case CARLS_ON_DESTROY:
                carls_onDestroyList.add(name);
                break;
        }
    }

    private void carls_unregister(Set<String> receivers) {
        if (receivers.isEmpty())
            return;
        BroadcastReceiverModel broadcastReceiverModel;
        for (String receiver :
                receivers) {
            broadcastReceiverModel = carls_broadcastReceiverMap.get(receiver);
            if (broadcastReceiverModel.isLocal())
                carls_unRegisterLocalBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver());
            else
                carls_unRegisterBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver());
        }
    }

    public enum RegisterBroadcastAction {
        CARLS_ON_CREATE, CARLS_ON_START, CARLS_ON_RESUME
    }

    public enum UnregisterBroadcastAction {
        CARLS_ON_PAUSE, CARLS_ON_STOP, CARLS_ON_DESTROY
    }

    @Override
    public void frg_registerLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        carls_registerLocalBroadcastReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void frg_unRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        carls_unRegisterLocalBroadcastReceiver(broadcastReceiver);
    }

    @Override
    public void frg_registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        carls_registerBroadcastReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    public void frg_unRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        carls_unRegisterBroadcastReceiver(broadcastReceiver);
    }
}
