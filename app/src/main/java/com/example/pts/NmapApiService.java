package com.example.pts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NmapApiService {
    @GET("scan")
    Call<String> scan(@Query("target") String target);
}
