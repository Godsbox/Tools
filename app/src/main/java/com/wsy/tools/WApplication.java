/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools;

import android.app.Application;

import com.wsy.tools.util.ContextUtils;

/**
 * 应用Application。
 */
public class WApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.setAppContext(this);
    }
}