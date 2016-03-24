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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
  private Context mActivity;
  private List<String> mListImagesLinks;

  public RecyclerViewAdapter(List<String> imagesLinks) {
    mListImagesLinks = imagesLinks;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
	.inflate(R.layout.image_layout, parent, false);
    mActivity = parent.getContext();
    return new MyViewHolder(itemView);
  }

  // Get display size, load with Picasso loader link and resize image to 1/2 of screen size
  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int width = size.x / 2;
    String link = mListImagesLinks.get(position);
    ImageView image = holder.mImageView;
    Picasso.with(mActivity).load(link).resize(width, width).into(image);
  }

  @Override
  public int getItemCount() {
    return mListImagesLinks.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;

    public MyViewHolder(View view) {
      super(view);
      mImageView = (ImageView) view.findViewById(R.id.imageView);
      mImageView.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
	  Toast.makeText(mActivity, v.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
	}
      });
    }
  }


}