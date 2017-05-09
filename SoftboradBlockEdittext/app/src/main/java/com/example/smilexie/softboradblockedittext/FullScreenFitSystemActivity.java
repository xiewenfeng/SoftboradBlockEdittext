package com.example.smilexie.softboradblockedittext;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.smilexie.softboradblockedittext.databinding.ActivityAdjustInputBinding;

/**
 * 透明状态栏+FitSystemWindow+adjustResize
 * Created by SmileXie on 2017/5/8.
 */

public class FullScreenFitSystemActivity extends TransStatusbarBaseActivity<ActivityAdjustInputBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_input);
        setTitle("透明状态栏+fitSystemWindow+adjustResize");
    }

}
