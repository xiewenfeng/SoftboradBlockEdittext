package com.example.smilexie.softboradblockedittext;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.smilexie.softboradblockedittext.databinding.ActivityAdjustInputBinding;

/**
 * adjustPan属性使用， 在AndroidMainfest.xml中设置windowSoftInputMode为adjustPan
 * 该属性会使界面整体上移或是下移，当前输入框下面还有输入框，无法通过滚动条来输入，标题栏也会被顶上去
 * Created by SmileXie on 2017/5/8.
 */

public class AdjustPanInputActivity extends TransStatusbarBaseActivity<ActivityAdjustInputBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useTransStatusBar = false;
        setContentView(R.layout.activity_adjust_input);
        setTitle("方法一：adjustPan属性使用");
    }

}
