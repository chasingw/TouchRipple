package com.robopano.touchripple;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * @date: 2017/2/9 11:59
 * @author: chasingw(wangdong_sti@126.com)
 * @Description:
 */

public class RippleButton extends Button {
    private RippleDrawable mRippleDrawable;

    public RippleButton(Context context) {
        this(context, null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRippleDrawable = new RippleDrawable();
        // 设置刷新接口， 在View中已经实现
        mRippleDrawable.setCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        //绘制自己的drawable
        mRippleDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mRippleDrawable.onTouch(event);
//        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 设置Drawable绘制和刷新的区域
        mRippleDrawable.setBounds(0, 0, getWidth(), getHeight());
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        // 验证drawable是否ok
        return who == mRippleDrawable || super.verifyDrawable(who);
    }
}
