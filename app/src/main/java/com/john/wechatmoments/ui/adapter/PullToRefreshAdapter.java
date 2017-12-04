package com.john.wechatmoments.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.john.wechatmoments.R;
import com.john.wechatmoments.component.ImageLoader;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.TweetBean.ImagesBean;
import com.john.wechatmoments.widget.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

public class PullToRefreshAdapter extends BaseQuickAdapter<TweetBean, BaseViewHolder> {
    private MomentAdapter mMomentAdapter;
    private RecyclerView mMomentRecyclerView;

    public PullToRefreshAdapter() {
        super(R.layout.item_tweet, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, TweetBean item) {
        ImageLoader.load(mContext, item.getSender().getAvatar(), (ImageView) helper.getView(R.id.iv_sender_avatar));
        helper.setText(R.id.tv_nick, item.getSender().getNick());
        helper.setText(R.id.tv_content, item.getContent());
        NineGridTestLayout layout = helper.getView(R.id.layout_nine_grid);
        layout.setIsShowAll(true);
        layout.setUrlList(getUrlList(item.getImages()));
        mMomentAdapter = new MomentAdapter();
        mMomentRecyclerView = helper.getView(R.id.rv_moments);
        mMomentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mMomentRecyclerView.setAdapter(mMomentAdapter);
        mMomentAdapter.addData(item.getComments());
        mMomentAdapter.notifyDataSetChanged();
    }

    private List<String> getUrlList(List<ImagesBean> list) {
        List<String> urlList = new ArrayList<>();
        for (ImagesBean imagesBean : list) {
            urlList.add(imagesBean.getUrl());
        }
        return urlList;
    }


}
