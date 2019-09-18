package com.wsy.tool.simple;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 简单文本观察者
 *
 * @autho wsy
 */
public class SimpleTextWatcher implements TextWatcher {

    /**
     * 内容
     */
    private int textLength;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public int getTextLength() {
        return textLength;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }
}