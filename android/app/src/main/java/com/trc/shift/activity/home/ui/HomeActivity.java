package com.trc.shift.activity.home.ui;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.gyf.barlibrary.ImmersionBar;
import com.trc.shift.R;
import com.trc.shift.activity.home.fragment.tag.TagFragment;
import com.trc.shift.activity.home.p.Home_P;
import com.trc.shift.activity.home.v.IHomeView;
import com.trc.shift.base.BaseMVPActivity;
import com.trc.shift.base.BasePresenter;
import com.trc.shift.resultBean.LoginBean;
import com.trc.shift.unit.transformer.AccordionTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trc on 2018/5/3.
 */

public class HomeActivity extends BaseMVPActivity<IHomeView, Home_P> implements IHomeView {
    @BindView(R.id.BottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private MenuItem menuItem;

    @NonNull
    @Override
    public Home_P createPresenter() {
        return new Home_P(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void checkData(String failMsg) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_tag;
    }

    @SuppressLint({"ResourceType", "NewApi"})
    @Override
    public void initData() {
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        /**设置MenuItem的字体颜色**/
        Resources resource = (Resources) getBaseContext().getResources();
        @SuppressLint("ResourceType") ColorStateList csl = (ColorStateList) resource.getColorStateList(R.drawable.tablayout_btn);
        bottomNavigationView.setItemTextColor(csl);

        /**设置MenuItem默认选中项**/
        bottomNavigationView.getMenu().getItem(0).setChecked(true);


        bottomNavigationView.setItemIconTintList(resource.getColorStateList(R.drawable.tablayout_btn, null));


        Log.d("HomeActivity", "bottomNavigationView:" + bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.bottom_one:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.bottom_two:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.bottom_three:
                            viewPager.setCurrentItem(2);
                            break;
//                        case R.id.item_more:
//                            viewPager.setCurrentItem(3);
//                            break;
                    }
                    return false;
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        List<Fragment> list = new ArrayList<>();


        Bundle bundle1 = new Bundle();
        bundle1.putString("Title", "第一个Fragment");
        bundle1.putInt("pager_num", 1);
        Fragment fg1 = TagFragment.newInstance(bundle1);

//        Bundle bundle2 = new Bundle();
//        bundle2.putString("Title", "第二个Fragment");
//        bundle2.putInt("pager_num", 2);
//        Fragment fg2 = TagFragment.newInstance(bundle2);
//
//        Bundle bundle3 = new Bundle();
//        bundle3.putString("Title", "第三个Fragment");
//        bundle3.putInt("pager_num", 3);
//        Fragment fg3 = TagFragment.newInstance(bundle3);

//        Bundle bundle4=new Bundle();
//        bundle4.putString("Title","第四个Fragment");
//        bundle4.putInt("pager_num",4);
//        Fragment fg4=TagFragment.newInstance(bundle4);

        list.add(fg1);
//        list.add(fg1);
//        list.add(fg1);
//        list.add(fg4);

        viewPager.setPageTransformer(true, new AccordionTransformer());
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void initView() {
        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.BottomNavigationView);
    }

    @Override
    public void loginSuccess(LoginBean str) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        mImmersionBar = ImmersionBar
                .with(this)
                .barColor(R.color.tou)
                .navigationBarColor(R.color.colorPrimary);
        mImmersionBar.init();
        ButterKnife.bind(this);
        Log.d("HomeActivity", "PPPPPPPPPPPP");
    }
}
