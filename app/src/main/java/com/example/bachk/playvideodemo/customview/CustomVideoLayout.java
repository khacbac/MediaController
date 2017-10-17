package com.example.bachk.playvideodemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by bachk on 10/17/2017.
 */

public class CustomVideoLayout extends RelativeLayout {
    public CustomVideoLayout(Context context) {
        super(context);
        initView(context);
    }

    public CustomVideoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomVideoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
//        inflater.inflate()
    }
}
