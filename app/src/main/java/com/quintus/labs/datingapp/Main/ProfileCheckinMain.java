package com.quintus.labs.datingapp.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clmca.labs.datingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProfileCheckinMain extends AppCompatActivity {
    private Context mContext;
    private ImageView imageView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_checkin_main);

        mContext = ProfileCheckinMain.this;
        TextView profileName = findViewById(R.id.name_main);


        TextView profileBio = findViewById(R.id.bio_beforematch);
        TextView profileInterest = findViewById(R.id.interests_beforematch);
        TextView profileDistance = findViewById(R.id.distance_main);
        imageView = findViewById(R.id.profileImage);


        //

        Intent intent = getIntent();

        List<String> pictures = (List<String>) intent.getSerializableExtra("pictures");
        Picasso.get().load(pictures.get(0)).into(imageView);

        imageView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();



                if (x >= 500) {
                    String s = pictures.get(0);
                    pictures.remove(0);
                    pictures.add(s);

                } else {
                    String s = pictures.get(pictures.size() - 1);
                    pictures.remove(pictures.size() - 1);
                    pictures.add(0, s);
                }

                Glide.with(v.getContext()).load(pictures.get(0)).into(imageView);

                //Picasso.get().load(card_item.getPictures().get(0)).into(images);
            }
            return true;
        });

        String name = intent.getStringExtra("name");
        String bio = intent.getStringExtra("bio");
        String interest = intent.getStringExtra("interest");
        double distance = intent.getDoubleExtra("distance", 1);
        String append = (distance == 1) ? "mile away" : "miles away";

        profileDistance.setText(distance + " " + append);
        profileName.setText(name);
        profileBio.setText(bio);
        profileInterest.setText(interest);


    }


    public void onCloseClicked(View view) {
        finish();

    }
}
