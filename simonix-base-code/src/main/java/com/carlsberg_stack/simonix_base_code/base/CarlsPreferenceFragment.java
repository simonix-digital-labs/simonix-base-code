package com.carlsberg_stack.simonix_base_code.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;


public abstract class CarlsPreferenceFragment<T extends CarlsCommunicator> extends PreferenceFragmentCompat {

    protected T communicator;
    protected SharedPreferences defaultPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultPreference = PreferenceManager.getDefaultSharedPreferences(getContext());

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communicator = (T) context;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(getPreferencesFromResource(), rootKey);
    }

    protected abstract int getPreferencesFromResource();


}
