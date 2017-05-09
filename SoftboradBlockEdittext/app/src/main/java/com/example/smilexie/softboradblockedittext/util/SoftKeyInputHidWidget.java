package com.example.smilexie.softboradblockedittext.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * 用于解决因为沉浸式状态栏，但不想采用fitSystemWidnow属性
 * 或不想设置键盘属adjustResize/adjustPan属性，想实现键盘不遮挡输入框
 * Created by SmileXie on 2017/3/27.
 */
public class SoftKeyInputHidWidget {
    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private int contentHeight;
    private boolean isfirst = true;
    private  int statusBarHeight;

    public static void assistActivity(Activity activity) {
        new SoftKeyInputHidWidget(activity);
    }

    private SoftKeyInputHidWidget(Activity activity) {
        statusBarHeight = getStatusBarHeight(activity);
        FrameLayout content = (FrameLayout)activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);

        //界面出现变动都会调用这个监听事件
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (isfirst) {
                    contentHeight = mChildOfContent.getHeight();//兼容华为等机型
                    isfirst = false;
                }
                possiblyResizeChildOfContent();
            }
        });

        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    //重新调整跟布局的高度
    private void possiblyResizeChildOfContent() {

        int usableHeightNow = computeUsableHeight();
        //当前可见高度和上一次可见高度不一致 布局变动
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    frameLayoutParams.height = usableHeightSansKeyboard - heightDifference + statusBarHeight;
                } else {
                    frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
                }
            } else {
                frameLayoutParams.height = contentHeight;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    /**
     * 获取改变之后界面的可用高度（可以为开发者显示内容的高度）
     * @return
     */
    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);//获取到的rect就是界面除去标题栏、除去软键盘挡住部分，所剩下的域
        return (r.bottom - r.top);
    }

    public static int getStatusBarHeight(Activity activity) {
        //获取状态栏的高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
}
