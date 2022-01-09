package com.clmca.labs.datingapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.clmca.labs.datingapp.Main.MainActivity;
import com.clmca.labs.datingapp.R;
import com.clmca.labs.datingapp.Utils.User;

public class SettingHobby extends AppCompatActivity {

    //User Info
    User userInfo;
    String password;
    private Context mContext;
    private Button hobbiesContinueButton;
    private CheckBox cbSport, cbTravel, cbMusic, cbFishing, cbMotorcycles, cbLibra, cbCoffee, cbkaraoke,
            cbStreetFood, cbSushi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_hobby);

        initHobby();
        init();
    }
    //init hobby
    private void initHobby(){

        //select hobby
        hobbiesContinueButton = findViewById(R.id.hobbiesContinueButton);
        cbSport = (CheckBox) findViewById(R.id.cbSport);
        cbTravel = (CheckBox) findViewById(R.id.cbTravel);
        cbMusic = (CheckBox) findViewById(R.id.cbMusic);
        cbFishing = (CheckBox) findViewById(R.id.cbFishing);
        cbMotorcycles = (CheckBox) findViewById(R.id.cbMotorcycles);
        cbLibra = (CheckBox) findViewById(R.id.cbLibra);
        cbCoffee = (CheckBox) findViewById(R.id.cbCoffee);
        cbkaraoke = (CheckBox) findViewById(R.id.cbkaraoke);
        cbStreetFood = (CheckBox) findViewById(R.id.cbStreetFood);
        cbSushi = (CheckBox) findViewById(R.id.cbSushi);
        //
        cbSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Sport", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Sport", Toast.LENGTH_LONG).show();
                }

            }
        });

        cbTravel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Travel", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Travel", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Music", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Music", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbFishing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Fishing", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Fishing", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbMotorcycles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Motorcycles", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Motorcycles", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbLibra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Libra", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Libra", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbCoffee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Coffee", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Coffee", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbkaraoke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Karaoke", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Karaoke", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbStreetFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Street Food", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Street Food", Toast.LENGTH_LONG).show();
                }

            }
        });
        //
        cbSushi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String hobby1="";
                //khi check thay đổi
                if(isChecked){
                    Toast.makeText(SettingHobby.this, "Bạn đã chọn Sushi", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SettingHobby.this, "Bạn đã bỏ chọn Sushi", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    //chọn xong sở thích chuyển về activity editprofile

    public void init() {
        hobbiesContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingHobby.this, EditProfileActivity.class);
                if(cbSport.isChecked()){
                    intent.putExtra("hobby1", "Sport");
                }
                if(cbTravel.isChecked()){
                    intent.putExtra("hobby2", "Travel");
                }
                if(cbMusic.isChecked()){
                    intent.putExtra("hobby3", "Music");
                }
                if(cbMusic.isChecked()){
                    intent.putExtra("hobby4", "Fishing");
                }
                if(cbMotorcycles.isChecked()){
                    intent.putExtra("hobby5", "Motorcycles");
                }
                if(cbLibra.isChecked()){
                    intent.putExtra("hobby6", "Libra");
                }
                if(cbCoffee.isChecked()){
                    intent.putExtra("hobby7", "Coffee");
                }
                if(cbkaraoke.isChecked()){
                    intent.putExtra("hobby8", "Karaoke");
                }
                if(cbStreetFood.isChecked()){
                    intent.putExtra("hobby9", "Street Food");
                }
                if(cbSushi.isChecked()){
                    intent.putExtra("hobby10", "Sushi");
                }
                startActivity(intent);
                finish();

            }
        });
    }
}