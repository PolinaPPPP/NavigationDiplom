package com.example.navigationv10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyHouseAdapter extends RecyclerView.Adapter<MyHouseAdapter.MyViewHolder>{

    private final RecyclerInterface recyclerInterface;
    private Context context;
    private ArrayList house_name_id, house_gk_id;

    public MyHouseAdapter(Context context, ArrayList house_name_id, ArrayList house_gk_id, RecyclerInterface recyclerInterface) {
        this.context = context;
        this.house_name_id = house_name_id;
        this.house_gk_id = house_gk_id;

        this.recyclerInterface = recyclerInterface;

    }

    @NonNull
    @Override
    public MyHouseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.spisok_house,parent,false);
        return new MyHouseAdapter.MyViewHolder(v, recyclerInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHouseAdapter.MyViewHolder holder, int position) {
        holder.house_name_id.setText(String.valueOf(house_name_id.get(position)));
        holder.house_gk_id.setText(String.valueOf(house_gk_id.get(position)));

    }
    @Override
    public int getItemCount() {
        return house_name_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView house_name_id, house_gk_id;
        public MyViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            house_name_id = itemView.findViewById(R.id.text_house_name);
            house_gk_id = itemView.findViewById(R.id.text_house_gk);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerInterface!=null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(pos);
                        }

                    }
                }
            });
        }
    }
}