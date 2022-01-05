package com.quintus.labs.datingapp.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.quintus.labs.datingapp.Main.MainActivity;
import com.quintus.labs.datingapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    SignInButton signInButton;
    private GoogleApiClient mGoogleSignInClient;

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Yêu cầu cung cáp thông tin email....
        String idTokenRequest = "1096280217531-liqq0djthhopsvgaom7cg6am19gk4gch.apps.googleusercontent.com";
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(idTokenRequest)
                .requestEmail()
                .build();
        // kết nối với google API client
        mGoogleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //ánh xạ
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(view -> {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
            startActivityForResult(intent, RC_SIGN_IN);
        });

        signInButton.setSize(SignInButton.SIZE_STANDARD);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //kết nối thất bại
        Log.d("Failed", connectionResult + "");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            handleSigninResult(result);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    private void handleSigninResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String idToken = account.getIdToken();

            GoogleAuthenticationRequest request = GoogleAuthenticationRequest.builder()
                    .idToken(idToken)
                    .build();

            ApiService.apiService.getAuthenticationToken(request).enqueue(new Callback<AuthenticationResponse>() {
                @Override
                public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                    AuthenticationResponse body = response.body();
                    String fullName = body.getFullName();
                }

                @Override
                public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                    String message = t.getMessage();
                }
            });


        } else {
            Toast.makeText(Login.this, "Login false", Toast.LENGTH_SHORT).show();
        }
    }


}
