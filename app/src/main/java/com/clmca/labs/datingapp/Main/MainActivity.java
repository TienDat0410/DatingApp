package com.clmca.labs.datingapp.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.clmca.labs.datingapp.Profile.ProfileResponse;
import com.clmca.labs.datingapp.R;
import com.clmca.labs.datingapp.Utils.PulsatorLayout;
import com.clmca.labs.datingapp.Utils.TopNavigationViewHelper;
import com.clmca.labs.datingapp.chat.ChatActivity;
import com.clmca.labs.datingapp.service.FindSuitablePersonRequest;
import com.clmca.labs.datingapp.service.PageResponse;
import com.clmca.labs.datingapp.service.api.ApiService;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity  implements LocationListener {
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 1;
    final private int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    ListView listView;
    List<Cards> rowItems;
    FrameLayout cardFrame, moreFrame;
    private Context mContext = MainActivity.this;
//    private NotificationHelper mNotificationHelper;
    private Cards cards_data[];
    private PhotoAdapter arrayAdapter;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cardFrame = findViewById(R.id.card_frame);
        moreFrame = findViewById(R.id.more_frame);
        // start pulsator
        PulsatorLayout mPulsator = findViewById(R.id.pulsator);
        mPulsator.start();

        setupTopNavigationView();

        rowItems = new ArrayList<Cards>();


        //convert card sang gson
//        Gson gson = new Gson();
//        String strGson = gson.toJson(cards);
//        Log.e("String JSON", strGson);
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
        }else {
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

    private void updateSwipeCard() {
        final SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                checkRowItem();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;

                //check matches
                checkRowItem();

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here


            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
               // view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
               // view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void DislikeBtn(View v) {
        if (rowItems.size() != 0) {
            Cards card_item = rowItems.get(0);

            String userId = card_item.getUserId();

            rowItems.remove(0);
            arrayAdapter.notifyDataSetChanged();

            Intent btnClick = new Intent(mContext, BtnDislikeActivity.class);
            btnClick.putExtra("url", card_item.getProfileImageUr());
            startActivity(btnClick);
        }
    }

    public void LikeBtn(View v) {
        if (rowItems.size() != 0) {
            Cards card_item = rowItems.get(0);

            String userId = card_item.getUserId();

            //check matches

            rowItems.remove(0);
            arrayAdapter.notifyDataSetChanged();

            Intent btnClick = new Intent(mContext, BtnLikeActivity.class);
            btnClick.putExtra("url", card_item.getProfileImageUr());
            startActivity(btnClick);
        }
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

//chat
    public void btnMess(View view) {
        Intent intent = new Intent(mContext, ChatActivity.class);
        Bundle b = new Bundle();

    }

    int cc = 0;

    @Override
    public void onLocationChanged(@NonNull Location location) {


        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        if(rowItems.size() <= 2){
            getSuitablePartner(latitude, longitude);
            Toast.makeText(getApplicationContext(), "get data from server time "+ cc++, Toast.LENGTH_LONG).show();
        }
    }

    public void getSuitablePartner(double lat, double lon){

        FindSuitablePersonRequest request = new FindSuitablePersonRequest();
        request.setLat(lat);
        request.setLon(lon);


        ApiService.apiService.getSuitablePartner(request).enqueue(new Callback<PageResponse<ProfileResponse>>() {
            @Override
            public void onResponse(Call<PageResponse<ProfileResponse>> call, Response<PageResponse<ProfileResponse>> response) {
                PageResponse<ProfileResponse> body = response.body();
                List<ProfileResponse> list = body.getList();
                for (ProfileResponse profileResponse : list) {
                    Cards cards
                            = new Cards();
                    cards.setUserId(profileResponse.getUsername());
                    cards.setName(profileResponse.getFullName());
                    cards.setBio(profileResponse.getAbout());
                    cards.setProfileImageUr(profileResponse.getAvatar());
                    cards.setMoreImageUr(profileResponse.getAvatar());
                    cards.setInterest(profileResponse.getPassions().toArray().toString());

                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    cards.setAge( year - profileResponse.getYearOfBirth());

                    // double v = SloppyMath.haversinMeters(profileResponse.getLatitude(), profileResponse.getLongitude(), 38, -120);
                    cards.setDistance(69);

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
}
