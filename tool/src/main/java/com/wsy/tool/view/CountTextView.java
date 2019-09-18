/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.AttributeSet;

import com.wsy.tool.R;


/**
 * 文本长度计数组件
 *
 * @author wsy
 */
public class CountTextView extends AppCompatTextView {
    /**
     * 最大文本长度
     */
    private int mMaxLength;

    private int mMinLength;

    public CountTextView(Context context) {
        super(context);
    }

    public CountTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置最大的文本长度。
     *
     * @param maxLength 最大的文本长度
     * @return
     */
    public void setMaxLength(int maxLength) {
        mMaxLength = maxLength;
        String text = getContext().getString(R.string.length_counter, 0, mMaxLength);
        setText(text);
    }

    /**
     * 设置当前长度。
     *
     * @param length 当前文本长度
     * @return
     */
    public void setCurrentLength(int length) {
        boolean over = length > mMaxLength || length < mMinLength;
        int textId = over ? R.string.length_counter_over : R.string.length_counter;
        String text = getContext().getString(textId, length, mMaxLength);
        setText(Html.fromHtml(text));
    }

    public void setmMinLength(int mMinLength) {
        this.mMinLength = mMinLength;
    }
}
