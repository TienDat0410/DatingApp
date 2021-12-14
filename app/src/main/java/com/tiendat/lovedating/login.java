package com.tiendat.lovedating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class login extends AppCompatActivity {
    Button btn_Login;
    TabLayout tabLayout;
    ViewPager viewPager;
    EditText edtEmail, edtPassword;
    float v =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText)findViewById(R.id.Edt_email);
        edtPassword = (EditText)findViewById(R.id.Edt_password);

        btn_Login = (Button)findViewById(R.id.btnNext);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tag_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        //
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        //
        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        //
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //set độ mờ
        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
    }
}