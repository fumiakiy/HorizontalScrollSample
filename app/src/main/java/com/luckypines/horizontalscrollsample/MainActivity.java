package com.luckypines.horizontalscrollsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView1;

    private String[] items;

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

        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("ITEMS", items);
    }

    private void initView() {
        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(new ItemAdapter(items));
    }

}
