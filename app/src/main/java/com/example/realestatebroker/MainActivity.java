package com.example.realestatebroker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private List<RealEstateRVModel> modelList = new ArrayList<>();
    private RecyclerView realEstateRV;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore firestore;
    private CustomAdapter adapter;

    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;

    private ArrayList<RealEstateRVModel> realEstateRVModelArrayList;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingPB = findViewById(R.id.idPBLoading);
        addFAB = findViewById(R.id.idAddFAB);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddRealEstateActivity.class));
            }
        });
        realEstateRV = findViewById(R.id.idRVRealEstates);
        realEstateRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        realEstateRV.setLayoutManager(layoutManager);

        showData();

    }

    private void showData(){
        firestore.collection("RealEstatesTest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc: task.getResult()){
                            RealEstateRVModel model = new RealEstateRVModel(doc.getString("id"),
                                    doc.getString("realEstateName"),
                                    doc.getString("realEstatePrice"),
                                    doc.getString("realEstateLocation"),
                                    doc.getString("realEstateImageLink"),
                                    doc.getString("realEstateRooms"),
                                    doc.getString("realEstateDesc")
                                    );
                            modelList.add(model);
                        }
                        adapter = new CustomAdapter(MainActivity.this, modelList);
                        realEstateRV.setAdapter(adapter);
                        loadingPB.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.idLogOut:
                Toast.makeText(this, "User Signed out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}