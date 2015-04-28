package com.luckypines.horizontalscrollsample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fumiaki on 4/28/15.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final static int VIEW_TYPE_STRING = 0;
    private final static int VIEW_TYPE_STRING_ARRAY = 1;

    private Object[] items;

    public ItemAdapter(Object[] items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items[position] instanceof String)
            return VIEW_TYPE_STRING;
        return VIEW_TYPE_STRING_ARRAY;
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.length;
        return 0;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_STRING) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text, parent, false);
            return new StringViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_horizontal_parent, parent, false);
            return new HorizontalItemsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (items == null)
            return;
        holder.bindView(items[position]);
    }
}
