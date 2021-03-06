package com.zo2m4bie.firstitemexpandablelistview.controller;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.zo2m4bie.firstitemexpandablelistview.R;

/**
 * Created by dima on 1/11/16.
 */
public class HaveMinController implements IMaxMinController {

    private int mMinValue;
    private int mSecondItemMaxHeight, mFirstItemMaxHeight;
    private int mHalfScreen;

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelfExpandableListView, 0, 0);
        try {
            mMinValue = (int) ta.getDimension(R.styleable.SelfExpandableListView_itemMinHeight, 0);
        } finally {
            ta.recycle();
        }
    }

    @Override
    public int getHalfMax() {
        return mSecondItemMaxHeight / 2;
    }

    @Override
    public int getMinValue(View child) {
        return mMinValue;
    }

    @Override
    public int getDifferentMaxMin(int minV) {
        return mSecondItemMaxHeight - mMinValue;
    }

    @Override
    public void measureToMinValue(View tmpView, int viewWidth) {
        tmpView.measure(View.MeasureSpec.EXACTLY | viewWidth, View.MeasureSpec.EXACTLY | mMinValue);
    }

    @Override
    public void setSecondItemHeight(View view, int width) {
        view.measure(View.MeasureSpec.EXACTLY | width, View.MeasureSpec.UNSPECIFIED);
        mSecondItemMaxHeight = view.getMeasuredHeight();
        if(mSecondItemMaxHeight > mHalfScreen){
            mSecondItemMaxHeight = mHalfScreen;
        }
    }

    @Override
    public void setFirstItemHeight(View view) {

    }

    @Override
    public int getSecondItemMinHeight() {
        return mMinValue;
    }

    @Override
    public void measureSecondItem(View tmpView, int width) {
        tmpView.measure(View.MeasureSpec.EXACTLY | width, View.MeasureSpec.UNSPECIFIED);
        mSecondItemMaxHeight = tmpView.getMeasuredHeight();
        if(mSecondItemMaxHeight > mHalfScreen){
            mSecondItemMaxHeight = mHalfScreen;
        }
    }

    @Override
    public void measureFirstItemToMax(View tmpView, int width) {
        tmpView.measure(View.MeasureSpec.EXACTLY | width, View.MeasureSpec.UNSPECIFIED);
        mFirstItemMaxHeight = tmpView.getMeasuredHeight();
        if(mFirstItemMaxHeight > mHalfScreen){
            mFirstItemMaxHeight = mHalfScreen;
        }
        tmpView.measure(View.MeasureSpec.EXACTLY | width, View.MeasureSpec.EXACTLY | mFirstItemMaxHeight);
    }

    @Override
    public void measureFirstItem(View child, int itemWidth) {
        child.measure(View.MeasureSpec.EXACTLY | itemWidth, View.MeasureSpec.UNSPECIFIED);
        mFirstItemMaxHeight = child.getMeasuredHeight();
        if(mFirstItemMaxHeight > mHalfScreen){
            mFirstItemMaxHeight = mHalfScreen;
        }
        child.measure(View.MeasureSpec.EXACTLY | itemWidth, View.MeasureSpec.EXACTLY | mFirstItemMaxHeight);
    }

    @Override
    public int getFirstItemMaxValue() {
        return mFirstItemMaxHeight;
    }

    @Override
    public float getSecondItemMaxValue() {
        return mSecondItemMaxHeight;
    }

    @Override
    public int getMaxVauleFor(View child, int itemWidth) {
        child.measure(View.MeasureSpec.EXACTLY | itemWidth, View.MeasureSpec.UNSPECIFIED);
        int maxValue = child.getMeasuredHeight();
        return maxValue > mHalfScreen ? mHalfScreen : maxValue;
    }

    @Override
    public void setMaxOfMaxHeight(int halfScreen) {
        mHalfScreen = halfScreen;
    }

    @Override
    public int getFirstItemMinValue(View child) {
        return mMinValue;
    }
}
