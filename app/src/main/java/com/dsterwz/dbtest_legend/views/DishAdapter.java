package com.dsterwz.dbtest_legend.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsterwz.dbtest_legend.R;
import com.dsterwz.dbtest_legend.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishHolder> {
    private List<Dish> dishes = new ArrayList<>();

    @NonNull
    @Override
    public DishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_item, parent, false);
        return new DishHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DishHolder holder, int position) {
        Dish currentDish = dishes.get(position);
        holder.textViewTitle.setText(currentDish.getNameDish());
        holder.textViewPrice.setText(String.valueOf(currentDish.getPrice()));

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        notifyDataSetChanged();
    }

    class DishHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewPrice;

        public DishHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
        }
    }
}
