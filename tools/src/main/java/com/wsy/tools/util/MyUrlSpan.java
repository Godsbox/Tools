package com.wsy.tools.util;

import android.support.annotation.ColorRes;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.wsy.tools.view.OnUrlListener;

/**
 * 无下划线 点击监听
 * @author wsy
 * @time 2018/7/24 14:48
 */
public class MyUrlSpan extends ClickableSpan {

    private int color;

    private String url;

    private OnUrlListener listener;

    public void setColor(@ColorRes int color){
        this.color = color;
    }

    public MyUrlSpan(String url, OnUrlListener listener) {
        this.url = url;
        this.listener = listener;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);//无下划线
        ds.setColor(ResourceUtils.getColor(color));
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClickUrl(url);
        }
    }
}
