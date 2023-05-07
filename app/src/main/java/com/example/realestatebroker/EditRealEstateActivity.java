package com.example.realestatebroker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.UUID;

public class EditRealEstateActivity extends AppCompatActivity {

    private TextInputEditText realEstateNameEdt, realEstatePriceEdt, realEstateLocationEdt, realEstateImageLinkEdt, realEstateRoomsEdt;
    private Button updateRealEstateBtn;
    private Button deleteRealEstateBtn;
    private ProgressBar loadingPB;
    private TextView realEstateDescEdt;
    private FirebaseFirestore firestore;

    private RealEstateRVModel realEstateRVModel;

    String pId, pRealEstateName, pRealEstatePrice, pRealEstateLocation, pRealEstateImageLink, pRealEstateRooms, pRealEstateDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_real_estate);
        firestore = FirebaseFirestore.getInstance();
        realEstateNameEdt = findViewById(R.id.idEdtRealEstateName);
        realEstatePriceEdt = findViewById(R.id.idEdtRealEstatePrice);
        realEstateLocationEdt = findViewById(R.id.idEdtRealEstateLocation);
        realEstateImageLinkEdt = findViewById(R.id.idEdtRealEstateImageLink);
        realEstateRoomsEdt = findViewById(R.id.idEdtRealEstateRooms);
        realEstateDescEdt = findViewById(R.id.idEdtRealEstateDesc);
        updateRealEstateBtn = findViewById(R.id.idBtnUpdateRealEstate);
        deleteRealEstateBtn = findViewById(R.id.idBtnDeleteRealEstate);
        loadingPB = findViewById(R.id.idPBLoading);

        updateRealEstateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = pId;
                String realEstateName = realEstateNameEdt.getText().toString().trim();
                String realEstatePrice = realEstatePriceEdt.getText().toString().trim();
                String realEstateLocation = realEstateLocationEdt.getText().toString().trim();
                String realEstateImageLink = realEstateImageLinkEdt.getText().toString().trim();
                String realEstateRooms = realEstateRoomsEdt.getText().toString().trim();
                String realEstateDesc = realEstateDescEdt.getText().toString().trim();

                RealEstateRVModel realEstateRVModel = new RealEstateRVModel(id, realEstateName, realEstatePrice, realEstateLocation, realEstateImageLink, realEstateRooms, realEstateDesc);

                if(TextUtils.isEmpty(realEstateName) || TextUtils.isEmpty(realEstatePrice) || TextUtils.isEmpty(realEstateLocation) || TextUtils.isEmpty(realEstateImageLink) || TextUtils.isEmpty(realEstateRooms) || TextUtils.isEmpty(realEstateDesc)){
                    Toast.makeText(EditRealEstateActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    firestore.collection("RealEstatesTest").document(id)
                            .update("realEstateName", realEstateName, "realEstatePrice", realEstatePrice, "realEstateLocation", realEstateLocation, "realEstateImageLink", realEstateImageLink, "realEstateRooms", realEstateRooms, "realEstateDesc", realEstateDesc)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditRealEstateActivity.this, MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        deleteRealEstateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = pId;
                firestore.collection("RealEstatesTest").document(id).delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EditRealEstateActivity.this, MainActivity.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            //get data
            pId = bundle.getString("pId");
            pRealEstateName = bundle.getString("pRealEstateName");
            pRealEstatePrice = bundle.getString("pRealEstatePrice");
            pRealEstateLocation = bundle.getString("pRealEstateLocation");
            pRealEstateImageLink = bundle.getString("pRealEstateImageLink");
            pRealEstateRooms = bundle.getString("pRealEstateRooms");
            pRealEstateDesc = bundle.getString("pRealEstateDesc");
            //set data
            realEstateNameEdt.setText(pRealEstateName);
            realEstatePriceEdt.setText(pRealEstatePrice);
            realEstateLocationEdt.setText(pRealEstateLocation);
            realEstateImageLinkEdt.setText(pRealEstateImageLink);
            realEstateRoomsEdt.setText(pRealEstateRooms);
            realEstateDescEdt.setText(pRealEstateDesc);
        }
        else{

        }
    }

}