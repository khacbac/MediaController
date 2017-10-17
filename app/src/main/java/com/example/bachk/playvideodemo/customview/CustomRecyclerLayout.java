package com.example.bachk.playvideodemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bachk.playvideodemo.R;

import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public class CustomRecyclerLayout extends LinearLayout {

    private CustomImage customImage;
    private TextView txtName;

    public CustomRecyclerLayout(Context context) {
        super(context);
    }

    public CustomRecyclerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerLayout,0,0);
        String titleText = a.getString(R.styleable.CustomRecyclerLayout_titleVideo);
        a.recycle();

        setOrientation(HORIZONTAL);
        setWeightSum(3);
        setPadding(5,5,5,5);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_recycler_layout, this, true);

        customImage = (CustomImage) getChildAt(0);
        txtName = (TextView) getChildAt(1);

        txtName.setText(titleText);
    }

    public void setText(String title) {
        this.txtName.setText(title);
    }

    public void setTxtTime(String videoUrl) {
        if (!videoUrl.contains("youtube")) {
            FFmpegMediaMetadataRetriever mFFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
            mFFmpegMediaMetadataRetriever.setDataSource(videoUrl);
            String mVideoDuration =  mFFmpegMediaMetadataRetriever .extractMetadata(FFmpegMediaMetadataRetriever .METADATA_KEY_DURATION);
            long mTimeInMilliseconds= Long.parseLong(mVideoDuration);
            long minus = mTimeInMilliseconds / 60000;
            long second = (mTimeInMilliseconds % 60000) / 1000;
            customImage.setTxtTime(""+minus + ":" + (second < 10 ? ("0" + second) : second));
        }
    }

    public void setBackGroundImage(String url) {
        customImage.setImageAvatar(url);
    }

}
