package com.example.navigationv10.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

    ArrayList<String> name, email, phone;
    DBHelper DB;
    MyAdapter adapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        DB = new DBHelper(getContext());
        name = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();
        adapter = new MyAdapter(getContext(), name, email, phone, this);
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
                phone.add(cursor.getString(2));
            }
        }
    }

    public void onItemClick(int position) {
        GKFragment gkFragment = new GKFragment();
        GKFragment.name_zas_replace = name.get(position);
        View view = this.getView();
        Navigation.findNavController(view).navigate(R.id.list_gk);

    }


}