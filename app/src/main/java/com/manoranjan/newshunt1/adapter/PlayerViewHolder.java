package com.manoranjan.newshunt1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.manoranjan.newshunt1.MediaObject;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.SingleVideoActivity;
import com.manoranjan.newshunt1.StaticDtaa.StaticData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created on : May 24, 2019
 * Author     : AndroidWave
 */
public class PlayerViewHolder extends RecyclerView.ViewHolder {

  /**
   * below view have public modifier because
   * we have access PlayerViewHolder inside the ExoPlayerRecyclerView
   */
  public FrameLayout mediaContainer;
  public ImageView mediaCoverImage, volumeControl;
  public ProgressBar progressBar;
  public RequestManager requestManager;
  private TextView title, userHandle;
  private View parent;

  public PlayerViewHolder(@NonNull View itemView) {
    super(itemView);
    parent = itemView;
    mediaContainer = itemView.findViewById(R.id.mediaContainer);
    mediaCoverImage = itemView.findViewById(R.id.ivMediaCoverImage);
    title = itemView.findViewById(R.id.tvTitle);
    userHandle = itemView.findViewById(R.id.tvUserHandle);
    progressBar = itemView.findViewById(R.id.progressBar);
    volumeControl = itemView.findViewById(R.id.ivVolumeControl);
  }

  void onBind(MediaObject mediaObject, RequestManager requestManager, final Context mContext) {
    this.requestManager = requestManager;
    parent.setTag(this);
    title.setText(mediaObject.getTitle());
    userHandle.setText(mediaObject.getUserHandle());
    this.requestManager
        .load(mediaObject.getCoverUrl())
        .into(mediaCoverImage);
    mediaContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i=new Intent(mContext, SingleVideoActivity.class);
        i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        //i.putExtra("url",uploadCurrent.getId());
        StaticData.videocode="mHZaQUK8eR8";
        mContext.startActivity(i);

              }
    });
  }
}

