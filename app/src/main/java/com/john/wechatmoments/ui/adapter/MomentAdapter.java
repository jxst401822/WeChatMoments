package com.john.wechatmoments.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.john.wechatmoments.R;
import com.john.wechatmoments.model.bean.TweetBean.CommentsBean;

public class MomentAdapter extends BaseQuickAdapter<CommentsBean, BaseViewHolder> {

    public MomentAdapter() {
        super(R.layout.item_comment, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommentsBean item) {
        helper.setText(R.id.tv_commenter, item.getSender().getNick() + ":");
        helper.setText(R.id.tv_comment_content, item.getContent());
    }
}
