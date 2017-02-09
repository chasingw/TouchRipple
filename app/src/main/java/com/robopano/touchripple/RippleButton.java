package com.robopano.touchripple;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @date: 2017/2/9 11:59
 * @author: chasingw(wangdong_sti@126.com)
 * @Description:
 */

public class RippleButton extends Button {
    public RippleButton(Context context) {
        this(context, null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
