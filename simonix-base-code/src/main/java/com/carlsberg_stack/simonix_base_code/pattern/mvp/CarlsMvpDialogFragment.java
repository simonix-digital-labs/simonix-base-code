package com.carlsberg_stack.simonix_base_code.pattern.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.carlsberg_stack.simonix_base_code.base.CarlsCommunicator;
import com.carlsberg_stack.simonix_base_code.base.CarlsDialogFragment;
import com.carlsberg_stack.simonix_base_code.base.CarlsMvp;


public abstract class CarlsMvpDialogFragment<T extends CarlsMvp.BasePresenter, C extends CarlsCommunicator> extends CarlsDialogFragment<C> implements CarlsMvp.BaseView {

    protected T presenter;

    protected abstract T createPresenter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = this.createPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }
    }

    protected abstract Class<T> createViewModelClass();

    @Override
    public void onDetach() {
        if (this.presenter != null) {
            this.presenter.detachView();
        }
        super.onDetach();

    }
}
