package com.example.navigationv10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GridSectionAdapter extends RecyclerView.Adapter<GridSectionAdapter.GridViewHolder> {

private List<String> items = new ArrayList<>();

private OnItemClick onItemClick = null;

public GridSectionAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        }

@NonNull
@Override
public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_section, parent,false);
        return new GridViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        String item = items.get(position);
        holder.section_namber.setText(item);
        holder.section_namber.setOnClickListener(new View.OnClickListener() {
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

    TextView section_namber;

    public GridViewHolder(@NonNull View itemView) {
        super(itemView);
        section_namber = itemView.findViewById(R.id.id_section_namber);
    }
}

public interface OnItemClick {
    void onClick(String houseName);
}
}
