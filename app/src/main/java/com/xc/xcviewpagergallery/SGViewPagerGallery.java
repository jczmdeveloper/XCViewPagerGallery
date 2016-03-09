package com.xc.xcviewpagergallery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by caizhiming on 2016/3/9.
 * 自定义ViewPager画廊效果控件
 */
public class SGViewPagerGallery extends RelativeLayout{

    private ViewPager mViewPager;
    private float mPercent = 0.7f;
    private int mWidth;
    private float mScale = 0.8f;

    public SGViewPagerGallery(Context context) {
        this(context, null);
    }
    public SGViewPagerGallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SGViewPagerGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        this.setClipChildren(false);

        mViewPager = new ViewPager(context,attrs);
        mViewPager.setClipChildren(false);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(10);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        LayoutParams lp = new LayoutParams(getContext(),attrs);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addView(mViewPager, lp);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int childMeasureSpec = MeasureSpec.makeMeasureSpec((int) (mWidth * mPercent),MeasureSpec.getMode(widthMeasureSpec));
        mViewPager.measure(childMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mViewPager.layout((int) (mWidth * (1-mPercent)/2), mViewPager.getTop(),
                (int) (mViewPager.getRight() - mWidth*(1-mPercent)/2), mViewPager.getBottom());
    }

    public void setPercent(float percent){
        mPercent = percent;
        invalidate();
    }
    public ViewPager getViewPager(){
        return mViewPager;
    }
    public void setPageMargin(int marginPixels){
        mViewPager.setPageMargin(marginPixels);
    }
    public void setAdapter(PagerAdapter adapter){
        mViewPager.setAdapter(adapter);
    }
    private ViewPager.OnPageChangeListener mOnPageChangeListener
            = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.v("czm","positionOffset/positionOffsetPixels="+positionOffset+"/"+positionOffsetPixels);
            Log.v("czm", "position=" + position);
            float scale = 1-positionOffset*mScale;
            Log.v("czm", "scale=" + scale);
//            mViewPager.getChildAt(position).setScaleY(scale);
        }

        @Override
        public void onPageSelected(int position) {
//            mViewPager.getChildAt(0).setScaleY(mScale);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
