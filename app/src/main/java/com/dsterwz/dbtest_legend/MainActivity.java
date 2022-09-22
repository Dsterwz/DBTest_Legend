package com.dsterwz.dbtest_legend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dsterwz.dbtest_legend.models.Dish;
import com.dsterwz.dbtest_legend.models.FoodApi;
import com.dsterwz.dbtest_legend.views.DishAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DishRepository dishRepository;
    private DishViewModel dishViewModel;
    private FoodApi foodApi;
    private FlexboxLayoutManager layoutManager;
    private LiveData<List<Dish>> allDishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDishes();
    }

    private void init() {

        //dishViewModel.getDishes();

        dishRepository = new DishRepository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setAlignItems(AlignItems.CENTER);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DishAdapter adapter = new DishAdapter();
        recyclerView.setAdapter(adapter);

        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllDishes().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                adapter.setDishes(dishes);
            }
        });

        /*dishViewModel.insert(new Dish(1,
                "Foods",
                "Zalupa",
                20,
                "jopa.jpg",
                "1.02"));*/

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