package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    public int currentItemCount = 10;
    private Recipe[] recipe = new Recipe[0];
    private OnItemClickListener listener;
    private OnItemLongClickListener onItemLongClickListener;

    public RecipeAdapter(Recipe[] recipes) {
        this.recipe = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recipeName.setText(recipe[position].getName());
        holder.itemView.setOnClickListener(view -> {
            if(listener != null) {
                listener.onItemClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (recipe != null) {
            return Math.min(recipe.length,currentItemCount);
        } else {
            return 0;
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {this.onItemLongClickListener = onItemLongClickListener;}
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (onItemLongClickListener != null && position != RecyclerView.NO_POSITION) {
                    onItemLongClickListener.onItemLongClick(position);
                }
                return true;
            });

        }
    }
}
