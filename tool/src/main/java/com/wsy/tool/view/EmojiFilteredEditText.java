/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static android.support.v7.appcompat.R.attr.editTextStyle;

/**
 * 过滤 Emoji 表情字符的文本编辑视图。
 *
 * @author wsy
 */
public class EmojiFilteredEditText extends TextInputEditText {

    private boolean intercept;

    public EmojiFilteredEditText(Context context) {
        this(context, null, editTextStyle);
    }

    public EmojiFilteredEditText(Context context, AttributeSet attrs) {
        this(context, attrs, editTextStyle);
    }

    public EmojiFilteredEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFilters(new InputFilter[]{ new EmojiInputFilter() });
    }

    public void setIntercept(boolean intercept) {
        this.intercept = intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return intercept ? false : super.onTouchEvent(event);
    }
}
