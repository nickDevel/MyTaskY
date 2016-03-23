package com.example.firstyalantistask;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
        private Context context; //[Comment]  wrong name
        private List<String> imagesLinks; //[Comment] Wrong name
        private MyToaster myToaster; //[Comment] wrong name

    public RecyclerViewAdapter(List<String> imagesLinks, MyToaster myToaster) {
        this.imagesLinks = imagesLinks;
        this.myToaster = myToaster;
    }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            private ImageView image; //[Comment] Wrong name

            public MyViewHolder(View view) {
                super(view);
                image = (ImageView) view.findViewById(R.id.imageView);
                myToaster.addClickListenerToViews(image);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_layout, parent, false);
            context = parent.getContext();
            return new MyViewHolder(itemView);
        }

        //Get display size, load with Picasso loader link and resize image to 1/2 of screen size
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x/2;
            String link = imagesLinks.get(position);
            ImageView image = holder.image;
            Picasso.with(context).load(link).resize(width,width).into(image);
        }

        @Override
        public int getItemCount() {
            return imagesLinks.size();
        }
    }