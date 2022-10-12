package com.example.navigationv10;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final RecyclerInterface recyclerInterface;
    private Context context;
    private ArrayList name_id, email_id, age_id;

    public MyAdapter(Context context, ArrayList name_id, ArrayList email_id, ArrayList age_id,RecyclerInterface recyclerInterface) {
        this.context = context;
        this.name_id = name_id;
        this.email_id = email_id;
        this.age_id = age_id;
        this.recyclerInterface = recyclerInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v, recyclerInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.age_id.setText(String.valueOf(age_id.get(position)));


    }
    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id, email_id, age_id;
        public MyViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            email_id = itemView.findViewById(R.id.textemail);
            age_id = itemView.findViewById(R.id.textage);
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
