package com.john.wechatmoments.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.john.wechatmoments.R;
import com.john.wechatmoments.component.ImageLoader;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.TweetBean.ImagesBean;
import com.john.wechatmoments.util.DateUtil;
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
        ImageLoader.load(mContext, item.getSender().getAvatar(), (ImageView) helper.getView(R.id.iv_sender_avatar), R.drawable.ic_avatar);
        helper.setText(R.id.tv_nick, item.getSender().getNick());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setText(R.id.tv_time, DateUtil.formatTime2String(System.currentTimeMillis()));
        NineGridTestLayout layout = helper.getView(R.id.layout_nine_grid);
        if(item.getImages() != null){
            layout.setVisibility(View.VISIBLE);
            layout.setIsShowAll(true);
            layout.setUrlList(getUrlList(item.getImages()));
        }else {
            layout.setVisibility(View.GONE);
        }
        mMomentRecyclerView = helper.getView(R.id.rv_moments);
        if (item.getComments() != null) {
            mMomentRecyclerView.setVisibility(View.VISIBLE);
            mMomentAdapter = new MomentAdapter(item.getComments());
            mMomentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mMomentRecyclerView.setAdapter(mMomentAdapter);
        }else {
            mMomentRecyclerView.setVisibility(View.GONE);
        }
    }

    private List<String> getUrlList(List<ImagesBean> list) {
        List<String> urlList = new ArrayList<>();
        if (list != null) {
            for (ImagesBean imagesBean : list) {
                urlList.add(imagesBean.getUrl());
            }
        }
        return urlList;
    }


}
