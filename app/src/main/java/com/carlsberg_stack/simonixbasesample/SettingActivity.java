package com.carlsberg_stack.simonixbasesample;

import com.carlsberg_stack.simonix_base_code.base.kisok.CarlsKisokSettingActivity;

public class SettingActivity extends CarlsKisokSettingActivity {

    protected int carls_getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void carls_bindViews() {

    }

    @Override
    protected int carls_getContainerViewId(int id) {
        return R.id.settings_container;
    }

    @Override
    protected String carls_getPackageName() {
        return getPackageName();
    }
}
