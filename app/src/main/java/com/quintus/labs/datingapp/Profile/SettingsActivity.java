package com.quintus.labs.datingapp.Profile;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.clmca.labs.datingapp.R;
import com.quintus.labs.datingapp.service.api.ApiService;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";
    SeekBar distance;
    SwitchCompat man, woman;
    RangeSeekBar rangeSeekBar;
    TextView gender, distance_text, age_rnge;
    private int distanceKm;

    private List<Gender> genderToShows;

    private int minAge;
    private int maxAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        TextView toolbar = findViewById(R.id.toolbartag);
        toolbar.setText("Profile");
        ImageButton back = findViewById(R.id.back);
        distance = findViewById(R.id.distance);
        man = findViewById(R.id.switch_man);
        woman = findViewById(R.id.switch_woman);
        distance_text = findViewById(R.id.distance_text);
        age_rnge = findViewById(R.id.age_range);
        rangeSeekBar = findViewById(R.id.rangeSeekbar);

        genderToShows = new ArrayList<>();


        distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distanceKm = progress;
                distance_text.setText(progress + " Km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                man.setChecked(isChecked);

                genderToShows.removeIf(e -> e.equals(Gender.MALE));
                if (isChecked) {
                    genderToShows.add(Gender.MALE);
                }

            }
        });
        woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                woman.setChecked(isChecked);

                genderToShows.removeIf(e -> e.equals(Gender.FEMALE));
                if (isChecked) {
                    genderToShows.add(Gender.FEMALE);
                }
            }
        });
        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                age_rnge.setText(minValue + "-" + maxValue);
                minAge = (int) minValue;
                maxAge = (int) maxValue;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();
            }
        });

        ApiService.apiService.getProfile().enqueue(new Callback<ProfileResponse>() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse body = response.body();

                if (body != null) {


                    if (body.getGenderToShow() != null) {
                        genderToShows = body.getGenderToShow();
                        if (genderToShows.contains(Gender.MALE)) {
                            man.setChecked(true);
                        }

                        if (genderToShows.contains(Gender.FEMALE)) {
                            woman.setChecked(true);
                        }
                    }


                    distanceKm = body.getDistance();

                    distance.setProgress(distanceKm);
                    minAge = body.getMinAge();
                    maxAge = body.getMaxAge();
                    rangeSeekBar.setSelectedMinValue(minAge);
                    rangeSeekBar.setSelectedMaxValue(maxAge);
                    age_rnge.setText(minAge + "-" + maxAge);

                }

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(SettingsActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SaveProfileRequest request = SaveProfileRequest.builder()
                .genderToShow(genderToShows)
                .distance(distanceKm)
                .minAge(minAge)
                .maxAge(maxAge)
                .build();

        ApiService.apiService.saveProfile(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SettingsActivity.this, "Update setting success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SettingsActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
