package com.dsterwz.dbtest_legend.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsterwz.dbtest_legend.R;
import com.dsterwz.dbtest_legend.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishHolder> {
    private List<Dish> dishes = new ArrayList<>();
    private List<Dish> dishesFull = new ArrayList<>();

    class DishHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewPrice;
        private ImageView imageViewIcon;

        public DishHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            imageViewIcon = itemView.findViewById(R.id.dish_icon);
        }
    }

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
        String mDishName = currentDish.getNameDish();
        holder.textViewTitle.setText(mDishName);
        holder.textViewPrice.setText(String.valueOf(currentDish.getPrice()));
        holder.imageViewIcon.setImageResource(R.drawable.fatass);
        if (mDishName.length() >= 18 && mDishName.length() < 29)
            holder.textViewTitle.setTextSize(20);
        else if (mDishName.length() >= 29) holder.textViewTitle.setTextSize(18);
        else holder.textViewTitle.setTextSize(22);

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        dishesFull = new ArrayList<>(dishes);
        notifyDataSetChanged();
    }

    public Filter getDishFilter() {
        return dishFilter;
    }

    private Filter dishFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Dish> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dishesFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Dish item : dishesFull) {
                    if (item.getNameDish().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;


            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dishes.clear();
            dishes.addAll((List) filterResults.values);
            dishes.add(new Dish(200,
                    "Foods",
                    "Залупа цветочная с горошком",
                    999_999,
                    "zalupa.jpg",
                    "1.3"));
            notifyDataSetChanged();
        }
    };
}
