package com.clmca.labs.datingapp.Matched;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.clmca.labs.datingapp.R;
import com.clmca.labs.datingapp.Utils.TopNavigationViewHelper;
import com.clmca.labs.datingapp.Utils.User;

import java.util.ArrayList;
import java.util.List;


public class Matched_Activity extends AppCompatActivity {

    private static final String TAG = "Matched_Activity";
    private static final int ACTIVITY_NUM = 2;
    List<Users> matchList = new ArrayList<>();
    List<User> copyList = new ArrayList<>();
    private Context mContext = Matched_Activity.this;
    private String userId, userSex, lookforSex;
    private double latitude = 37.349642;
    private double longtitude = -121.938987;
    private EditText search;
    private List<Users> usersList = new ArrayList<>();
    private RecyclerView recyclerView, mRecyclerView;
    private ActiveUserAdapter adapter;
    private MatchUserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);

        setupTopNavigationView();
        searchFunc();


        recyclerView = findViewById(R.id.active_recycler_view);
        mRecyclerView = findViewById(R.id.matche_recycler_view);

        adapter = new ActiveUserAdapter(usersList, getApplicationContext());
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareActiveData();

        mAdapter = new MatchUserAdapter(matchList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager1);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareMatchData();


    }

    private void prepareActiveData() {
        Users users = new Users("1", "Ali Al-Adba", 35, "https://baoquocte.vn/stores/news_dataimages/thanhtruc/122021/25/12/aff-cup-2020-trong-tai-tran-viet-nam-thai-lan-al-adba-saoud-ali-roi-singapore.jpg?rt=20211225120159", "Cầu thử số 12", "Acting", 200);
        usersList.add(users);
        users = new Users("2", "Thuỳ Duyên", 20, "https://i.pinimg.com/564x/7d/d5/e3/7dd5e3cfa515d800e6a0c2a40638c2a3.jpg", "cool Minded Girl", "Dancing", 800);
        usersList.add(users);
        users = new Users("3", "Ngọc Vũ", 22, "https://i.pinimg.com/564x/ab/18/93/ab18939da2b85ea5959349dc4ad996c2.jpg", "Simple and beautiful Girl", "Singing", 400);
        usersList.add(users);
        users = new Users("7", "Diệu Linh", 19, "https://i.pinimg.com/564x/4a/5e/7d/4a5e7deed283397043b3c394fb00f565.jpg", "OhNo", "Art", 5000);
        usersList.add(users);

        adapter.notifyDataSetChanged();
    }

    private void prepareMatchData() {
        Users users = new Users("1", "Hoàng Khang", 21, "https://scontent.fsgn13-2.fna.fbcdn.net/v/t39.30808-6/269833073_1576261169382012_4208457639995580791_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=vBPVGPyCdKwAX8O1ja7&_nc_ht=scontent.fsgn13-2.fna&oh=00_AT-CTLUhxjwqY2a9vd1aKq2-hcbSWsXdIeLvSsyfPnPwDw&oe=61D1EC98", "HChua Mhe, dark bruh", "Acting", 200);
        matchList.add(users);
        users = new Users("2", "Bob Vu", 20, "https://scontent.fsgn13-2.fna.fbcdn.net/v/t1.6435-9/173412871_277560620579419_3767717723319533504_n.jpg?_nc_cat=108&ccb=1-5&_nc_sid=174925&_nc_ohc=ri3g09EOs5QAX9FhWOr&_nc_ht=scontent.fsgn13-2.fna&oh=00_AT8TRDfesm9XbXkSNv4yPBfNmi5DWxuPaqArXK5mb185Kw&oe=61F16E9F", "BobVu", "Dancing", 800);
        matchList.add(users);
        users = new Users("3", "Tien Dat", 20, "https://scontent.fsgn8-2.fna.fbcdn.net/v/t39.30808-6/208293637_496209454821482_7496314946123640223_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=AEoeYpYEWwoAX9iG52u&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT-8YCliuRdGfbKhnRbAnYRHW7yKgQR_nO3XU8AdGCZitQ&oe=61D0EF27", "Simp", "Singing", 400);
        matchList.add(users);
        users = new Users("4", "Gia Nguyễn", 10, "https://scontent.fsgn3-1.fna.fbcdn.net/v/t1.6435-9/159412590_1776326282549220_6488695765601549205_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=Yyh-x69fUO8AX-srRWg&_nc_ht=scontent.fsgn3-1.fna&oh=00_AT-6V6VqMvU6016xT5jhVLhlWNXzUnvhD_viJHTOm5F7Uw&oe=61F1F1BC", "Prolayer", "swiming", 1308);
        matchList.add(users);
        users = new Users("5", "Minh Anh", 20, "https://i.pinimg.com/564x/5d/a2/70/5da27003cba271fe295a787ea3fba410.jpg", "love you ", "Drawing", 1200);
        matchList.add(users);
        users = new Users("6", "Nguyen Hà", 21, "https://i.pinimg.com/564x/87/d3/98/87d398f723397c8add8a32537719ba16.jpg", "Simple and beautiful Girl", "Sleeping", 700);
        matchList.add(users);
        users = new Users("7", "Ngọc Hạnh", 19, "https://i.pinimg.com/564x/9c/28/53/9c2853e7661095d57f94418d77b43c36.jpg", "Papa's Pari", "Art", 5000);
        matchList.add(users);

        mAdapter.notifyDataSetChanged();
    }

    private void searchFunc() {

    }

    private void setupTopNavigationView() {
        Log.d(TAG, "setupTopNavigationView: setting up TopNavigationView");
        BottomNavigationViewEx tvEx = findViewById(R.id.topNavViewBar);
        TopNavigationViewHelper.setupTopNavigationView(tvEx);
        TopNavigationViewHelper.enableNavigation(mContext, tvEx);
        Menu menu = tvEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


}
