package com.androidtalks.recyclerviewandroidlab.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidtalks.recyclerviewandroidlab.R;
import com.androidtalks.recyclerviewandroidlab.model.RecyclerAdapterItem;

import java.util.ArrayList;

/**
 * Created by leo on 1/25/15.
 */
public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder> {

    private ArrayList<RecyclerAdapterItem> items;

    public CustomRecyclerAdapter(ArrayList<RecyclerAdapterItem> items) {
        this.items = items;
    }

    public CustomRecyclerAdapter(){
        this.items = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout,parent,false);
        return new CustomViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

            RecyclerAdapterItem item = items.get(position);
            holder.firstText.setText(item.getFirstText());
            holder.secondText.setText(item.getSecondText());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView firstText,secondText;


        public CustomViewHolder(View itemView) {
            super(itemView);
            this.firstText = (TextView)itemView.findViewById(R.id.firstText);
            this.secondText = (TextView)itemView.findViewById(R.id.secondText);
        }
    }

}


