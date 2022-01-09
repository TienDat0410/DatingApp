package com.clmca.labs.datingapp.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.clmca.labs.datingapp.Matched.MatchUserAdapter;
import com.clmca.labs.datingapp.Matched.Users;
import com.clmca.labs.datingapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView, mChatRecyclerView;
    private MatchUserAdapter mChatAdapter;
    List<Users> matchList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mChatRecyclerView = findViewById(R.id.matche_recycler_view);
        //
        mChatAdapter = new MatchUserAdapter(matchList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mChatRecyclerView.setLayoutManager(mLayoutManager1);
        mChatRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mChatRecyclerView.setAdapter(mChatAdapter);
    }
}