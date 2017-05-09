package com.example.smilexie.softboradblockedittext;

import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.smilexie.softboradblockedittext.databinding.ActivityLoginBinding;

/**
 * 采用方法三，仿普通登录界面，根据状态栏状态，调整界面，透明状态栏下登录页面
 * Created by SmileXie on 2017/5/8.
 */

public class LoginActivity extends TransStatusbarBaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBaseBinding.toolBar.toolBar.setVisibility(View.GONE);
        addLayoutListener(bindingView.mainLl, bindingView.loginBtn);
        setTitle("全屏无标题栏滚动界面");
    }

    /**
     *  1、获取main在窗体的可视区域
     *  2、获取main在窗体的不可视区域高度
     *  3、判断不可视区域高度，之前根据经验值，在有些手机上有点不大准，现改成屏幕整体高度的1/3
     *      1、大于屏幕整体高度的1/3：键盘显示  获取Scroll的窗体坐标
     *                           算出main需要滚动的高度，使scroll显示。
     *      2、小于屏幕整体高度的1/3：键盘隐藏
     *
     * @param main 根布局
     * @param scroll 需要显示的最下方View
     */
    public void addLayoutListener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                int screenHeight = main.getRootView().getHeight();
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > screenHeight / 4) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    main.scrollTo(0, srollHeight);
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }
}
