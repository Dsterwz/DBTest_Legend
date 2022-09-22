package com.dsterwz.dbtest_legend.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {
    @GET("dishes/version")
    Call<DishesVersion> getVersion();

    @GET("dishes")
    Call<List<Dish>> getDishes(
            @Query("version") String version
    );
}