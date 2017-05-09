package com.example.smilexie.softboradblockedittext;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.smilexie.softboradblockedittext.databinding.ActivityBaseBinding;

/**
 * 基类，用于设置状态栏
 * Created by SmileXie on 2017/3/12.
 */

public class TransStatusbarBaseActivity<SV extends ViewDataBinding> extends AppCompatActivity {
    protected ActivityBaseBinding mBaseBinding;
    // 布局view
    protected SV bindingView;
    protected boolean useTransStatusBar = true;//判断是否使用透明状态栏

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        // 设置透明状态栏
        if (useTransStatusBar) {
            setStatusBar(this);
        }
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());
        //设置标题栏
        setToolBar();
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mBaseBinding.toolBar.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
        mBaseBinding.toolBar.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 设置状态栏颜色为透明色，这样能保证状态栏为沉浸式状态
     * 根据SDK >= 21时，标题栏高度设为statusBarHeight(25dp)+titlBarHeight(48dp)
     * 若SDK < 21,标题栏高度直接为titlBarHeight
     */
    public static void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.titlebar_main_color));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 隐藏返回按钮
     */
    protected void hideBackBtn() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * 设置标题
     * @param text
     */
    public void setTitle(CharSequence text) {
        mBaseBinding.toolBar.toolBar.setTitle("");
        mBaseBinding.toolBar.titleTv.setText(text);
    }
}
