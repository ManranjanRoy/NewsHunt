package com.manoranjan.newshunt1.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Activity.SingleVideoActivity;
import com.manoranjan.newshunt1.StaticDtaa.StaticData;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BrowseVideosslistAdaptor extends RecyclerView.Adapter<BrowseVideosslistAdaptor.ImageViewHolder> {
private Context mContext;
private List<NewsListModel> mUploads;
private  String key="AIzaSyBMVLBc2NMWQgoCmwspRcpsVzq06RATqDE";

public BrowseVideosslistAdaptor(Context mContext, List<NewsListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;

        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_videos, parent, false);
        return new ImageViewHolder(v);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final NewsListModel uploadCurrent = mUploads.get(position);

//            String imgurl= "http://buyseller.digitalwebgurukul.com/public/uploaded/"+uploadCurrent.getImage();
//            Picasso.with(mContext)
//                    .load(imgurl)
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .fit()
//                    .into(holder.imgca);
            //holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getImg()));

    if (uploadCurrent.getName().length()>100){
        holder.txtcatname.setText(uploadCurrent.getName().substring(0,100)+"...");
    }else {
        holder.txtcatname.setText(uploadCurrent.getName());
    }
    /*  initialize the thumbnail image view , we need to pass Developer Key */

    holder.youTubeThumbnailView.initialize(StaticData.api_key, new YouTubeThumbnailView.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
            //when initialization is sucess, set the video id to thumbnail to load
            youTubeThumbnailLoader.setVideo("mHZaQUK8eR8");

            youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                @Override
                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                    //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                    youTubeThumbnailLoader.release();
                }

                @Override
                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                    //print or show error when thumbnail load failed
                    Log.e("tag", "Youtube Thumbnail Error");
                }
            });
        }

        @Override
        public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
            //print or show error when initialization failed
            Log.e("TAG", "Youtube Initialization Failure");

        }
    });
    holder.youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
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

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView txtcatname,pprice,oprice,poffer;
    ImageView imgca,pwishlist;
    YouTubeThumbnailView youTubeThumbnailView;
    RelativeLayout ll;
    public ImageViewHolder(View itemView) {
        super(itemView);
        txtcatname = itemView.findViewById(R.id.pname);
        youTubeThumbnailView=itemView.findViewById(R.id.video_thumbnail_image_view);
        //imgca=itemView.findViewById(R.id.pimg);
        //ll=itemView.findViewById(R.id.rr);


    }


}


}