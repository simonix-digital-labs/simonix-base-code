package com.carlsberg_stack.simonix_base_code.base;

/**
 * Created by kepler on 20/3/18.
 */

public interface CarlsMvp {

    /**
     * Represents a View in CarlsMvp.
     */
    interface BaseView {

    }

    /**
     * Represents a Presenter in CarlsMvp.
     * <p>
     * By default, it can attach and detach its View.
     *
     * @param <T> The type of the View that the Presenter will handle
     */
    interface BasePresenter<T extends BaseView> {

        void attachView(T view);

        void detachView();
    }
}
