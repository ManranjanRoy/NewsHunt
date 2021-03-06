package com.manoranjan.newshunt1.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manoranjan.newshunt1.Activity.SingleNewsActivity;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.StaticData;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AdslistAdaptor extends RecyclerView.Adapter<AdslistAdaptor.ImageViewHolder> {
    private Context mContext;
    private List<NewsListModel> mUploads;

    public AdslistAdaptor(Context mContext, List<NewsListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_ads, parent, false);
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
        holder.imgca.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ads));

        if (uploadCurrent.getName().length() > 100) {
            holder.txtcatname.setText(uploadCurrent.getName().substring(0, 100) + "...");
        } else {
            holder.txtcatname.setText(uploadCurrent.getName());
        }
    /*if (uploadCurrent.getWishlist().equals("0")){
        holder.pwishlist.setImageResource(R.drawable.wishlistoff);
    }else{
        holder.pwishlist.setImageResource(R.drawable.wishliston);
    }*/
    /*holder.pwishlist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (uploadCurrent.getWishlist().equals("0")){
                uploadCurrent.setWishlist("1");
                holder.pwishlist.setImageResource(R.drawable.wishlistoff);
            }else{
                uploadCurrent.setWishlist("0");
                holder.pwishlist.setImageResource(R.drawable.wishliston);
            }
        }
    });*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(mContext, SingleNewsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //i.putExtra("url",uploadCurrent.getId());
                StaticData.videocode = "mHZaQUK8eR8";
                mContext.startActivity(i);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView txtcatname, pprice, oprice, poffer;
        ImageView imgca, pwishlist;
        RelativeLayout ll;

        public ImageViewHolder(View itemView) {
            super(itemView);
            txtcatname = itemView.findViewById(R.id.pname);
            imgca = itemView.findViewById(R.id.pimg);
            //ll=itemView.findViewById(R.id.rr);


        }


    }


}