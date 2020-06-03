package com.carlsberg_stack.simonix_base_code.pattern.mvvm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.carlsberg_stack.simonix_base_code.base.CarlsCommunicator;
import com.carlsberg_stack.simonix_base_code.base.CarlsFragment;
import com.carlsberg_stack.simonix_base_code.base.CarlsViewModel;


public abstract class CarlsMvvmFragment<T extends CarlsViewModel, C extends CarlsCommunicator> extends CarlsFragment<C> {


    protected T mvvm;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mvvm = createViewModel();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private T createViewModel() {
        return ViewModelProviders.of(this).get(createViewModelClass());
    }

    protected abstract Class<T> createViewModelClass();


    @Override
    public void onDetach() {
        mvvm = null;
        super.onDetach();
    }


}
