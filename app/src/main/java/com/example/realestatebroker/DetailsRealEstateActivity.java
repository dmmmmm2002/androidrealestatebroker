package com.example.realestatebroker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class DetailsRealEstateActivity extends AppCompatActivity {

    private TextView realEstateNameTV, realEstatePriceTV, realEstateLocationTV, realEstateRoomsTV, realEstateDescTV;
    private ImageView realEstateIV;
    private ProgressBar loadingPB;

    private RealEstateRVModel realEstateRVModel;
    private Button backBtn;

    NotificationCompat.Builder mBuilder;
    private static final String CHANNEL_ID = "channel_id01";
    private static final int NOTIFICATION_ID = 1;

    String pId, pRealEstateName, pRealEstatePrice, pRealEstateLocation, pRealEstateImageLink, pRealEstateRooms, pRealEstateDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_real_estate);
        realEstateNameTV = findViewById(R.id.idTVRealEstateName);
        realEstatePriceTV = findViewById(R.id.idTVPrice);
        realEstateLocationTV = findViewById(R.id.idTVLocation);
        realEstateRoomsTV = findViewById(R.id.idTVRooms);
        realEstateDescTV = findViewById(R.id.idTVDesc);
        realEstateIV = findViewById(R.id.idIVRealEstate);
        backBtn = findViewById(R.id.idBtnBack);
//        loadingPB = findViewById(R.id.idPBLoading);


        realEstateDescTV.setMovementMethod(new ScrollingMovementMethod());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
                startActivity(new Intent(DetailsRealEstateActivity.this, MainActivity.class) );
                finish();
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
            realEstateNameTV.setText(pRealEstateName);
            realEstatePriceTV.setText(pRealEstatePrice + '€');
            realEstateLocationTV.setText(pRealEstateLocation);
            realEstateRoomsTV.setText(pRealEstateRooms);
            realEstateDescTV.setText(pRealEstateDesc);
            Picasso.get().load(pRealEstateImageLink.toString().trim()).into(realEstateIV);
        }
        else{

        }
    }

    private void showNotification() {
        createNotificationChannel();
        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Take a look");
        mBuilder.setContentText("Check out the other Real Estates!");

        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, mBuilder.build());


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "My Notification";
            String description = "My notification description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}
