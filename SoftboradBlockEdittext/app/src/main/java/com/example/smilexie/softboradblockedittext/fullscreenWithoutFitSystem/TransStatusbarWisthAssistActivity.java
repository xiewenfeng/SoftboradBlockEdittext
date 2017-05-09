package com.example.smilexie.softboradblockedittext.fullscreenWithoutFitSystem;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.smilexie.softboradblockedittext.R;
import com.example.smilexie.softboradblockedittext.databinding.ActivityWithAssistBinding;
import com.example.smilexie.softboradblockedittext.util.SoftKeyInputHidWidget;

/** 方法四，使用透明状态栏 + assistActivity 实现键盘不挡住输入框
 * Created by SmileXie on 2017/5/8.
 */

public class TransStatusbarWisthAssistActivity extends AppCompatActivity {
    private ActivityWithAssistBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_with_assist);
        initStatusBar();
        SoftKeyInputHidWidget.assistActivity(this);
        setToolBar();
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于4.4
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(getResources().getColor(R.color.titlebar_main_color));
            } else {
                //4.4到5.0
                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            }
            binding.statusBarView.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight = SoftKeyInputHidWidget.getStatusBarHeight(this);
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.statusBarView.getLayoutParams();
            params.height = statusHeight;
            binding.statusBarView.setLayoutParams(params);
        }
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(binding.toolBar.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
        binding.toolBar.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.toolBar.titleTv.setText("透明状态栏 + assistActivity 重绘界面");
    }
}
