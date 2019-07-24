/**
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.util;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.wsy.tool.util.ContextUtils.getAppContext;

/**
 * 关于视图操作方法的工具类。
 *
 * @author wsy
 */
public final class ViewUtils {

    /**
     * TextLine 缓存数组字段
     */
    private static final Field TEXT_LINE_CACHED;

    static {
        Field textLineCached = null;
        try {
            textLineCached = Class.forName("android.text.TextLine").getDeclaredField("sCached");
            textLineCached.setAccessible(true);
        } catch (Exception ex) {
            // ignore
        }
        TEXT_LINE_CACHED = textLineCached;
    }

    /**
     * 私有构造器。
     */
    private ViewUtils() {
    }

    /**
     * 清空 TextLine 缓存。
     */
    public static void clearTextLineCache() {
        // If the field was not found for whatever reason just return.
        if (TEXT_LINE_CACHED == null) {
            return;
        }

        Object cached = null;
        try {
            // Get reference to the TextLine sCached array.
            cached = TEXT_LINE_CACHED.get(null);
        } catch (Exception ex) {
            // ignore
        }

        if (cached == null) {
            return;
        }
        // Clear the array.
        for (int i = 0, size = Array.getLength(cached); i < size; i++) {
            Array.set(cached, i, null);
        }
    }

    /**
     * 在指定的父视图中查找给定视图 ID 的子视图。<br /><br />
     * <p>
     * 该工具方法是用泛型推导，省去手动书写强制类型转换。
     *
     * @param parent 父视图
     * @param id     子视图 ID
     * @param <T>    子视图类型
     * @return 子视图或null。
     */
    @SuppressWarnings("unchecked")
    public static <T> T findView(View parent, @IdRes int id) {
        if (parent == null) {
            return null;
        }
        return (T) parent.findViewById(id);
    }

    /**
     * 设置文本视图左边图片。
     *
     * @param textView   文本视图
     * @param resourceId 图片资源 ID
     */
    public static void setLeftDrawable(TextView textView, int resourceId) {
        textView.setCompoundDrawablesWithIntrinsicBounds(resourceId, 0, 0, 0);
    }

