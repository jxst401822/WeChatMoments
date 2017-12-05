package com.john.wechatmoments.ui;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.john.wechatmoments.R;
import com.john.wechatmoments.app.Constants;
import com.john.wechatmoments.base.BaseActivity;
import com.john.wechatmoments.base.contract.MainContract;
import com.john.wechatmoments.component.ACache;
import com.john.wechatmoments.component.ImageLoader;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;
import com.john.wechatmoments.presenter.MainPresenter;
import com.john.wechatmoments.ui.adapter.PullToRefreshAdapter;
import com.john.wechatmoments.ui.adapter.loadmore.CustomLoadMoreView;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    private static final int PAGE_SIZE = 5;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private PullToRefreshAdapter mAdapter;
    private ACache mACache;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mACache = ACache.get(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView((UserBean) mACache.getAsObject(Constants.ACHE_USER));
        initRefreshLayout();
        mPresenter.refreshData(PAGE_SIZE);
    }

    @Override
    public void showRefreshData(List<TweetBean> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        setData(true,list);
    }

    @Override
    public void showLoadMoreData(List<TweetBean> list) {
        setData(false,list);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMoreData(PAGE_SIZE);
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addHeadView(UserBean userBean) {
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.tv_username)).setText(userBean.getNick());
        ImageLoader.load(this, userBean.getProfileimage(), (ImageView) headView.findViewById(R.id.iv_mtopimg), R.drawable.m_top_img);
        ImageLoader.load(this, userBean.getAvatar(), (ImageView) headView.findViewById(R.id.siv_img), R.drawable.ic_avatar);
        mAdapter.addHeaderView(headView);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.refreshData(PAGE_SIZE);
            }
        });
    }

    private void setData(boolean isRefresh, List<TweetBean> data) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }

}
