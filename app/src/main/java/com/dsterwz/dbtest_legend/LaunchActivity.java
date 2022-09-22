package com.dsterwz.dbtest_legend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dsterwz.dbtest_legend.models.Dish;
import com.dsterwz.dbtest_legend.models.FoodApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaunchActivity extends AppCompatActivity {

    private FoodApi foodApi;
    private DishRepository dishRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        dishRepository = new DishRepository(getApplication());
        getDishes();
    }

    public void getDishes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://food.madskill.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        foodApi = retrofit.create(FoodApi.class);

        Call<List<Dish>> call = foodApi.getDishes("1.01");
        call.enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                if (response.isSuccessful()) {
                    List<Dish> dishes = response.body();


                    for (Dish dish : dishes) {
                        dishRepository.insert(dish);
                    /*
                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    content += "Dish ID: " + dish.getDishId() + "\n";
                    content += "Category: " + dish.getCategory() + "\n";
                    content += "Name Dish: " + dish.getNameDish() + "\n";
                    content += "Price: " + dish.getPrice() + "\n";
                    content += "Icon: " + dish.getIcon() + "\n";
                    content += "Version: " + dish.getVersion() + "\n\n";

                    anal.append(content);*/
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {

            }
        });
    }
}