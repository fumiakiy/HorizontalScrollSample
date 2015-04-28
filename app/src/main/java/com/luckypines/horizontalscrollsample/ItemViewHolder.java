package com.luckypines.horizontalscrollsample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by fumiaki on 4/28/15.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    protected TextView text1;
    public ItemViewHolder(View itemView) {
        super(itemView);
        text1 = (TextView)itemView.findViewById(R.id.text1);
    }

    public TextView getText1() {
        return text1;
    }
}
