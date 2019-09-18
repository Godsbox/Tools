/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可配置是否启用翻页功能的翻页视图。
 *
 * @author wsy
 */
public class EnableViewPager extends ViewPager {

    /**
     * 标识是否启用翻页功能。
     */
    private boolean pagingEnabled = true;

    public EnableViewPager(Context context) {
        super(context);
    }

    public EnableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.pagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.pagingEnabled && super.onInterceptTouchEvent(event);
    }

    /**
     * 设置是否启用翻页功能。
     *
     * @param enabled true：启用；false：禁用。
     */
    public void setPagingEnabled(boolean enabled) {
        this.pagingEnabled = enabled;
    }
}
