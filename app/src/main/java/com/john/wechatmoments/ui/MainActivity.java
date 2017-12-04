package com.john.wechatmoments.ui;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.john.wechatmoments.R;
import com.john.wechatmoments.app.Constants;
import com.john.wechatmoments.base.BaseActivity;
import com.john.wechatmoments.base.contract.MainContract;
import com.john.wechatmoments.component.ImageLoader;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;
import com.john.wechatmoments.presenter.MainPresenter;
import com.john.wechatmoments.ui.adapter.PullToRefreshAdapter;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    private static final int PAGE_SIZE = 5;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private PullToRefreshAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView(Constants.userBean);
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.refreshData();
    }

    @Override
    public void showRefreshData(List<TweetBean> list) {
        mAdapter.addData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadMoreData() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addHeadView(UserBean userBean) {
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.tv_username)).setText(userBean.getNick());
//        ImageLoader.load(this, userBean.getProfileimage(), (ImageView) headView.findViewById(R.id.iv_mtopimg));
        ImageLoader.load(this, userBean.getAvatar(), (ImageView) headView.findViewById(R.id.siv_img));
        mAdapter.addHeaderView(headView);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
    }

    private void loadMore() {
    }

    private void setData(boolean isRefresh, List data) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
