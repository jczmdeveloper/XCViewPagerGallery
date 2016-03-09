# XCViewPagerGallery
XCViewPagerGallery-A Gallery View  which scroll like ViewPager,一个自定义的ViewPager画廊效果控件

1、效果图


![image](https://github.com/jczmdeveloper/XCViewPagerGallery/blob/master/screenshots/01.gif)


2、使用示例：


package com.xc.xcviewpagergallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    SGViewPagerGallery mViewPager;
    private List<View> mBannerViewList = new ArrayList<>(0);
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mContext = this;
        mViewPager = ((SGViewPagerGallery) findViewById(R.id.viewpager));
        int[] resId = new int[]{
                R.drawable.a_1,
                R.drawable.a_2,
                R.drawable.a_3,
                R.drawable.a_4,
                R.drawable.a_5,
        };
        for (int i = 0; i < 5; i++) {
            View item = new View(mContext);
            item.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT));
            item.setBackgroundResource(resId[i]);

            mBannerViewList.add(item);
        }
        mViewPager.setAdapter(new ViewPagerAdapter());
        mViewPager.setPageMargin(20);
//        mViewPager.setPercent(0.75f);
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mBannerViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mBannerViewList.get(position), 0);//添加页卡
            return mBannerViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mBannerViewList.get(position));//删除页卡
        }
    }

}


