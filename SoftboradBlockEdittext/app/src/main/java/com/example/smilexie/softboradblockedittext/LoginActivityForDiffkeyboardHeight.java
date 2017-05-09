package com.example.smilexie.softboradblockedittext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.smilexie.softboradblockedittext.databinding.ActivityLoginBinding;
import com.example.smilexie.softboradblockedittext.databinding.ActivityLoginWithDiffheightBinding;
import com.example.smilexie.softboradblockedittext.util.KeyboardLayout;
import com.example.smilexie.softboradblockedittext.util.SoftKeyInputHidWidget;

/**
 * 采用方法三，仿普通登录界面，根据状态栏状态，调整界面，对于键盘高度可变化时情况
 * Created by SmileXie on 2017/5/8.
 */

public class LoginActivityForDiffkeyboardHeight extends TransStatusbarBaseActivity<ActivityLoginWithDiffheightBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_diffheight);
        mBaseBinding.toolBar.toolBar.setVisibility(View.GONE);
        addLayoutListener();
        setTitle("全屏无标题栏滚动界面，自适应键盘高度变化");
    }

    /**
     * 监听键盘状态，布局有变化时，靠scrollView去滚动界面
     */
    public void addLayoutListener() {
        bindingView.mainLl.setKeyboardListener(new KeyboardLayout.KeyboardLayoutListener() {
            @Override
            public void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
                Log.e("onKeyboardStateChanged", "isActive:" + isActive + " keyboardHeight:" + keyboardHeight);
                if (isActive) {
                    scrollToBottom();
                }
            }
        });
    }

    /**
     * 弹出软键盘时将SVContainer滑到底
     */
    private void scrollToBottom() {

        bindingView.loginLl.postDelayed(new Runnable() {

            @Override
            public void run() {
                bindingView.loginLl.smoothScrollTo(0, bindingView.loginLl.getBottom() + SoftKeyInputHidWidget.getStatusBarHeight(LoginActivityForDiffkeyboardHeight.this));
            }
        }, 100);

    }
}
