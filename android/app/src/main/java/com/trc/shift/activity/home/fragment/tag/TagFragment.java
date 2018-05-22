package com.trc.shift.activity.home.fragment.tag;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.GridLayout;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.trc.shift.R;
import com.trc.shift.base.BaseFragment;
import com.trc.shift.config.ApiUrl;
import com.trc.shift.okgoDialog.DialogCallback;
import com.trc.shift.okgoDialog.TrcResponse;
import com.trc.shift.resultBean.ArticleTagListBean;
import com.trc.shift.resultBean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by trc on 2018/5/3.
 */

public class TagFragment extends BaseFragment {
    @BindView(R.id.tag_rv)
    RecyclerView tagRv;
    Unbinder unbinder;
    @BindView(R.id.main_swipe)
    WaveSwipeRefreshLayout mainSwipe;

    private Bundle arg;
    private TagRvAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg = getArguments();
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_tag;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        unbinder = ButterKnife.bind(this, getRootView());
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        GridLayoutManager gridLayout = new GridLayoutManager(getContext(), 2);
//        tagRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        tagRv.setLayoutManager(gridLayout);
        adapter = new TagRvAdapter(getContext());
        //设置Adapter
        tagRv.setAdapter(adapter);
        //设置分隔线
        //        recyclerView.addItemDecoration( new DividerGridItemDecoration(this ));
        //设置增加或删除条目的动画
        tagRv.setItemAnimator(new DefaultItemAnimator());
        mainSwipe.setWaveRGBColor(171, 71, 188);
        int resId = R.anim.grid_layout_animation_from_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        tagRv.setLayoutAnimation(animation);


        mainSwipe.setOnRefreshListener(() -> {
            request();
        });
        mainSwipe.setColorSchemeColors(Color.WHITE);
        request();

    }

    public static TagFragment newInstance(Bundle args) {
        TagFragment fragment = new TagFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void request() {
        OkGo.<TrcResponse<ArticleTagListBean>>post(ApiUrl.TAG_LIST)
                .tag(this)
                .execute(new DialogCallback<TrcResponse<ArticleTagListBean>>() {
                    @Override
                    public void onSuccess(Response<TrcResponse<ArticleTagListBean>> response) {
                        Log.d("TagFragment", "onSuccess");
                        mainSwipe.setRefreshing(false);
                        adapter.setList(response.body().data.getTags());
                    }
                });
    }


}
