package com.example.smilexie.softboradblockedittext;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.smilexie.softboradblockedittext.databinding.ActivityAdjustInputBinding;

/**
 * 非全屏+scrollView+adjustResize
 * Created by SmileXie on 2017/5/8.
 */

public class AdjustResizeNFullScreenScrollActivity extends TransStatusbarBaseActivity<ActivityAdjustInputBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useTransStatusBar = false;
        setContentView(R.layout.activity_adjust_input);
        setTitle("非全屏+scrollView+adjustResize");
    }

}
