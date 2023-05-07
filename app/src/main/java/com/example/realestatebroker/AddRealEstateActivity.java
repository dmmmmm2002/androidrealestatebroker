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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AddRealEstateActivity extends AppCompatActivity {

    private TextInputEditText realEstateNameEdt, realEstatePriceEdt, realEstateLocationEdt, realEstateImageLinkEdt, realEstateRoomsEdt;
    private Button addRealEstateBtn, cancelBtn;
    private ProgressBar loadingPB;
    private TextView realEstateDescEdt;
    private FirebaseFirestore firestore;
    private DatabaseReference databaseReference;

    private String realEstateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_real_estate);

        realEstateNameEdt = findViewById(R.id.idEdtRealEstateName);
        realEstatePriceEdt = findViewById(R.id.idEdtRealEstatePrice);
        realEstateLocationEdt = findViewById(R.id.idEdtRealEstateLocation);
        realEstateImageLinkEdt = findViewById(R.id.idEdtRealEstateImageLink);
        realEstateRoomsEdt = findViewById(R.id.idEdtRealEstateRooms);
        realEstateDescEdt = findViewById(R.id.idEdtRealEstateDesc);
        addRealEstateBtn = findViewById(R.id.idBtnAddRealEstate);
        cancelBtn = findViewById(R.id.idBtnCancel);
        loadingPB = findViewById(R.id.idPBLoading);
        firestore = FirebaseFirestore.getInstance();


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddRealEstateActivity.this, MainActivity.class) );
                finish();
            }
        });


        addRealEstateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String realEstateName = realEstateNameEdt.getText().toString().trim();
                String realEstatePrice = realEstatePriceEdt.getText().toString().trim();
                String realEstateLocation = realEstateLocationEdt.getText().toString().trim();
                String realEstateImageLink = realEstateImageLinkEdt.getText().toString().trim();
                String realEstateRooms = realEstateRoomsEdt.getText().toString().trim();
                String realEstateDesc = realEstateDescEdt.getText().toString().trim();
                String id = UUID.randomUUID().toString();
                RealEstateRVModel realEstateRVModel = new RealEstateRVModel(id, realEstateName, realEstatePrice, realEstateLocation, realEstateImageLink, realEstateRooms, realEstateDesc);

                if(TextUtils.isEmpty(realEstateName) || TextUtils.isEmpty(realEstatePrice) || TextUtils.isEmpty(realEstateLocation) || TextUtils.isEmpty(realEstateImageLink) || TextUtils.isEmpty(realEstateRooms) || TextUtils.isEmpty(realEstateDesc)){
                    Toast.makeText(AddRealEstateActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
//                    Map<String, Object> RealEstate = new HashMap<>();
//                    RealEstate.put("Name", Objects.requireNonNull(realEstateNameEdt.getText()).toString());
//                    RealEstate.put("Price", Objects.requireNonNull(realEstatePriceEdt.getText()).toString());
//                    RealEstate.put("Location", Objects.requireNonNull(realEstateLocationEdt.getText()).toString());
//                    RealEstate.put("ImageLink", Objects.requireNonNull(realEstateImageLinkEdt.getText()).toString());
//                    RealEstate.put("Link", Objects.requireNonNull(realEstateLinkEdt.getText()).toString());
//                    RealEstate.put("Description", realEstateDescEdt.getText().toString());

//                    firestore.collection("RealEstatesTest").add(realEstateRVModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddRealEstateActivity.this, MainActivity.class));
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    firestore.collection("RealEstatesTest").document(id).set(realEstateRVModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddRealEstateActivity.this, MainActivity.class));
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

    }
}