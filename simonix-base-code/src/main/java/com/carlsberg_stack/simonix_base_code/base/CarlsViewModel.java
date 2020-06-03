package com.carlsberg_stack.simonix_base_code.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class CarlsViewModel extends AndroidViewModel {

    public CarlsViewModel(@NonNull Application application) {
        super(application);
    }

}
