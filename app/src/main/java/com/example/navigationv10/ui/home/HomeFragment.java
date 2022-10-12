package com.example.navigationv10.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.List.GKFragment;
import com.example.navigationv10.MyAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.RecyclerInterface;
import com.example.navigationv10.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerInterface{

    //RecyclerView recyclerView;
    ArrayList<String> name, email, age;
    DBHelper DB;
    MyAdapter adapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Log.d("debug", "1");
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        Log.d("debug", "2");
        View root = binding.getRoot();
        Log.d("debug", "3");

        DB = new DBHelper(getContext());
        name = new ArrayList<>();
        email = new ArrayList<>();
        age = new ArrayList<>();
        Log.d("debug", "4");
        //recyclerView = getView().findViewById(R.id.recyclerview);
        Log.d("debug", "5");
        adapter = new MyAdapter(getContext(), name, email, age, this);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        displaydata();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displaydata()
    {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                email.add(cursor.getString(1));
                age.add(cursor.getString(2));
            }
        }
    }

    public void onItemClick(int position) {
        Log.d("debug", "Befor");
        GKFragment gkFragment= new GKFragment(); //NextFragmetn is you detail or BFragmnent.
        Log.d("debug", "Befor1");
        View view = this.getView();
        Navigation.findNavController(view).navigate(R.id.list_gk);
        Log.d("debug", "Befor2");
    }


}