package com.carlsberg_stack.simonix_base_code.pattern.mvvm;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.carlsberg_stack.simonix_base_code.base.CarlsViewModel;
import com.carlsberg_stack.simonix_base_code.base.fragment.broadcast.CarlsBroadcastActivity;


public abstract class CarlsMvvmBroadcastActivity<T extends CarlsViewModel> extends CarlsBroadcastActivity {

    protected T mvvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mvvm = createViewModel();
        super.onCreate(savedInstanceState);
    }

    private T createViewModel() {
        return ViewModelProviders.of(this).get(createViewModelClass());
    }

    protected abstract Class<T> createViewModelClass();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
