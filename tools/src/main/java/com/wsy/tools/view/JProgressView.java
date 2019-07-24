package com.wsy.tools.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wsy.tools.util.DisplayUtils;

/**
 * @another wsy
 * @date 2017/6/22.
 */
public class JProgressView extends View {

    float mRingWidth = DisplayUtils.dp2px(2);

    RectF mRectF = new RectF(0, 0, 0, 0);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setStyle(Style.STROKE);
            setColor(Color.BLUE);
            setStrokeWidth(mRingWidth);
        }
    };
    Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setStyle(Style.STROKE);
            setStrokeWidth(mRingWidth);
            setColor(Color.LTGRAY);
        }
    };
    private float progress = 0;

    public JProgressView(Context context){
        super(context);
    }

    public JProgressView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    public JProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(mRingWidth/2, mRingWidth/2, w-mRingWidth/2, h-mRingWidth/2);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawArc(mRectF, 0, 360, false, mBgPaint);
        canvas.drawArc(mRectF, -90, progress*360/100, false, mPaint);
    }

    public void setProgress(float progress){
        this.progress = progress;
        postInvalidate();
    }

    public void setColor(@ColorInt int color){
        mPaint.setColor(color);
    }
}
