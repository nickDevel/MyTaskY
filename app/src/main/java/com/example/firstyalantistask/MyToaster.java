package com.example.firstyalantistask;

import android.view.View;
import android.view.ViewGroup;

public class MyToaster implements View.OnClickListener {
    private Toastable toaster;

    public MyToaster(Toastable toaster){
        this.toaster = toaster;
    }

    //calls a method of interface to make toast
    @Override
    public void onClick(View v) {
        toaster.showToast(v.getClass().getSimpleName());
    }
    //add onClickListener For Views array
    void addClickListenerToViews(View...view){
        for(View v : view){
            v.setOnClickListener(this);
        }
    }

    //add onClickListener For Views in array of ViewGroups
    void addClickListenerToViews(ViewGroup...view){
        for(ViewGroup v : view){
            for (int i = 0; i < v.getChildCount(); i++) {
                View child = v.getChildAt(i);
                child.setOnClickListener(this);
            }
        }
    }
}
