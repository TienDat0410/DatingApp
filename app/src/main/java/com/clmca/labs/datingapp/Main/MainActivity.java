package com.clmca.labs.datingapp.Main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.clmca.labs.datingapp.Profile.ProfileResponse;
import com.clmca.labs.datingapp.chat.ChatActivity;
import com.clmca.labs.datingapp.service.FindSuitablePersonRequest;
import com.clmca.labs.datingapp.service.PageResponse;
import com.clmca.labs.datingapp.service.api.ApiService;
import com.google.gson.Gson;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.clmca.labs.datingapp.R;
import com.clmca.labs.datingapp.Utils.PulsatorLayout;
import com.clmca.labs.datingapp.Utils.TopNavigationViewHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity {
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

        Cards cards = new Cards("1", "Diệu Linh", 21, "https://i.pinimg.com/564x/2b/c5/77/2bc57784be1a73c9fac04c1ccc6f8e05.jpg", "Simple and beautiful Girl", "Acting", 200,"https://i.pinimg.com/564x/ea/8e/8f/ea8e8ff659da23dac2d6fc6b8a03eb17.jpg");
        rowItems.add(cards);
        cards = new Cards("2", "Trà Giang", 20, "https://i.pinimg.com/564x/d8/73/c0/d873c0348b9e539ad3cf1d91a6bbf3a5.jpg", "cool Minded Girl", "Dancing", 800, "https://i.pinimg.com/564x/71/80/27/71802767c3eae1dcb63edb901085ecf9.jpg");
        rowItems.add(cards);
        cards = new Cards("3", "Phương Uyên", 22, "https://i.pinimg.com/564x/33/0d/05/330d0506ac93291a89ff1a9002846cdd.jpg", "Simple and beautiful Girl", "Singing", 400, "https://i.pinimg.com/236x/64/ee/9c/64ee9c95cade0dad6fb463b6474fe91c.jpg");
        rowItems.add(cards);
        cards = new Cards("4", "Thuý", 19, "https://i.pinimg.com/564x/a4/cb/75/a4cb75b582a094347972bb8cb87d9857.jpg", "dashing girl", "swiming", 1308, "https://i.pinimg.com/236x/80/9e/da/809edab7523aec0f50ca18091648a356.jpg");
        rowItems.add(cards);
        cards = new Cards("5", "Em đẹp lắm", 20, "https://i.pinimg.com/564x/fb/ba/49/fbba49f0b84465dd4165e24f37d60c48.jpg", "chulbuli nautankibaj ", "Drawing", 1200, "https://i.pinimg.com/236x/72/89/69/7289695b2980252c7e30b4a279ce2c41.jpg");
        rowItems.add(cards);
        cards = new Cards("6", "Hạnh Hạnh", 21, "https://i.pinimg.com/564x/50/95/d6/5095d60043b4cfd6ecd70a4ad1a3e1a9.jpg", "Simple and beautiful Girl", "Sleeping", 700, "https://i.pinimg.com/564x/87/d3/98/87d398f723397c8add8a32537719ba16.jpg");
        rowItems.add(cards);
        cards = new Cards("7", "Thu Kiều", 19, "https://i.pinimg.com/236x/53/9e/f6/539ef67532c43104483446bb3ede715e.jpg", "Papa's Pari", "Art", 5000, "https://i.pinimg.com/564x/27/37/20/27372020ca6311452c34c901aae34372.jpg");
        rowItems.add(cards);
        cards = new Cards("8", "Kiều Trinh", 19, "https://i.pinimg.com/564x/a7/19/7e/a7197e58c8206f47fef25f029d2f13aa.jpg", "suppermen", "Art", 3000, "https://i.pinimg.com/236x/d5/48/82/d54882e71e6cfc18f467f16700851454.jpg");
        rowItems.add(cards);
        cards = new Cards("9", "Suri Suri", 20, "https://i.pinimg.com/564x/64/72/51/64725181ceb2ae26ab38b4cab935674c.jpg", "in4", "Sleep", 4500, "https://i.pinimg.com/236x/b1/c1/41/b1c14177862a3220fa4e8da6f7deb43d.jpg");
        rowItems.add(cards);
        cards = new Cards("10", "Anh đen", 21, "https://i.pinimg.com/236x/60/84/ac/6084ac38965d8e3d8581570dbbe37216.jpg", "in4", "Sleep", 4500, "https://i.pinimg.com/236x/e1/33/27/e1332716ec9877248948dd2a32081603.jpg");
        rowItems.add(cards);
        //convert card sang gson
//        Gson gson = new Gson();
//        String strGson = gson.toJson(cards);
//        Log.e("String JSON", strGson);
        arrayAdapter = new PhotoAdapter(this, R.layout.item, rowItems);


        FindSuitablePersonRequest request = new FindSuitablePersonRequest();
        request.setLat(38);
        request.setLon(-120);


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


                //convert card sang gson
//        Gson gson = new Gson();
//        String strGson = gson.toJson(cards);
//        Log.e("String JSON", strGson);
                arrayAdapter = new PhotoAdapter(MainActivity.this, R.layout.item, rowItems);

                checkRowItem();
                updateSwipeCard();

            }

            @Override
            public void onFailure(Call<PageResponse<ProfileResponse>> call, Throwable t) {
                System.out.println(123);
            }
        });
        checkRowItem();
        updateSwipeCard();
    }

    private void checkRowItem() {
        if (rowItems.isEmpty()) {
            moreFrame.setVisibility(View.VISIBLE);
            cardFrame.setVisibility(View.GONE);
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
}
