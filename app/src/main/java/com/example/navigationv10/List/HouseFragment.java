package com.example.navigationv10.List;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.navigationv10.GridSectionAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentGKInfoBinding;
import com.example.navigationv10.databinding.FragmentHouseBinding;
import com.example.navigationv10.databinding.SpisokGkInfoBinding;
import com.example.navigationv10.databinding.SpisokHouseBinding;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HouseFragment extends Fragment {
    DBHelper DB;
    GridSectionAdapter adapter;

    private FragmentHouseBinding binding;
    ViewGroup container_r;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        String houseName = getArguments().getString("house_name", "house_name");
        Toast.makeText(getContext(),  "AAAAAAAAAAAAAAAAAA     "+houseName, Toast.LENGTH_SHORT).show();


        binding = FragmentHouseBinding.inflate(inflater, container, false);

        container_r = container;


        //house_name,house_gk,data,otdelka
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdatahouseinfo(houseName);
        Log.d("displaydata", "cursor.getCount()=" + cursor.getCount());
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            List<String> namefield = new ArrayList<>();
            List<String> datafield = new ArrayList<>();

            Log.d("tesrtyyoikdfokpgfd", "data colums:"+ cursor.getColumnCount());
            String temp;
            cursor.moveToNext();
            temp = cursor.getString(0);
            if (temp != null && temp.length() != 0) {
                namefield.add("Название дома   ");
                datafield.add(cursor.getString(0));
                Log.d("TEST get house details", "data:" + cursor.getString(0));
            }
            temp = cursor.getString(1);
            if (temp != null && temp.length() != 0) {
                namefield.add("Жилой комплекс   ");
                datafield.add(cursor.getString(1));
                Log.d("TEST get house details", "data:" + cursor.getString(1));
            }
            temp = cursor.getString(2);
            if (temp != null && temp.length() != 0) {
                namefield.add("Срок сдачи   ");
                datafield.add(cursor.getString(2));
                Log.d("TEST get house details", "data:" + cursor.getString(2));
            }
            temp = cursor.getString(3);
            if (temp != null && temp.length() != 0) {
                namefield.add("Отделка   ");
                datafield.add(cursor.getString(3));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }

            binding.houserecyclerview.setAdapter(new HouseFragment.MyHouseAdapter(namefield, datafield));
            binding.houserecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        }

        Cursor cursor_section = DB.getdatasection(houseName);
        Log.d("getdatasection", "cursor.getCount()="+cursor_section.getCount());
        if(cursor_section.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            List<String> section_namefield = new ArrayList<>();
            //gk_name,gk_adress,gk_website
            while(cursor_section.moveToNext())
            {
                String temp;
                temp = cursor_section.getString(0);
                if(temp != null && temp.length() != 0) {
                    section_namefield.add(cursor_section.getString(0));

                }
                adapter = new GridSectionAdapter(new GridSectionAdapter.OnItemClick() {
                    @Override
                    public void onClick(String section_namber) {
                        Bundle bundle = new Bundle();
                        bundle.putString("section_namber", section_namber);
                        Navigation.findNavController(getView()).navigate(R.id.list_section, bundle);

                        //navController.navigate(R.id.list_house, bundle);
                        Toast.makeText(getContext(),  section_namber, Toast.LENGTH_SHORT).show();
                    }
                });
                binding.sectionrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
                binding.sectionrecyclerview.setAdapter(adapter);
                adapter.updateItems(section_namefield);

            }
        }

        View root = binding.getRoot();
        return root;
    }

    private class MyHouseAdapter extends RecyclerView.Adapter<HouseFragment.MyHouseAdapter.MyViewHolder>{

        private List<String> items1;
        private List<String> items2;

        private class MyViewHolder extends RecyclerView.ViewHolder{

            SpisokHouseBinding binding;//Name of the test_list_item.xml in camel case + "Binding"

            public MyViewHolder(SpisokHouseBinding b){
                super(b.getRoot());
                binding = b;
            }
        }

        public MyHouseAdapter(List<String> items1, List<String> items2){
            this.items1 = items1;
            this.items2 = items2;
        }

        @NonNull
        @Override
        public HouseFragment.MyHouseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

            return new HouseFragment.MyHouseAdapter.MyViewHolder(SpisokHouseBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(HouseFragment.MyHouseAdapter.MyViewHolder holder, int position){
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


