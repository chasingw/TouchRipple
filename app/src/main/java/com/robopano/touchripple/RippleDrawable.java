package com.robopano.touchripple;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @date: 2017/2/9 14:01
 * @author: chasingw(wangdong_sti@126.com)
 * @Description:
 */

public class RippleDrawable extends Drawable {

    private static final String TAG          = "RippleDrawable";
    // 0 ~ 255 透明度
    private              int    mAlpha       = 200;
    private              int    mRippleColor = 0;
    // 画笔 抗锯齿
    private              Paint  mPaint       = new Paint(Paint.ANTI_ALIAS_FLAG);
    //圆心
    private float mRipplePointX, mRipplePointY;
    //半径
    private float mRippleRadius = 200;
    private Bitmap mBitmap;

    public RippleDrawable() {
        // 设置抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);

        setRippleColor(0xffff0000);
//        setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x00003300));
    }

    public void onTouch(MotionEvent event) {
        mRippleRadius += 10;
        invalidateSelf();
        // 判断点击操作类型
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                onTouchDown(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchMove(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                onTouchUp(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_CANCEL:
                onTouchCancel(event.getX(), event.getY());
                break;
        }
    }

    private void onTouchDown(float x, float y) {
        mRipplePointX = x;
        mRipplePointY = y;
    }
    private void onTouchMove(float x, float y) {

    }
    private void onTouchUp(float x, float y) {

    }
    private void onTouchCancel(float x, float y) {

    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawColor(0xaaFF0000);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        画一个圆
        canvas.drawCircle(mRipplePointX, mRipplePointY,
                mRippleRadius,
                mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if (mPaint.getColorFilter() != colorFilter) {
            mPaint.setColorFilter(colorFilter);
            // 刷新当前Drawable
            invalidateSelf();
        }
    }

    @Override
    public int getOpacity() {
        int alpha = mPaint.getAlpha();
        if (alpha == 255) {
            // 不透明
            return PixelFormat.OPAQUE;
        } else if (alpha == 0) {
            // 全透明
            return PixelFormat.TRANSPARENT;
        } else {
            // 半透明
            return PixelFormat.TRANSLUCENT;
        }
    }

    public void setRippleColor(int color) {
        // 不建议直接设置
//        mPaint.setColor(color);
        mRippleColor = color;
        onColorOrAlphaChange();
    }

    private void onColorOrAlphaChange() {
        mPaint.setColor(mRippleColor);
        Log.e(TAG, "Set: " + mRippleColor);
        if (mAlpha != 255) {
            int pAlpha = mPaint.getAlpha();
//        pAlpha = Color.alpha(mRippleColor);
            int realAlpha = (int) (pAlpha * (mAlpha / 255f));
            mPaint.setAlpha(realAlpha);
            Log.e(TAG, "Old: " + mRippleColor + ", New: " + mPaint.getColor());
        }
        // 刷新当前Drawable
        invalidateSelf();
    }
}
