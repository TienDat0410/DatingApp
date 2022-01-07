package com.clmca.labs.datingapp.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clmca.labs.datingapp.R;
import com.clmca.labs.datingapp.Utils.SquareImageView;


public class ProfileCheckinMain extends AppCompatActivity {
    private Button btnMoreImg;
    private Context mContext;
    String profileImageUrl;
    String moreImageUr;
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
        int distance = intent.getIntExtra("distance", 1);
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
        Intent btnClick = new Intent(mContext, MainActivity.class);
        btnClick.putExtra("url", profileImageUrl);
        startActivity(btnClick);
    }
}
