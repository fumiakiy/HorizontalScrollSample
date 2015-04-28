package com.luckypines.horizontalscrollsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    protected RecyclerView recyclerView1;

    private Object[] items;

    private GestureDetector gestureDetector;

    private void initItems() {
        items = new Object[10];
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                String[] children = new String[5];
                for (int j = 0; j < 5; j++)
                    children[j] = String.format("Item %d - %d", i, j);
                items[i] = children;
            }
            else
                items[i] = String.format("Item %d", i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            int length = savedInstanceState.getInt("NUM_ITEMS");
            if (length > 0) {
                items = new Object[length];
                for (int i = 0; i < items.length; i++)
                    items[i] = savedInstanceState.get("ITEM" + i);
            }
        }

        if (items == null)
            initItems();

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (items == null || items.length <= 0)
            outState.putInt("NUM_ITEMS", 0);
        else {
            outState.putInt("NUM_ITEMS", items.length);
            for (int i = 0; i < items.length; i++) {
                if (items[i] instanceof String)
                    outState.putString("ITEM" + i, (String) items[i]);
                else
                    outState.putStringArray("ITEM" + i, (String[]) items[i]);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerView1.removeOnItemTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView1.addOnItemTouchListener(this);
    }

    private void initView() {
        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(new ItemAdapter(items));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = recyclerView1.findChildViewUnder(e.getX(), e.getY());
        if (child != null && gestureDetector.onTouchEvent(e)) {
            int position = recyclerView1.getChildAdapterPosition(child);
            if (items[position] instanceof String)
                Toast.makeText(this, (String)items[position], Toast.LENGTH_SHORT).show();
            else {
                View childCard = recyclerView1.findChildViewUnder(e.getX(), e.getY());
                HorizontalItemsViewHolder horizontalItemsViewHolder = (HorizontalItemsViewHolder) recyclerView1.findViewHolderForAdapterPosition(position);
                int grandChildPosition = horizontalItemsViewHolder.getChildItemPosition(e.getX() - childCard.getX(), e.getY() - childCard.getY());
                if (grandChildPosition < 0)
                    return false;
                String[] grandChildren = (String[])items[position];
                Toast.makeText(this, grandChildren[grandChildPosition], Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent event) {
        // we don't use this event
    }
}
