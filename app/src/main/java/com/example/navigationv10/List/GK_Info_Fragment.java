package com.example.navigationv10.List;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.MyGKAdapter;
import com.example.navigationv10.MyGKinfoAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.RecyclerInterface;
import com.example.navigationv10.databinding.FragmentGKBinding;
import com.example.navigationv10.databinding.FragmentGKInfoBinding;

import java.util.ArrayList;


public class GK_Info_Fragment extends Fragment implements RecyclerInterface {

    ArrayList<String> gk_name, gk_adress, gk_website;
    DBHelper DB;
    MyGKinfoAdapter adapter;
    public static String name_gk_replace = "";


    private FragmentGKInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("test", "Goooopa");

        binding = FragmentGKInfoBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        DB = new DBHelper(getContext());
        gk_name = new ArrayList<>();
        gk_adress = new ArrayList<>();
        gk_website = new ArrayList<>();
        displaydata();
        adapter = new MyGKinfoAdapter(getContext(), gk_name, gk_adress, gk_website, this);
        binding.gkinforecyclerview.setAdapter(adapter);
        binding.gkinforecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));





        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displaydata()
    {
        Log.d("test", "fuckeeeeeeeeer " +name_gk_replace);
        Cursor cursor = DB.getdatagkinfo(name_gk_replace); //getdatagk для второй таблицы
        if(cursor.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                Log.d("test", "gk_name " +gk_name);
                Log.d("test", "gk_adress " +gk_adress);
                Log.d("test", "gk_website " +gk_website);
                gk_name.add(cursor.getString(0));
                gk_adress.add(cursor.getString(1));
                gk_website.add(cursor.getString(2));

            }
        }
    }

    public void onItemClick(int position) {
        Log.d("debug", "Befor");
        HouseFragment house_fragment = new HouseFragment();
        HouseFragment.house_gk_replace = gk_name.get(position);
        View view = this.getView();
        Navigation.findNavController(view).navigate(R.id.list_house);
        Log.d("debug", "Befor2");
    }


}

