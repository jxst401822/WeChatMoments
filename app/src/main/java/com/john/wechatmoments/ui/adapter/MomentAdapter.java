package com.john.wechatmoments.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.john.wechatmoments.R;
import com.john.wechatmoments.model.bean.TweetBean.CommentsBean;

import java.util.List;

public class MomentAdapter extends BaseQuickAdapter<CommentsBean, BaseViewHolder> {

    public MomentAdapter(List<CommentsBean> commentsBeans) {
        super(R.layout.item_comment, commentsBeans);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommentsBean item) {
        helper.setText(R.id.tv_commenter, item.getSender().getNick() + ":");
        helper.setText(R.id.tv_comment_content, item.getContent());
    }
}
