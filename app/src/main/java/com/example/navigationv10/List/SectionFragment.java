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
import android.widget.Toast;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.GridRoomAdapter;
import com.example.navigationv10.GridSectionAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentHouseBinding;
import com.example.navigationv10.databinding.FragmentSectionBinding;
import com.example.navigationv10.databinding.SpisokHouseBinding;
import com.example.navigationv10.databinding.SpisokSectionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SectionFragment extends Fragment {

    DBHelper DB;
    GridRoomAdapter adapter;

    private FragmentSectionBinding binding;
    ViewGroup container_r;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        String section_namber = getArguments().getString("section_namber", "section_namber");
        Toast.makeText(getContext(), "Секция     " + section_namber, Toast.LENGTH_SHORT).show();


        binding = FragmentSectionBinding.inflate(inflater, container, false);

        container_r = container;

        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdatasectioninfo(section_namber);
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
                namefield.add("Секция:   ");
                datafield.add(cursor.getString(0));
                Log.d("TEST get house details", "data:" + cursor.getString(0));
            }
            temp = cursor.getString(1);
            if (temp != null && temp.length() != 0) {
                namefield.add("Дом:   ");
                datafield.add(cursor.getString(1));
                Log.d("TEST get house details", "data:" + cursor.getString(1));
            }
            temp = cursor.getString(2);
            if (temp != null && temp.length() != 0) {
                namefield.add("Количество этажей:   ");
                datafield.add(cursor.getString(2));
                Log.d("TEST get house details", "data:" + cursor.getString(2));
            }
            temp = cursor.getString(3);
            if (temp != null && temp.length() != 0) {
                namefield.add("Количество квартир на этаже:   ");
                datafield.add(cursor.getString(3));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }
            temp = cursor.getString(4);
            if (temp != null && temp.length() != 0) {
                namefield.add("Номер квартир с:   ");
                datafield.add(cursor.getString(4));
                Log.d("TEST get house details", "data:" + cursor.getString(3));
            }

            binding.sectioninforecyclerview.setAdapter(new SectionFragment.MySectionAdapter(namefield, datafield));
            binding.sectioninforecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        }


        Cursor cursor_room = DB.getdataroom(section_namber);
        Log.d("getdataroom", "cursor.getCount()="+cursor_room.getCount());
        if(cursor_room.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else {
            List<String> room_namefield = new ArrayList<>();
            //gk_name,gk_adress,gk_website
            while (cursor_room.moveToNext()) {
                String temp;
                temp = cursor_room.getString(0);
                if (temp != null && temp.length() != 0) {
                    room_namefield.add(cursor_room.getString(0));

                }
                adapter = new GridRoomAdapter(new GridRoomAdapter.OnItemClick() {
                    @Override
                    public void onClick(String roomName) {
                        Bundle bundle = new Bundle();
                        bundle.putString("roomName", roomName);
                        Navigation.findNavController(getView()).navigate(R.id.list_room, bundle);

                        //navController.navigate(R.id.list_house, bundle);
                        Toast.makeText(getContext(), roomName, Toast.LENGTH_SHORT).show();
                    }
                });
                binding.roomrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
                binding.roomrecyclerview.setAdapter(adapter);
                adapter.updateItems(room_namefield);

            }
        }

        View root = binding.getRoot();
        return root;
    }


    private class MySectionAdapter extends RecyclerView.Adapter<SectionFragment.MySectionAdapter.MyViewHolder>{

        private List<String> items1;
        private List<String> items2;

        private class MyViewHolder extends RecyclerView.ViewHolder{

            SpisokSectionBinding binding;//Name of the test_list_item.xml in camel case + "Binding"

            public MyViewHolder(SpisokSectionBinding b){
                super(b.getRoot());
                binding = b;
            }
        }

        public MySectionAdapter(List<String> items1, List<String> items2){
            this.items1 = items1;
            this.items2 = items2;
        }

        @NonNull
        @Override
        public SectionFragment.MySectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

            return new SectionFragment.MySectionAdapter.MyViewHolder(SpisokSectionBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(SectionFragment.MySectionAdapter.MyViewHolder holder, int position){
            String text1 = String.format(Locale.ENGLISH, "%s ", items1.get(position), position);
            String text2 = String.format(Locale.ENGLISH, "%s ", items2.get(position), position);
            //An example of how to use the bindings
            holder.binding.textView2.setText(text1);
            holder.binding.textView3.setText(text2);
        }

        @Override
        public int getItemCount(){
            return items1.size();
        }
    }

}