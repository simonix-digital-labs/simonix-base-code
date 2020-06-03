package com.carlsberg_stack.simonix_base_code.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public abstract class CarlsDialogFragment<T extends CarlsCommunicator> extends DialogFragment {

    protected static final String TAG = CarlsDialogFragment.class.getSimpleName();
    protected T communicator;
    protected View view;

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communicator = (T) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getContentView(), container, false);
        bindViews(view);
        return view;
    }

    protected abstract void bindViews(View view);

    protected abstract int getContentView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
