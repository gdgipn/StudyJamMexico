package com.androidtalks.recyclerviewandroidlab;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidtalks.recyclerviewandroidlab.adapters.CustomRecyclerAdapter;
import com.androidtalks.recyclerviewandroidlab.interfaces.OnRecyclerViewItemListener;
import com.androidtalks.recyclerviewandroidlab.model.RecyclerAdapterItem;
import com.androidtalks.recyclerviewandroidlab.util.RecyclerViewItemListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements OnRecyclerViewItemListener {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        this.recyclerView.addOnItemTouchListener(new RecyclerViewItemListener(MainActivity.this,MainActivity.this));
        setUpRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_linear:
                setLinearLayoutManager();
            return true;
            case R.id.action_grid:
            setGridLayoutManager();
                return true;
            case R.id.action_staggered:
                setStaggeredGridLayoutManager();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setUpRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<RecyclerAdapterItem> items = createItemsForRecyclerView();
        CustomRecyclerAdapter customRecyclerAdapter = new CustomRecyclerAdapter(items);

        recyclerView.setAdapter(customRecyclerAdapter);
    }

    private ArrayList<RecyclerAdapterItem> createItemsForRecyclerView(){

        ArrayList<RecyclerAdapterItem> items = new ArrayList<>();
        for (int index = 0; index <30 ; index++) {
                items.add(new RecyclerAdapterItem("Item: " + index,"Index: " + index));
        }
        return items;
    }

    private void setGridLayoutManager(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

    }
    private void setStaggeredGridLayoutManager(){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    private void setLinearLayoutManager(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onRecyclerViewItemClick(View view, int position) {
        Toast.makeText(MainActivity.this,"Position: " + position,Toast.LENGTH_SHORT ).show();
    }
}
