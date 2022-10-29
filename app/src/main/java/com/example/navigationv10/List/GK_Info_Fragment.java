package com.example.navigationv10.List;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.GridHouseAdapter;
import com.example.navigationv10.MyGKAdapter;
import com.example.navigationv10.databinding.FragmentGKInfoBinding;
import com.example.navigationv10.databinding.GridviewHouseBinding;
import com.example.navigationv10.databinding.ItemGkinfofullBinding;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class GK_Info_Fragment extends Fragment  {
    DBHelper DB;
    GridHouseAdapter adapter;

    public static String name_gk_replace = "Не заполнен";
    private FragmentGKInfoBinding binding;
    ViewGroup container_r;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("test", "Goooopa "+name_gk_replace);

        binding = FragmentGKInfoBinding.inflate(inflater, container, false);
        container_r = container;

        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdatagkinfo(name_gk_replace);
        Log.d("displaydata", "cursor.getCount()="+cursor.getCount());
        if(cursor.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            List<String> namefield = new ArrayList<>();
            List<String> datafield = new ArrayList<>();
            //gk_name,gk_adress,gk_website
            while(cursor.moveToNext())
            {
                String temp;
                temp = cursor.getString(0);
                if(temp != null && temp.length() != 0) {
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));
                    namefield.add("Название ЖК   ");
                    datafield.add(cursor.getString(0));



                }
                temp = cursor.getString(1);
                if(temp != null && temp.length() != 0){
                    namefield.add("Адрес   ");
                    datafield.add(cursor.getString(1));
                }
                temp = cursor.getString(2);
                if(temp != null && temp.length() != 0) {
                    namefield.add("Вебсайт   ");
                    datafield.add(cursor.getString(2));
                }
                binding.gkinforecyclerview.setAdapter(new MyAdapter(namefield, datafield));
                binding.gkinforecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }

        Cursor cursor_house = DB.getdatahouse(name_gk_replace);
        Log.d("getdatahouse", "cursor.getCount()="+cursor_house.getCount());
        if(cursor_house.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            List<String> housenamefield = new ArrayList<>();
            //gk_name,gk_adress,gk_website
            while(cursor_house.moveToNext())
            {
                String temp;
                temp = cursor_house.getString(0);
                if(temp != null && temp.length() != 0) {
                    housenamefield.add(cursor_house.getString(0));
                    housenamefield.add(cursor_house.getString(0));
                    housenamefield.add(cursor_house.getString(0));
                    housenamefield.add(cursor_house.getString(0));
                    housenamefield.add(cursor_house.getString(0));
                    housenamefield.add(cursor_house.getString(0));
                    housenamefield.add(cursor_house.getString(0));
                }
                adapter = new GridHouseAdapter(getContext(), housenamefield);
                binding.houseGrid.setAdapter(adapter);
                binding.houseGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), "Выбран дом" + housenamefield.get(position), Toast.LENGTH_SHORT).show();
                        return;
                    }
                });            }
        }








        View root = binding.getRoot();

        return root;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private List<String> items1;
        private List<String> items2;

        private class MyViewHolder extends RecyclerView.ViewHolder{

            ItemGkinfofullBinding binding;//Name of the test_list_item.xml in camel case + "Binding"

            public MyViewHolder(ItemGkinfofullBinding b){
                super(b.getRoot());
                binding = b;
            }
        }

        public MyAdapter(List<String> items1, List<String> items2){
            this.items1 = items1;
            this.items2 = items2;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

            return new MyViewHolder(ItemGkinfofullBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position){
            String text1 = String.format(Locale.ENGLISH, "%s ", items1.get(position), position);
            String text2 = String.format(Locale.ENGLISH, "%s ", items2.get(position), position);
            //An example of how to use the bindings
            holder.binding.textView1.setText(text1);
            holder.binding.textView2.setText(text2);
        }

        @Override
        public int getItemCount(){
            return items1.size();
        }
    }








}

