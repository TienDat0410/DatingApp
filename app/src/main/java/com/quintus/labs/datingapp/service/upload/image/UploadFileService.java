package com.quintus.labs.datingapp.service.upload.image;

import com.quintus.labs.datingapp.service.api.ApiService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFileService {
    private void uploadFile(File file) {

        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"),
                file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), fbody);


        ApiService.apiService.uploadFile(image).enqueue(new Callback<UploadFileResponse>() {
            @Override
            public void onResponse(Call<UploadFileResponse> call, Response<UploadFileResponse> response) {
                UploadFileResponse body = response.body();
                String link = body.getData().getLink();
            }

            @Override
            public void onFailure(Call<UploadFileResponse> call, Throwable t) {

            }
        });
    }

}
