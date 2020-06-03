package com.carlsberg_stack.simonix_base_code.helper;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.carlsberg_stack.simonix_base_code.base.fragment.broadcast.CarlsBroadcastActivity;

public class BroadcastReceiverModel {
    private boolean local;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private CarlsBroadcastActivity.RegisterBroadcastAction registerAction;
    private CarlsBroadcastActivity.UnregisterBroadcastAction unRegisterAction;

    private BroadcastReceiverModel(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, CarlsBroadcastActivity.RegisterBroadcastAction registerAction, CarlsBroadcastActivity.UnregisterBroadcastAction unRegisterAction, boolean local) {
        this.broadcastReceiver = broadcastReceiver;
        this.intentFilter = intentFilter;
        this.registerAction = registerAction;
        this.unRegisterAction = unRegisterAction;
        this.local = local;
    }

    private BroadcastReceiverModel() {

    }

    public static BroadcastReceiverModel getInstance(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, CarlsBroadcastActivity.RegisterBroadcastAction registerAction, CarlsBroadcastActivity.UnregisterBroadcastAction unRegisterAction, Boolean local) {
        return new BroadcastReceiverModel(broadcastReceiver, intentFilter, registerAction, unRegisterAction, local);
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return broadcastReceiver;
    }

    public IntentFilter getIntentFilter() {
        return intentFilter;
    }

    public CarlsBroadcastActivity.RegisterBroadcastAction getRegisterAction() {
        return registerAction;
    }

    public CarlsBroadcastActivity.UnregisterBroadcastAction getUnRegisterAction() {
        return unRegisterAction;
    }

    public boolean isLocal() {
        return local;
    }
}
