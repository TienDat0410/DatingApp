package com.quintus.labs.datingapp.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.clmca.labs.datingapp.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.quintus.labs.datingapp.Profile.ProfileResponse;
import com.quintus.labs.datingapp.Utils.PulsatorLayout;
import com.quintus.labs.datingapp.Utils.TopNavigationViewHelper;
import com.quintus.labs.datingapp.chat.PushMessageRequest;
import com.quintus.labs.datingapp.service.FindSuitablePersonRequest;
import com.quintus.labs.datingapp.service.PageResponse;
import com.quintus.labs.datingapp.service.api.ApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity implements LocationListener {
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 1;
    final private int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    protected Double latitude, longitude;
    List<Cards> rowItems;
    FrameLayout cardFrame, moreFrame;
    int cc = 0;
    private Context mContext = MainActivity.this;
    private PhotoAdapter arrayAdapter;

    private SwipeFlingAdapterView flingContainer;
    private ImageButton dislikeBtn, likeBtn;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // button
        dislikeBtn = findViewById(R.id.dislikebtn);
        likeBtn = findViewById(R.id.likebtn);


        dislikeBtn.setOnClickListener(v -> {
            flingContainer.getTopCardListener().selectLeft();
        });


        likeBtn.setOnClickListener(v -> {
            flingContainer.getTopCardListener().selectRight();
        });


        cardFrame = findViewById(R.id.card_frame);
        moreFrame = findViewById(R.id.more_frame);
        // start pulsator
        PulsatorLayout mPulsator = findViewById(R.id.pulsator);
        mPulsator.start();

        setupTopNavigationView();

        rowItems = new ArrayList<Cards>();

        arrayAdapter = new PhotoAdapter(this, R.layout.item, rowItems);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        checkRowItem();
        updateSwipeCard();
    }

    private void checkRowItem() {
        if (rowItems.isEmpty()) {
            moreFrame.setVisibility(View.VISIBLE);
            cardFrame.setVisibility(View.GONE);
        } else {
            moreFrame.setVisibility(View.GONE);
            cardFrame.setVisibility(View.VISIBLE);
        }
    }

    private void updateLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        updateLocation();
                    } else {
                        Toast.makeText(MainActivity.this, "Quyền Vị trí bị Từ chối. Bạn phải cấp quyền cho inorder để biết phạm vi người dùng ", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    private void updateSwipeCard() {
        flingContainer = findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                if (rowItems.size() > 0) {
                    rowItems.remove(0);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                checkRowItem();
                Toast.makeText(getApplicationContext(), "dislike", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;

                //check matches
                checkRowItem();

                PushMessageRequest request = PushMessageRequest.builder()
                        .targetId(obj.getUserId())
                        .message("Like!!!")
                        .build();

                ApiService.apiService.sendMessage(request).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "like success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "like error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here


            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();


                if (view != null) {
                    TextView rightText = view.findViewById(R.id.item_swipe_right_indicator2);
                    if (rightText != null) {
                        rightText.setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                    }

                    TextView leftText = view.findViewById(R.id.item_swipe_left_indicator2);

                    if (leftText != null) {
                        leftText.setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                    }


                }

            }
        });


// Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener((i, o) -> {

            System.out.println("item click listener from main");

            //flingContainer.getTopCardListener().selectLeft();
        });


    }


    /**
     * setup top tool bar
     */
    private void setupTopNavigationView() {
        Log.d(TAG, "setupTopNavigationView: setting up TopNavigationView");
        BottomNavigationViewEx tvEx = findViewById(R.id.topNavViewBar);
        TopNavigationViewHelper.setupTopNavigationView(tvEx);
        TopNavigationViewHelper.enableNavigation(mContext, tvEx);
        Menu menu = tvEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {


        latitude = location.getLatitude();
        longitude = location.getLongitude();

        if (rowItems.size() <= 3) {
            getSuitablePartner(latitude, longitude);
            Toast.makeText(getApplicationContext(), "get data from server time " + cc++, Toast.LENGTH_SHORT).show();
        }
    }

    public void getSuitablePartner(double lat, double lon) {

        FindSuitablePersonRequest request = new FindSuitablePersonRequest();
        request.setLat(lat);
        request.setLon(lon);


        ApiService.apiService.getSuitablePartner(request).enqueue(new Callback<PageResponse<ProfileResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PageResponse<ProfileResponse>> call, Response<PageResponse<ProfileResponse>> response) {
                PageResponse<ProfileResponse> body = response.body();
                List<ProfileResponse> list = body.getList();
                for (ProfileResponse profile : list) {
                    Cards cards
                            = new Cards();
                    cards.setUserId(profile.getUsername());
                    cards.setName(profile.getFullName());

                    String bio = "About: " + profile.getAbout() + "\nDescription: "
                            + profile.getCompany() + "\nJob: "
                            + profile.getJobDescription() + "\nSchool: " + profile.getSchool();

                    cards.setPictures(profile.getPictures());

                    cards.setBio(bio);
                    cards.setProfileImageUr(profile.getAvatar());
                    cards.setMoreImageUr(profile.getAvatar());
                    cards.setInterest("Interest: " + profile.getPassions().stream().map(Object::toString)
                            .collect(Collectors.joining(", ")));

                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    cards.setAge(year - profile.getYearOfBirth());

                    double v = distance(latitude, longitude, profile.getLatitude(), profile.getLongitude(), 'K');

                    cards.setDistance(v);

                    rowItems.add(cards);

                }

                arrayAdapter = new PhotoAdapter(MainActivity.this, R.layout.item, rowItems);

                checkRowItem();
                updateSwipeCard();

            }

            @Override
            public void onFailure(Call<PageResponse<ProfileResponse>> call, Throwable t) {
                System.out.println("false");
            }
        });


    }

    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
