package com.quintus.labs.datingapp.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clmca.labs.datingapp.R;


public class ProfileCheckinMain extends AppCompatActivity {
    String profileImageUrl;
    String moreImageUr;
    private Button btnMoreImg, btnbeforeImg;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_checkin_main);

        mContext = ProfileCheckinMain.this;
        TextView profileName = findViewById(R.id.name_main);
        //
        ImageView profileImage = findViewById(R.id.profileImage);
        ImageView moreImage = findViewById(R.id.profileImage);
        //

        TextView profileBio = findViewById(R.id.bio_beforematch);
        TextView profileInterest = findViewById(R.id.interests_beforematch);
        TextView profileDistance = findViewById(R.id.distance_main);
        //
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String bio = intent.getStringExtra("bio");
        String interest = intent.getStringExtra("interest");
        double distance = intent.getDoubleExtra("distance", 1);
        String append = (distance == 1) ? "mile away" : "miles away";

        profileDistance.setText(distance + " " + append);
        profileName.setText(name);
        profileBio.setText(bio);
        profileInterest.setText(interest);

        profileImageUrl = intent.getStringExtra("photo");
        moreImageUr = intent.getStringExtra("moreimage");
        switch (profileImageUrl) {
            case "defaultFemale":
                Glide.with(mContext).load(R.drawable.default_woman).into(profileImage);
                break;
            case "defaultMale":
                Glide.with(mContext).load(R.drawable.default_man).into(profileImage);
                break;
            default:
                Glide.with(mContext).load(profileImageUrl).into(profileImage);
                break;
        }
        //more image
        btnMoreImg = (Button) findViewById(R.id.moreImg);
        btnMoreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mContext).load(moreImageUr).into(moreImage);
            }
        });
        //xem ảnh trước
        btnbeforeImg = (Button) findViewById(R.id.beforeImg);
        btnbeforeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mContext).load(profileImageUrl).into(profileImage);
            }
        });


    }


    public void DislikeBtn(View v) {

        Intent btnClick = new Intent(mContext, BtnDislikeActivity.class);
        btnClick.putExtra("url", profileImageUrl);
        startActivity(btnClick);

    }

    public void LikeBtn(View v) {
        Intent btnClick = new Intent(mContext, BtnLikeActivity.class);
        btnClick.putExtra("url", profileImageUrl);
        startActivity(btnClick);

    }

    public void onCloseClicked(View view) {
        finish();

    }
}
