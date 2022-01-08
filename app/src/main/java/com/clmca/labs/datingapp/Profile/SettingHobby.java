package com.clmca.labs.datingapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clmca.labs.datingapp.Main.MainActivity;
import com.clmca.labs.datingapp.R;
import com.clmca.labs.datingapp.Utils.User;

public class SettingHobby extends AppCompatActivity {

    //User Info
    User userInfo;
    String password;
    private Context mContext;
    private Button hobbiesContinueButton;
    private Button sportsSelectionButton;
    private Button travelSelectionButton;
    private Button musicSelectionButton;
    private Button fishingSelectionButton;
    private Button motorcyclesSelectionButton;
    private Button libraSelectionButton;
    private Button coffeeSelectionButton;
    private Button karaokeSelectionButton;
    private Button streetfoodSelectionButton;
    private Button sushiSelectionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_hobby);

        initHobby();
        init();
    }
    //init hobby
    private void initHobby(){
        sportsSelectionButton = findViewById(R.id.sportsSelectionButton);
        travelSelectionButton = findViewById(R.id.travelSelectionButton);
        musicSelectionButton = findViewById(R.id.musicSelectionButton);
        fishingSelectionButton = findViewById(R.id.fishingSelectionButton);
        //
        motorcyclesSelectionButton = findViewById(R.id.motorcyclesSelectionButton);
        libraSelectionButton = findViewById(R.id.libraSelectionButton);
        coffeeSelectionButton = findViewById(R.id.coffeeSelectionButton);
        karaokeSelectionButton = findViewById(R.id.karaokeSelectionButton);
        streetfoodSelectionButton = findViewById(R.id.streetfoodSelectionButton);
        sushiSelectionButton = findViewById(R.id.sushiSelectionButton);
        //select hobby
        hobbiesContinueButton = findViewById(R.id.hobbiesContinueButton);
        //
        sportsSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsButtonClicked();
            }
        });

        travelSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelButtonClicked();
            }
        });

        musicSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicButtonClicked();
            }
        });

        fishingSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fishingButtonClicked();
            }
        });

        motorcyclesSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motorcyclesButtonClicked();
            }
        });

        libraSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraButtonClicked();
            }
        });

        coffeeSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coffeeButtonClicked();
            }
        });

        karaokeSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                karaokeButtonClicked();
            }
        });

        streetfoodSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetfoodButtonClicked();
            }
        });

        sushiSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sushiButtonClicked();
            }
        });

    }



    //chọn nút chuyển màu
    public void sportsButtonClicked() {
        //chọn và không chọn nút
        if (sportsSelectionButton.getAlpha() == 1.0f) {
            sportsSelectionButton.setAlpha(.5f);
            sportsSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setSports(false);
        } else {
            sportsSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            sportsSelectionButton.setAlpha(1.0f);
            userInfo.setSports(true);
        }
    }

    public void travelButtonClicked() {
        if (travelSelectionButton.getAlpha() == 1.0f) {
            travelSelectionButton.setAlpha(.5f);
            travelSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setTravel(false);
        } else {
            travelSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            travelSelectionButton.setAlpha(1.0f);
            userInfo.setTravel(true);

        }

    }

    public void musicButtonClicked() {
        if (musicSelectionButton.getAlpha() == 1.0f) {
            musicSelectionButton.setAlpha(.5f);
            musicSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setMusic(false);
        } else {
            musicSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            musicSelectionButton.setAlpha(1.0f);
            userInfo.setMusic(true);

        }

    }

    public void fishingButtonClicked() {
        if (fishingSelectionButton.getAlpha() == 1.0f) {
            fishingSelectionButton.setAlpha(.5f);
            fishingSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            fishingSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            fishingSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }

    }

    public void motorcyclesButtonClicked() {
        if (motorcyclesSelectionButton.getAlpha() == 1.0f) {
            motorcyclesSelectionButton.setAlpha(.5f);
            motorcyclesSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            motorcyclesSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            motorcyclesSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }

    }
    private void libraButtonClicked() {
        if (libraSelectionButton.getAlpha() == 1.0f) {
            libraSelectionButton.setAlpha(.5f);
            libraSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            libraSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            libraSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }
    }

    private void coffeeButtonClicked() {
        if (coffeeSelectionButton.getAlpha() == 1.0f) {
            coffeeSelectionButton.setAlpha(.5f);
            coffeeSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            coffeeSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            coffeeSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }
    }


    private void karaokeButtonClicked() {
        if (karaokeSelectionButton.getAlpha() == 1.0f) {
            karaokeSelectionButton.setAlpha(.5f);
            karaokeSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            karaokeSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            karaokeSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }
    }

    private void streetfoodButtonClicked() {
        if (streetfoodSelectionButton.getAlpha() == 1.0f) {
            streetfoodSelectionButton.setAlpha(.5f);
            streetfoodSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            streetfoodSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            streetfoodSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }
    }

    private void sushiButtonClicked() {
        if (sushiSelectionButton.getAlpha() == 1.0f) {
            sushiSelectionButton.setAlpha(.5f);
            sushiSelectionButton.setBackgroundColor(Color.GRAY);
            userInfo.setFishing(false);
        } else {
            sushiSelectionButton.setBackgroundColor(Color.parseColor("#FF4081"));
            sushiSelectionButton.setAlpha(1.0f);
            userInfo.setFishing(true);

        }
    }
    //chọn xong sở thích chuyển về activity editprofile

    public void init() {
        hobbiesContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                finish();

            }
        });
    }
}