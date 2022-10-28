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
import com.example.navigationv10.MyGKAdapter;
import com.example.navigationv10.MyHouseAdapter;
import com.example.navigationv10.R;
import com.example.navigationv10.RecyclerInterface;
import com.example.navigationv10.databinding.FragmentGKBinding;
import com.example.navigationv10.databinding.FragmentHouseBinding;

import java.util.ArrayList;


public class HouseFragment extends Fragment implements RecyclerInterface {

    ArrayList<String> house_name, house_gk;
    DBHelper DB;
    MyHouseAdapter adapter;
    public static String house_gk_replace = "";

    private FragmentHouseBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHouseBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        DB = new DBHelper(getContext());
        house_name = new ArrayList<>();
        house_gk = new ArrayList<>();


        adapter = new MyHouseAdapter(getContext(), house_name, house_gk, this);
        binding.houserecyclerview.setAdapter(adapter);
        binding.houserecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
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

        Cursor cursor = DB.getdatahouse(house_gk_replace);
        Log.d("displaydata", "cursor.getCount()="+cursor.getCount());
        if(cursor.getCount()==0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {


                house_name.add(cursor.getString(0));
                house_gk.add(cursor.getString(1));


            }
        }
    }

    public void onItemClick(int position) {

       // GK_Info_Fragment gk_info_fragment = new GK_Info_Fragment();
       // GK_Info_Fragment.name_gk_replace = gk_name.get(position);
       // View view = this.getView();
       // Navigation.findNavController(view).navigate(R.id.list_gk_info);

    }


}