package com.dsterwz.dbtest_legend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.dsterwz.dbtest_legend.models.Dish;
import com.dsterwz.dbtest_legend.models.FoodApi;
import com.dsterwz.dbtest_legend.views.DishAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DishViewModel dishViewModel;
    private FlexboxLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //getDishes();
    }

    private void init() {

        //dishViewModel.getDishes();

        //dishRepository = new DishRepository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setAlignItems(AlignItems.CENTER);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DishAdapter adapter = new DishAdapter();
        recyclerView.setAdapter(adapter);

        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllDrinks().observe(this, new Observer<List<Dish>>() {
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

    public void onClickFoods(View view) {
    }

    public void onClickDrinks(View view) {
    }

    public void onClickSnacks(View view) {
    }

    public void OnClickSauce(View view) {
    }

    public void onClickSearch(View view) {
    }

    public void onClickSearchBarOpen(View view) {
    }

    public void OnClickSearchBarClose(View view) {
    }
}