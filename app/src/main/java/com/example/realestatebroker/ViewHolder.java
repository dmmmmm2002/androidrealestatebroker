package com.example.realestatebroker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mTVRealEstateName, mTVPrice, mTVLocation, mTVImageLink, mTVRooms, mTVDesc;
    View mView;
    ImageView mIVImage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

        mTVRealEstateName = itemView.findViewById(R.id.idTVRealEstateName);
        mTVPrice = itemView.findViewById(R.id.idTVPrice);
//        mTVLocation = itemView.findViewById(R.id.idTVLocation);
//        mTVImageLink = itemView.findViewById(R.id.idTVImageLink);
//        mTVRooms = itemView.findViewById(R.id.idTVRooms);
//        mTVDesc = itemView.findViewById(R.id.idTVDesc);
        mIVImage = itemView.findViewById(R.id.idIVRealEstate);

    }

    private ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
