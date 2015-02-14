package com.androidtalks.recyclerviewandroidlab.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.androidtalks.recyclerviewandroidlab.interfaces.OnRecyclerViewItemListener;


/**
 * Created by leonelmendez on 15/12/14.
 */
public class RecyclerViewItemListener implements RecyclerView.OnItemTouchListener {

    private OnRecyclerViewItemListener onRecyclerViewItemListener;
    private GestureDetector gestureDetector;



    public RecyclerViewItemListener(Context context){
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }


    public RecyclerViewItemListener(Context context,OnRecyclerViewItemListener onRecyclerViewItemListener){
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View item = rv.findChildViewUnder(e.getX(),e.getY());

        if(item != null && onRecyclerViewItemListener != null && gestureDetector.onTouchEvent(e)){
            onRecyclerViewItemListener.onRecyclerViewItemClick(item,rv.getChildPosition(item));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener onRecyclerViewItemListener) {
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
    }
}
