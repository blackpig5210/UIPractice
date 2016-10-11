package com.blackpig.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by black on 2016/10/11/0011.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> titleList;

    public MyViewPagerAdapter(List<View> viewList, List<String> titleList) {
        this.viewList = viewList;
        this.titleList = titleList;
    }

    //返回所有视图的数量
    @Override
    public int getCount() {
        return viewList.size();
    }

    //判断视图是否由对象产生
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //实例化页面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //删除页面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
