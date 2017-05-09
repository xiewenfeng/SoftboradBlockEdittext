package com.example.smilexie.softboradblockedittext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smilexie.softboradblockedittext.databinding.ActivityMainBinding;
import com.example.smilexie.softboradblockedittext.fullscreenWithoutFitSystem.AdjustResizeFullScreenFailActivity;
import com.example.smilexie.softboradblockedittext.fullscreenWithoutFitSystem.TransStatusbarWisthAssistActivity;

/**
 * 主界面
 */
public class MainActivity extends TransStatusbarBaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("各种键盘挡住输入框解决办法");
        hideBackBtn();
    }

    /**
     * 方法一：adjustPan属性使用
     * @param view
     */
    public void adjustPan(View view) {
        startActivity(new Intent(this, AdjustPanInputActivity.class));
    }

    /**
     * 方法一：非全屏+ScrollView+adjustResize
     * @param view
     */
    public void scrollViewAdjustResizeBtn(View view) {
        startActivity(new Intent(this, AdjustResizeNFullScreenScrollActivity.class));
    }


    /**
     * 方法二：全屏，透明状态栏+fitSystemWindow
     * @param view
     */
    public void adjustResizeBtn(View view) {
        startActivity(new Intent(this, FullScreenFitSystemActivity.class));
    }

    /**
     * adjustResize在全屏模式下失效
     * @param view
     */
    public void adjustResizeFail(View view) {
        startActivity(new Intent(this, AdjustResizeFullScreenFailActivity.class));
    }

    /**
     * 方法三：全屏，透明状态栏+键盘状态+滚动界面，仿常规登录界面
     * @param view
     */
    public void loginStep(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * 方法三：全屏，透明状态栏+键盘状态+滚动界面，仿常规登录界面
     * @param view
     */
    public void loginForDiffKeyboardHeightStep(View view) {
        startActivity(new Intent(this, LoginActivityForDiffkeyboardHeight.class));
    }

    /**
     * 方法四：全屏，透明状态栏+assistActivity重绘界面，借助assist实现键盘不挡住输入框
     * @param view
     */
    public void assistStep(View view) {
        startActivity(new Intent(this, TransStatusbarWisthAssistActivity.class));
    }
}
