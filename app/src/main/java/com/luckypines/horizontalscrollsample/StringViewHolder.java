package com.luckypines.horizontalscrollsample;

import android.view.View;
import android.widget.TextView;

/**
 * Created by fumiaki on 4/28/15.
 */
public class StringViewHolder extends ItemViewHolder {
    protected TextView text1;
    public StringViewHolder(View itemView) {
        super(itemView);
        text1 = (TextView)itemView.findViewById(R.id.text1);
    }

    @Override
    public void bindView(Object item) {
        text1.setText(item.toString());
    }
}
