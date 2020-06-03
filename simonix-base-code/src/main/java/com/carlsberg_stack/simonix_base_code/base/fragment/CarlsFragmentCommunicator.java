package com.carlsberg_stack.simonix_base_code.base.fragment;


import androidx.annotation.NonNull;

import com.carlsberg_stack.simonix_base_code.base.CarlsCommunicator;
import com.carlsberg_stack.simonix_base_code.base.CarlsFragment;

public interface CarlsFragmentCommunicator extends CarlsCommunicator {
    /*fragments*/
    void frg_replaceFragment(@NonNull CarlsFragment fragment);

    void frg_replaceFragment(@NonNull CarlsFragment fragment, String tag);

    void frg_addFragment(@NonNull CarlsFragment fragment, String tag);

    void frg_addFragment(@NonNull CarlsFragment fragment);
}