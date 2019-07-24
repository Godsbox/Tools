package com.wsy.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.wsy.tools.R;


/**
 * Created by wsy on 2017/7/7.
 */
public class HProgressView extends AppCompatTextView {


    private float mProgress; //当前进度
    private int mMaxProgress = 100; //最大进度：默认为100
    private int mMinProgress = 0;//最小进度：默认为0
    private GradientDrawable mProgressDrawable;// 加载进度时的进度颜色
    private GradientDrawable mProgressDrawableBg;// 加载进度时的背景色
    private float cornerRadius;
    private int defaultColor = Color.RED;
    private int defaultBgColor = Color.WHITE;


    public HProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.progressbutton);

        try {

            /*mProgressDrawable = (GradientDrawable) getResources().getDrawable(
                    R.drawable.app_shape_rect_round_primary).mutate();
            mProgressDrawableBg = (GradientDrawable) getResources().getDrawable(
                    R.drawable.app_shape_rect_round_gray).mutate();*/

            mProgressDrawable = (GradientDrawable) getResources().getDrawable(attr.getResourceId(R.styleable.progressbutton_mProgressDrawable, -1));
            mProgressDrawableBg = (GradientDrawable) getResources().getDrawable(attr.getResourceId(R.styleable.progressbutton_mProgressDrawable, -1));

            float defValue = 2;
            cornerRadius = attr.getDimension(R.styleable.progressbutton_buttonCornerRadius, defValue);

            mProgress = attr.getFloat(R.styleable.progressbutton_progress, defValue);

            int progressColor = attr.getColor(R.styleable.progressbutton_progressColor, defaultColor);
            mProgressDrawable.setColor(progressColor);

            int progressBgColor = attr.getColor(R.styleable.progressbutton_progressBgColor, defaultBgColor);
            mProgressDrawableBg.setColor(progressBgColor);


        } finally {
            attr.recycle();
        }

        if(mProgressDrawable != null){
            mProgressDrawable.setCornerRadius(cornerRadius);
        }
        if(mProgressDrawableBg != null){
            mProgressDrawableBg.setCornerRadius(cornerRadius);
        }
        setBackgroundCompat(mProgressDrawableBg);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (mProgress > mMinProgress && mProgress <= mMaxProgress) {

            float scale = getProgress() / (float) mMaxProgress;
            float indicatorWidth = (float) getMeasuredWidth() * scale;

            mProgressDrawable.setBounds(0, 0, (int) indicatorWidth, getMeasuredHeight());

            mProgressDrawable.draw(canvas);

            if (mProgress == mMaxProgress) {
                setBackgroundCompat(mProgressDrawable);
            }
        }

        super.onDraw(canvas);
    }

    public void setProgress(float progress) {
        mProgress = progress;
        //Log.d("wsy","当前进度 == "+ mProgress);
        // 设置背景
        setBackgroundCompat(mProgressDrawableBg);
        invalidate();
    }

    public float getProgress() {
        return mProgress;
    }

    private void setBackgroundCompat(Drawable drawable) {
        int pL = getPaddingLeft();
        int pT = getPaddingTop();
        int pR = getPaddingRight();
        int pB = getPaddingBottom();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        setPadding(pL, pT, pR, pB);
    }
}
