package com.example.bachk.playvideodemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bachk.playvideodemo.R;

/**
 * Created by bachk on 10/16/2017.
 */

public class CustomController extends LinearLayout implements View.OnClickListener{

    private ImageButton mPauseButton;
    private ImageButton mFfwdButton;
    private ImageButton mRewButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private IeOnBtnControlClickListener clickListener;

    public CustomController(Context context) {
        super(context);
        initView(context);
    }

    public CustomController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        setPadding(5,5,5,5);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_controller, this, true);

        mPrevButton = (ImageButton) getChildAt(0);
        mRewButton = (ImageButton) getChildAt(1);
        mPauseButton = (ImageButton) getChildAt(2);
        mFfwdButton = (ImageButton) getChildAt(3);
        mNextButton = (ImageButton) getChildAt(4);

        mPrevButton.setOnClickListener(this);
        mRewButton.setOnClickListener(this);
        mPauseButton.setOnClickListener(this);
        mFfwdButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prev:
                clickListener.onPrevButtonClick();
                break;
            case R.id.rew:
                clickListener.onRewButtonClick();
                break;
            case R.id.pause:
                clickListener.onPauseButtonClick();
                break;
            case R.id.ffwd:
                clickListener.onFfwdButtonClick();
                break;
            case R.id.next:
                clickListener.onNextButtonClick();
                break;
            default:
                break;
        }
    }

    public interface IeOnBtnControlClickListener {
        void onPrevButtonClick();
        void onRewButtonClick();
        void onPauseButtonClick();
        void onFfwdButtonClick();
        void onNextButtonClick();
    }

    public void setOnBtnControlClickListener(IeOnBtnControlClickListener listener) {
        this.clickListener = listener;
    }
}
