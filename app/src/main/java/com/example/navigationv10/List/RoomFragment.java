package com.example.navigationv10.List;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.GridRoomAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentRoomBinding;
import com.example.navigationv10.databinding.FragmentSectionBinding;
import com.example.navigationv10.databinding.SpisokRoomBinding;
import com.example.navigationv10.databinding.SpisokSectionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RoomFragment extends Fragment {

    DBHelper DB;


    private FragmentRoomBinding binding;
    ViewGroup container_r;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        String roomName = getArguments().getString("roomName", "roomName");
        Toast.makeText(getContext(), "Квартира     " + roomName, Toast.LENGTH_SHORT).show();


        binding = FragmentRoomBinding.inflate(inflater, container, false);

        container_r = container;

        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdataroominfo(roomName);
        Log.d("displaydata", "cursor.getCount()=" + cursor.getCount());
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            List<String> namefield = new ArrayList<>();
            List<String> datafield = new ArrayList<>();

            Log.d("tesrtyyoikdfokpgfd", "data colums:" + cursor.getColumnCount());
            String temp;
            cursor.moveToNext();
            temp = cursor.getString(0);
            if (temp != null && temp.length() != 0) {
                namefield.add("Номер квартиры:   ");
                datafield.add(cursor.getString(0));
                Log.d("TEST get house details", "data:" + cursor.getString(0));
            }
            temp = cursor.getString(1);
            if (temp != null && temp.length() != 0) {
                namefield.add("Секция:   ");
                datafield.add(cursor.getString(1));
                Log.d("TEST get house details", "data:" + cursor.getString(1));
            }
            temp = cursor.getString(2);
            if (temp != null && temp.length() != 0) {
                namefield.add("Количество комнат:   ");
                datafield.add(cursor.getString(2));
                Log.d("TEST get house details", "data:" + cursor.getString(2));
            }
            temp = cursor.getString(3);
            if (temp != null && temp.length() != 0) {
                namefield.add("Стоимость:   ");
                datafield.add(cursor.getString(3));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }
            temp = cursor.getString(4);
            if (temp != null && temp.length() != 0) {
                namefield.add("Площадь:   ");
                datafield.add(cursor.getString(4));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }
            temp = cursor.getString(5);
            if (temp != null && temp.length() != 0) {
                namefield.add("Стороны света:   ");
                datafield.add(cursor.getString(5));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }
            temp = cursor.getString(6);
            if (temp != null && temp.length() != 0) {
                namefield.add("Отделка:   ");
                datafield.add(cursor.getString(6));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }

            binding.roominforecyclerview.setAdapter(new RoomFragment.MyRoomAdapter(namefield, datafield));
            binding.roominforecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        }
        View root = binding.getRoot();
        return root;
    }
    private class MyRoomAdapter extends RecyclerView.Adapter<RoomFragment.MyRoomAdapter.MyViewHolder>{

        private List<String> items1;
        private List<String> items2;

        private class MyViewHolder extends RecyclerView.ViewHolder{

            SpisokRoomBinding binding;//Name of the test_list_item.xml in camel case + "Binding"

            public MyViewHolder(SpisokRoomBinding b){
                super(b.getRoot());
                binding = b;
            }
        }

        public MyRoomAdapter(List<String> items1, List<String> items2){
            this.items1 = items1;
            this.items2 = items2;
        }

        @NonNull
        @Override
        public RoomFragment.MyRoomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

            return new RoomFragment.MyRoomAdapter.MyViewHolder(SpisokRoomBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(RoomFragment.MyRoomAdapter.MyViewHolder holder, int position){
            String text1 = String.format(Locale.ENGLISH, "%s ", items1.get(position), position);
            String text2 = String.format(Locale.ENGLISH, "%s ", items2.get(position), position);
            //An example of how to use the bindings
            holder.binding.textView5.setText(text1);
            holder.binding.textView6.setText(text2);
        }

        @Override
        public int getItemCount(){
            return items1.size();
        }
    }

}