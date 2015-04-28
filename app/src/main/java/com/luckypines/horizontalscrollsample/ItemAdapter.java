package com.luckypines.horizontalscrollsample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fumiaki on 4/28/15.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private String[] items;

    public ItemAdapter(String[] items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.length;
        return 0;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_parent, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (items == null)
            return;
        String item = items[position];
        holder.getText1().setText(item);
    }
}
