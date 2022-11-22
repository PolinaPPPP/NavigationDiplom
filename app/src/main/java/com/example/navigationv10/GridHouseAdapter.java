package com.example.navigationv10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GridHouseAdapter extends RecyclerView.Adapter<GridHouseAdapter.GridViewHolder> {

    private List<String> items = new ArrayList<>();

    private OnItemClick onItemClick = null;

    public GridHouseAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_house, parent,false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        String item = items.get(position);

        holder.houseName.setText(item);
        holder.houseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {

        TextView houseName;
        
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            houseName = itemView.findViewById(R.id.id_house_name);
        }
    }

    public interface OnItemClick {
        void onClick(String houseName);
    }
}
