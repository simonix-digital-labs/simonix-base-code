package com.carlsberg_stack.simonix_base_code.base.fragment.broadcast;


import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.carlsberg_stack.simonix_base_code.base.fragment.CarlsFragmentCommunicator;

public interface CarlsBroadcastCommunicator extends CarlsFragmentCommunicator {
    void frg_registerLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void frg_unRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver);

    void frg_registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void frg_unRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver);
}