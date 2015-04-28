package com.luckypines.horizontalscrollsample;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fumiaki on 4/28/15.
 */
public class HorizontalItemsViewHolder extends ItemViewHolder {
    RecyclerView recyclerView2;

    public HorizontalItemsViewHolder(View itemView) {
        super(itemView);
        recyclerView2 = (RecyclerView)itemView.findViewById(R.id.recyclerView2);
    }

    @Override
    public void bindView(Object item) {
        String[] items = (String[])item;
        recyclerView2.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setAdapter(new ItemAdapter(items));
    }

    public int getChildItemPosition(float x, float y) {
        View child = recyclerView2.findChildViewUnder(x, y);
        if (child != null)
            return recyclerView2.getChildAdapterPosition(child);
        return -1;
    }
}
