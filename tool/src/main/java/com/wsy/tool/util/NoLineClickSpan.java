package com.wsy.tool.util;

import android.support.annotation.ColorRes;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * 文本截取点击去除下划线.
 *
 * @author wsy
 */
public class NoLineClickSpan extends ClickableSpan {

    private int color;

    public NoLineClickSpan() {
        super();
    }

    public void setColor(@ColorRes int color){
        this.color = color;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ResourceUtils.getColor(color));
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {

    }
}