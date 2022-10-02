package com.dsterwz.dbtest_legend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dsterwz.dbtest_legend.models.Dish;
import com.dsterwz.dbtest_legend.views.DishAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DishAdapter dishAdapter;
    private DishViewModel dishViewModel;

    private FlexboxLayoutManager layoutManager;
    private EditText editTextSearchBar;
    private Toolbar toolbar;

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

        dishAdapter = new DishAdapter();
        recyclerView.setAdapter(dishAdapter);

        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllFoods().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });

        toolbar = findViewById(R.id.search_bar);

        editTextSearchBar = findViewById(R.id.edit_text_search_bar);
        editTextSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dishAdapter.getDishFilter().filter(editable.toString());
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
        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllFoods().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });
    }

    public void onClickDrinks(View view) {
        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllDrinks().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });
    }

    public void onClickSnacks(View view) {
        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllSnacks().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });
    }

    public void OnClickSauce(View view) {
        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllSauce().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });
    }

    public void onClickSearchBarOpen(View view) {
        editTextSearchBar.setText("");
        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllDishes().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });
        toolbar.setVisibility(View.VISIBLE);
    }

    public void OnClickSearchBarClose(View view) {
        dishViewModel = new ViewModelProvider(this).get(DishViewModel.class);
        dishViewModel.getAllDishes().observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setDishes(dishes);
            }
        });
        toolbar.setVisibility(View.INVISIBLE);
    }
}