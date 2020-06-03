package com.carlsberg_stack.simonix_base_code.base.kisok;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carlsberg_stack.simonix_base_code.R;
import com.carlsberg_stack.simonix_base_code.base.CarlsDialogFragment;
import com.carlsberg_stack.simonix_base_code.helper.CarlsLogger;
import com.carlsberg_stack.simonix_base_code.helper.ToastMessage;

public class CarlsChangePasscodeFragment extends CarlsDialogFragment<ChangePasscodeHandler> implements View.OnClickListener {
    private StringBuilder stringBuilder = new StringBuilder();
    private String passcode;
    private ImageView first_dot, second_dot, third_dot, fourth_dot;
    private TextView exit, backspace, one, two, three, four, five, six, seven, eight, nine, zero;
    private int counter;
    private Handler mHandler = new Handler();
    private Runnable mAction = new Runnable() {
        @Override
        public void run() {
            if (counter == 1) {
                counter = 0;
                if (passcode.equals(stringBuilder.toString())) {
                    communicator.changePasscode(passcode);
                    dismiss();
                } else {
                    ToastMessage.makeErrorToast(getActivity(), "Passcode does not matched").show();
                }
            } else {
                passcode = stringBuilder.toString();
                counter++;
            }
            stringBuilder.delete(0, stringBuilder.length());
            updateUI();
        }
    };

    @Override
    protected void bindViews(View view) {
        first_dot = view.findViewById(R.id.first_dot);
        second_dot = view.findViewById(R.id.second_dot);
        third_dot = view.findViewById(R.id.third_dot);
        fourth_dot = view.findViewById(R.id.fourth_dot);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);
        zero = view.findViewById(R.id.zero);
        backspace = view.findViewById(R.id.backspace);
        exit = view.findViewById(R.id.exit);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        exit.setOnClickListener(this);
        backspace.setOnClickListener(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setCancelable(false);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.carls_passcode;
    }


    private void updateUI() {
        switch (stringBuilder.length()) {
            case 0:
                first_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                second_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                third_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                fourth_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                break;
            case 1:
                first_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                second_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                third_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                fourth_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                break;
            case 2:
                first_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                second_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                third_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                fourth_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                break;
            case 3:
                first_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                second_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                third_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                fourth_dot.setImageResource(R.drawable.carls_ic_mpin_dot_disabled);
                break;
            case 4:
                first_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                second_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                third_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                fourth_dot.setImageResource(R.drawable.carls_ic_mpin_dot_enabled);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch ((String) v.getTag()) {
            case "exit":
                dismiss();
//                communicator.exit();
                break;
            case "backspace":
                if (stringBuilder.length() > 0)
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                updateUI();
                break;
            default:
                stringBuilder.append((String) v.getTag());
                CarlsLogger.e(stringBuilder.toString());
                updateUI();
                if (stringBuilder.length() == 4) {
                    mHandler.postDelayed(mAction, 200);
                }

        }
    }
}
