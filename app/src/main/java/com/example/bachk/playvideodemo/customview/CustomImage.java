package com.example.bachk.playvideodemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.bachk.playvideodemo.R;

/**
 * Created by bachk on 10/16/2017.
 */

public class CustomImage extends RelativeLayout {

    private ImageView imageAvatar;
    private TextView txtTime;

    public CustomImage(Context context) {
        super(context);
        initView(context);
    }

    public CustomImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_imageview, this, true);
//        setBackgroundResource(R.drawable.border_top_right_bottom_left);
        imageAvatar = (ImageView) getChildAt(0);
        txtTime = (TextView) getChildAt(1);
    }

    public void setTxtTime(String time) {
        txtTime.setText(time);
    }

    public void setImageAvatar(String url) {
        // Loading avatar image.
        Glide.with(imageAvatar.getContext()).load(url)
                .crossFade()
                .thumbnail(0.5f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageAvatar);
    }
}
