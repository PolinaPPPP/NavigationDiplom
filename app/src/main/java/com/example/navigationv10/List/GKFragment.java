package com.example.navigationv10.List;

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
import com.example.navigationv10.MyAdapter;
import com.example.navigationv10.MyGKAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.RecyclerInterface;
import com.example.navigationv10.databinding.FragmentFormGkBinding;
import com.example.navigationv10.databinding.FragmentGKBinding;
import com.example.navigationv10.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class GKFragment extends Fragment implements RecyclerInterface{

    ArrayList<String> gk_name,gk_adress;
    DBHelper DB;
    MyGKAdapter adapter;
    //public String namebulder = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
    private FragmentGKBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("test", "before intent");

        binding = FragmentGKBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        DB = new DBHelper(getContext());
        gk_name = new ArrayList<>();
        gk_adress = new ArrayList<>();

        adapter = new MyGKAdapter(getContext(), gk_name, gk_adress,  this);
        binding.gkrecyclerview.setAdapter(adapter);
        binding.gkrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
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
        Cursor cursor = DB.getdatagk(); //getdatagk для второй таблицы
        if(cursor.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                //Log.d("qwe", namebulder);
                gk_name.add(cursor.getString(0));
                gk_adress.add(cursor.getString(1));

            }
        }
    }

    public void onItemClick(int position) {
        Log.d("debug", "Befor");
        GKFragment gkFragment= new GKFragment();
        Log.d("debug", "Befor1");
        View view = this.getView();
        Navigation.findNavController(view).navigate(R.id.list_gk);
        Log.d("debug", "Befor2");
    }


}