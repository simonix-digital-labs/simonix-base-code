package com.carlsberg_stack.simonix_base_code.pattern.mvp;

import android.os.Bundle;

import com.carlsberg_stack.simonix_base_code.base.CarlsActivity;
import com.carlsberg_stack.simonix_base_code.base.CarlsMvp;

public abstract class CarlsMvpActivity<T extends CarlsMvp.BasePresenter> extends CarlsActivity implements CarlsMvp.BaseView {

    /**
     * The Presenter attached to this View
     */
    protected T presenter;

    /**
     * Must be overriden to define the Presenter used by this activity.
     *
     * @return The presenter that will be used by this View.
     */
    protected abstract T createPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.presenter = this.createPresenter();
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (this.presenter != null) {
            this.presenter.detachView();
        }
        super.onDestroy();
    }
}
