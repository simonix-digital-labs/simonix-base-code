package com.carlsberg_stack.simonix_base_code.base.fragment;

import androidx.annotation.NonNull;

import com.carlsberg_stack.simonix_base_code.base.CarlsActivity;
import com.carlsberg_stack.simonix_base_code.base.CarlsFragment;


public abstract class CarlsFragmentActivity extends CarlsActivity implements CarlsFragmentCommunicator {

    @Override
    public void frg_replaceFragment(@NonNull CarlsFragment fragment) {
        carls_replaceFragment(carls_getContainerViewId(fragment.getId()), fragment);
    }

    protected abstract int carls_getContainerViewId(int id);

    @Override
    public void frg_replaceFragment(@NonNull CarlsFragment fragment, String tag) {
        carls_replaceFragment(carls_getContainerViewId(fragment.getId()), fragment, tag);
    }

    @Override
    public void frg_addFragment(@NonNull CarlsFragment fragment, String tag) {
        carls_addFragment(carls_getContainerViewId(fragment.getId()), fragment, tag);
    }

    @Override
    public void frg_addFragment(@NonNull CarlsFragment fragment) {
        carls_addFragment(carls_getContainerViewId(fragment.getId()), fragment);
    }
}
