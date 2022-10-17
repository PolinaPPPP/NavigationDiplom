package com.example.navigationv10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MyGKAdapter extends RecyclerView.Adapter<MyGKAdapter.MyViewHolder> {
    private final RecyclerInterface recyclerInterface;
    private Context context;
    private ArrayList gk_name_id,gk_adress_id;


    public MyGKAdapter(Context context, ArrayList gk_name_id, ArrayList gk_adress_id, RecyclerInterface recyclerInterface) {
        this.context = context;
        this.gk_name_id = gk_name_id;
        this.gk_adress_id = gk_adress_id;

        this.recyclerInterface = recyclerInterface;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.spisok_gk,parent,false);
        return new MyViewHolder(v, recyclerInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.gk_name_id.setText(String.valueOf(gk_name_id.get(position)));
        holder.gk_adress_id.setText(String.valueOf(gk_adress_id.get(position)));



    }
    @Override
    public int getItemCount() {
        return gk_name_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gk_name_id, gk_adress_id;
        public MyViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            gk_name_id = itemView.findViewById(R.id.text_gk_name);
            gk_adress_id = itemView.findViewById(R.id.text_gk_adress);


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
