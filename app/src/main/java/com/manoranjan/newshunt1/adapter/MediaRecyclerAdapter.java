package com.manoranjan.newshunt1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.manoranjan.newshunt1.Activity.MediaObject;
import com.manoranjan.newshunt1.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MediaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private ArrayList<MediaObject> mediaObjects;
  private RequestManager requestManager;

  Context context;

  public MediaRecyclerAdapter(ArrayList<MediaObject> mediaObjects,
                              RequestManager requestManager,Context context) {
    this.mediaObjects = mediaObjects;
    this.requestManager = requestManager;
    this.context=context;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new PlayerViewHolder(
        LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.item_videos, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    ((PlayerViewHolder) viewHolder).onBind(mediaObjects.get(i), requestManager,context);
  }

  @Override
  public int getItemCount() {
    return mediaObjects.size();
  }
}
