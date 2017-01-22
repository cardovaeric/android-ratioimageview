package com.cardova.ratioimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Cardova on 1/22/17.
 */

public class RatioImageView extends AppCompatImageView {

    private float mRatio = 1.0f;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView );
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr  = a.getIndex(i);
            if (attr == R.styleable.RatioImageView_ratio) {
                setRatio(a.getFloat(i, 1.0f));

            }
        }
    }

    public float getRatio() {
        return mRatio;
    }

    public void setRatio(float ratio) {
        mRatio = ratio;
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mRatio);
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}