    /**
     * 设置文本视图右边图片。
     *
     * @param textView   文本视图
     * @param resourceId 图片资源 ID
     */
    public static void setRightDrawable(TextView textView, int resourceId) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, resourceId, 0);
    }

    /**
     * 设置文本视图右边图片。
     *
     * @param gravity 文本视图
     */
    public static void setGravity(TextView view, int gravity) {
        if (view != null) {
            view.setGravity(gravity);
        }
    }

    /**
     * 设置文本视图顶部图片。
     *
     * @param textView   文本视图
     * @param resourceId 图片资源 ID
     */
    public static void setTopDrawable(TextView textView, int resourceId) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, resourceId, 0, 0);
    }

    /**
     * 设置文本视图底部图片。
     *
     * @param textView   文本视图
     * @param resourceId 图片资源 ID
     */
    public static void setBottomDrawable(TextView textView, int resourceId) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resourceId);
    }

    /**
     * 设置文本视图底部图片。
     *
     * @param textView 文本视图
     */
    public static void clearDrawable(TextView textView) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    /**
     * 获取适应指定宽度的尺寸。
     *
     * @param widthMeasureSpec 宽度规格
     * @param width            固定宽度（单位：像素）
     * @param height           固定高度（单位：像素）
     * @return 尺寸
     */
    public static int[] getFitWidthDimension(int widthMeasureSpec, int width, int height) {
        int measuredWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        float factor = measuredWidth / (float) width;
        int measuredHeight = (int) (height * factor);
        return new int[]{measuredWidth, measuredHeight};
    }

    /**
     * 获取适应指定宽度的尺寸。
     *
     * @param heightMeasureSpec 高度规格
     * @param width             固定宽度（单位：像素）
     * @param height            固定高度（单位：像素）
     * @return 尺寸
     */
    public static int[] getFitHeightDimension(int heightMeasureSpec, int width, int height) {
        int measuredHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        float factor = measuredHeight / (float) height;
        int measuredWidth = (int) (width * factor);
        return new int[]{measuredWidth, measuredHeight};
    }

    /**
     * 设置视图显示或隐藏。
     *
     * @param view    视图
     * @param visible true：显示；false：隐藏。
     */
    public static void setVisible(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : INVISIBLE);
        }
    }

    /**
     * 设置视图消失或显示。
     *
     * @param view    视图
     * @param visible true：显示；false：消失。
     */
    public static void setGone(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    /**
     * 设置文本内容。
     *
     * @param view 文本视图
     * @param text 文本资源 ID
     */
    public static void setText(TextView view, @StringRes int text) {
        if (view == null || text == 0) {
            return;
        }
        view.setText(text);
    }

    /**
     * 设置文本内容。
     *
     * @param view  文本视图
     * @param color 文本资源 ID
     */
    public static void setTextColor(TextView view, @ColorRes int color) {
        if (view == null || color == 0) {
            return;
        }
        view.setTextColor(ResourceUtils.getColor(color));
    }

    /**
     * 设置文本内容。
     *
     * @param view 文本视图
     * @param text 文本
     */
    public static void setText(TextView view, String text) {
        if (view != null && text != null) {
            view.setText(text);
        }
    }

    /**
     * 设置文本内容。
     *
     * @param view 文本视图
     * @param text 文本
     */
    public static void setText(TextView view, SpannableStringBuilder text) {
        if (view != null && text != null) {
            view.setText(text);
        }
    }

    /**
     * 设置文本字号。
     *
     * @param view 文本视图
     * @param size 字号
     */
    public static void setTextSize(TextView view, int size) {
        if (view != null && size > 0) {
            view.setTextSize(size);
        }
    }

    /**
     * 设置backGround。
     *
     * @param view     文本视图
     * @param drawable 文本
     */
    public static void setBackgroundResource(TextView view, int drawable) {
        if (view != null) {
            view.setBackgroundResource(drawable);
        }
    }

    /**
     * 设置文本内容。
     * 有内容时显示，否则隐藏视图
     *
     * @param view    文本视图
     * @param content 文本内容
     */
    public static void setTextVisibility(TextView view, String content) {
        if (view == null) {
            return;
        }
        if (content != null && content.length() > 0) {
            view.setText(content);
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 设置view的宽度 高度
     *
     * @param view
     * @param size
     */
    public static void setViewWidthAndHeightRel(View view, int size) {
        if (view == null) {
            return;
        }
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = size;
        linearParams.height = size;
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 设置view的宽度 高度
     *
     * @param view
     * @param size
     */
    public static void setViewWidthAndHeightRel(View view, int size, int marginTop) {
        if (view == null) {
            return;
        }
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = size;// 控件的宽强制设成30
        linearParams.height = size;
        linearParams.setMargins(0, DisplayUtils.dp2px(marginTop), 0, 0);
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 设置view的宽度 高度
     *
     * @param view
     * @param size
     */
    public static void setViewWidthAndHeightRelCenter(View view, int size) {
        if (view == null) {
            return;
        }
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = size;// 控件的宽强制设成30
        linearParams.height = size;
        linearParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 设置view的 高度
     *
     * @param view
     * @param size
     */
    public static void setViewHeightRel(View view, int size) {
        if (view == null) {
            return;
        }
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = size;// 控件的宽强制设成30
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 设置view的 宽度
     *
     * @param view
     * @param size
     */
    public static void setViewWidthLin(View view, int size) {
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = size;// 控件的宽强制设成30
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 设置view的宽度
     *
     * @param view
     * @param size
     */
    public static void setViewWidthRel(View view, int size) {
        if (view == null) {
            return;
        }
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = size;// 控件的宽强制设成30
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 设置view的宽度 高度
     *
     * @param view
     * @param size
     */
    public static void setViewHeightLin(View view, int size) {
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = size;
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    /**
     * 新建一个指定布局资源 ID 的视图。
     *
     * @param layout 布局资源 ID
     * @param root   父视图
     * @param <T>    视图类型
     * @return 视图
     */
    public static <T> T inflate(@LayoutRes int layout, @Nullable ViewGroup root) {
        return inflate(layout, root, root != null);
    }

    /**
     * 新建一个指定布局资源 ID 的视图。
     *
     * @param layout       布局资源 ID
     * @param root         父视图
     * @param attachToRoot 是否绑定到 <code>root</code>，true：是；false：否。
     * @param <T>          视图类型
     * @return 视图
     */
    public static <T> T inflate(@LayoutRes int layout, @Nullable ViewGroup root, boolean attachToRoot) {
        LayoutInflater inflater = LayoutInflater.from(getAppContext());
        return (T) inflater.inflate(layout, root, attachToRoot);
    }

    /**
     * 设置单击监听器。
     *
     * @param view     视图
     * @param listener 单击监听器
     */
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 设置勾选框监听器。
     *
     * @param view     视图
     * @param listener 勾选监听器
     */
    public static void setOnCheckedChangeListener(CheckBox view, CompoundButton.OnCheckedChangeListener listener) {
        if (view != null) {
            view.setOnCheckedChangeListener(listener);
        }
    }

    /**
     * 增加View的paddingTop,增加的值为状态栏高度
     */
    public static void setPaddingSmart(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            int statusBarHeight = DeviceUtils.getStatusBarHeight();
            if (lp != null && lp.height > 0) {
                lp.height += statusBarHeight;//增高
            }
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + statusBarHeight,
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    @Deprecated
    public static int getStatusBarHeight() {
        return DeviceUtils.getStatusBarHeight();
    }

    /**
     * 文本加粗
     *
     * @param isBold 是否加粗
     */
    public static void setBoldText(TextView view, boolean isBold) {
        if (view != null) {
            TextPaint tp = view.getPaint();
            tp.setFakeBoldText(isBold);
        }
    }

    /**
     * 设置字间距。
     *
     * @param view          视图
     * @param letterSpacing 间距 0-0.5
     */
    public static void setTextSpace(TextView view, float letterSpacing) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && view != null) {
            view.setLetterSpacing(letterSpacing);
        }
    }

}
