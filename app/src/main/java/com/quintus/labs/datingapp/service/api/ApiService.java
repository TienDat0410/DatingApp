package com.quintus.labs.datingapp.service.api;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quintus.labs.datingapp.Login.AuthenticationResponse;
import com.quintus.labs.datingapp.Login.GoogleAuthenticationRequest;
import com.quintus.labs.datingapp.Profile.ProfileResponse;
import com.quintus.labs.datingapp.Profile.SaveProfileRequest;
import com.quintus.labs.datingapp.service.sharedprefs.SharedPrefs;
import com.quintus.labs.datingapp.service.upload.image.UploadFileResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            SharedPreferences prefs = SharedPrefs.getInstance();

            String token = prefs.getString("jwt", "undefined");
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    ApiService apiService = new Retrofit.Builder()
            .client(client)
            .baseUrl("http://192.168.1.4:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("authentication/google/login")
    Call<AuthenticationResponse> getAuthenticationToken(@Body GoogleAuthenticationRequest body);

    @GET("user/profile")
    Call<ProfileResponse> getProfile();

    @POST("user/profile")
    Call<Void> saveProfile(@Body SaveProfileRequest request);

    //@Headers({"Authorization: Client-ID 79adc87693a65cc"})
    @Multipart
    @POST("https://api.imgur.com/3/upload")
    Call<UploadFileResponse> uploadFile(
            @Part MultipartBody.Part file);
}
