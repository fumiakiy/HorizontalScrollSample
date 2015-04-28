package com.luckypines.horizontalscrollsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fumiaki on 4/28/15.
 */
public abstract class ItemViewHolder extends RecyclerView.ViewHolder {
    public ItemViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(Object item);
}
