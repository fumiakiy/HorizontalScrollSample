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

    private String[] items;

    private GestureDetector gestureDetector;

    private void initItems() {
        items = new String[10];
        for (int i = 0; i < 10; i++)
            items[i] = String.format("Item %d", i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            items = savedInstanceState.getStringArray("ITEMS");

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
        outState.putStringArray("ITEMS", items);
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
            String item = items[position];
            Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent event) {
        // we don't use this event
    }
}
