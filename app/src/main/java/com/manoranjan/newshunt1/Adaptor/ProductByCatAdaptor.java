package com.manoranjan.newshunt1.Adaptor;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manoranjan.newshunt1.Model.Common;
import com.manoranjan.newshunt1.Model.SubCatagoryModel;
import com.manoranjan.newshunt1.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProductByCatAdaptor extends RecyclerView.Adapter<ProductByCatAdaptor.ImageViewHolder> {
private Context mContext;
private List<SubCatagoryModel> mUploads;
    private  OnitemClickListner mlistner;
    int row_index=0;
    public interface  OnitemClickListner{

        void OnItemCLiCK(int position);
        void OnCartCLiCK(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }
public ProductByCatAdaptor(Context mContext, List<SubCatagoryModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_catagoryproduct, parent, false);
        return new ImageViewHolder(v,mlistner);
        }

    @Override
        public void onBindViewHolder(final ImageViewHolder holder, final int position) {

            final SubCatagoryModel uploadCurrent = mUploads.get(position);
            Log.d("check",uploadCurrent.getName());
            // holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getCode()));
            holder.txtcatname.setText(uploadCurrent.getName());
            //  holder.txtdesc.setText(uploadCurrent.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                Common.subCatagoryModel=mUploads.get(position);
                // Toast.makeText(mContext,"hi",Toast.LENGTH_LONG).show();
                notifyDataSetChanged();

            }
        });
        if (row_index==position){
            holder.txtcatname.setBackground(mContext.getResources().getDrawable(R.drawable.add_button_border));
           // holder.txtcatname.setBackgroundColor(android.graphics.Color.parseColor("#ff0000"));
            holder.txtcatname.setTextColor(Color.WHITE);
        }
        else{
            holder.txtcatname.setBackground(mContext.getResources().getDrawable(R.drawable.add_button_border1));
           // holder.txtcatname.setBackgroundColor(android.graphics.Color.parseColor("#ff0000"));
            holder.txtcatname.setTextColor(Color.BLACK);
        }

        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView txtcatname;

    public ImageViewHolder(View itemView, final OnitemClickListner listner) {
        super(itemView);
        txtcatname = itemView.findViewById(R.id.subcate);
        //txtdesc=itemView.findViewById(R.id.subdesc);
        //ll=itemView.findViewById(R.id.rr);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner !=null){
                    int position =getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listner.OnItemCLiCK(position);
                    }
                }
            }
        });

    }


}


}