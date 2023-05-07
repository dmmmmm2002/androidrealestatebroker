package com.example.realestatebroker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
    int lastPos = -1;

    MainActivity mainActivity;
    List<RealEstateRVModel> modelList;
    Context context;

    public CustomAdapter(MainActivity mainActivity, List<RealEstateRVModel> modelList) {
        this.mainActivity = mainActivity;
        this.modelList = modelList;
    }

//    public void setAnimation(View itemView, int position){
//        if(position > lastPos){
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//            itemView.setAnimation(animation);
//            lastPos = position;
//        }
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                String name = modelList.get(position).getRealEstateName();
//                String price = modelList.get(position).getRealEstatePrice();
//                Toast.makeText(mainActivity, name + "\n" + price, Toast.LENGTH_SHORT).show();

//                setAnimation(viewHolder.mView, position);
                String id = modelList.get(position).getid();
                String realEstateName = modelList.get(position).getRealEstateName();
                String realEstatePrice = modelList.get(position).getRealEstatePrice();
                String realEstateLocation = modelList.get(position).getRealEstateLocation();
                String realEstateImageLink = modelList.get(position).getRealEstateImageLink();
                String realEstateRooms = modelList.get(position).getRealEstateRooms();
                String realEstateDesc = modelList.get(position).getRealEstateDesc();
                Intent intent = new Intent(mainActivity, DetailsRealEstateActivity.class);
                intent.putExtra("pId", id);
                intent.putExtra("pRealEstateName", realEstateName);
                intent.putExtra("pRealEstatePrice", realEstatePrice);
                intent.putExtra("pRealEstateLocation", realEstateLocation);
                intent.putExtra("pRealEstateImageLink", realEstateImageLink);
                intent.putExtra("pRealEstateRooms", realEstateRooms);
                intent.putExtra("pRealEstateDesc", realEstateDesc);

                mainActivity.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                String[] options = {"Update/Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which == 0){
                            String id = modelList.get(position).getid();
                            String realEstateName = modelList.get(position).getRealEstateName();
                            String realEstatePrice = modelList.get(position).getRealEstatePrice();
                            String realEstateLocation = modelList.get(position).getRealEstateLocation();
                            String realEstateImageLink = modelList.get(position).getRealEstateImageLink();
                            String realEstateRooms = modelList.get(position).getRealEstateRooms();
                            String realEstateDesc = modelList.get(position).getRealEstateDesc();
                            Intent intent = new Intent(mainActivity, EditRealEstateActivity.class);
                            intent.putExtra("pId", id);
                            intent.putExtra("pRealEstateName", realEstateName);
                            intent.putExtra("pRealEstatePrice", realEstatePrice);
                            intent.putExtra("pRealEstateLocation", realEstateLocation);
                            intent.putExtra("pRealEstateImageLink", realEstateImageLink);
                            intent.putExtra("pRealEstateRooms", realEstateRooms);
                            intent.putExtra("pRealEstateDesc", realEstateDesc);
                            mainActivity.startActivity(intent);
                        }
//                        if (which == 1){
//
//                        }
                    }
                }).create().show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTVRealEstateName.setText(modelList.get(i).getRealEstateName());
        viewHolder.mTVPrice.setText(modelList.get(i).getRealEstatePrice() + '€');
//        viewHolder.mTVLocation.setText(modelList.get(i).getRealEstateLocation());
//        viewHolder.mTVImageLink.setText(modelList.get(i).getRealEstateImageLink());
//        viewHolder.mTVRooms.setText(modelList.get(i).getRealEstateRooms());
//        viewHolder.mTVDesc.setText(modelList.get(i).getRealEstateDesc());
        Picasso.get().load(modelList.get(i).getRealEstateImageLink().toString().trim()).into(viewHolder.mIVImage);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
