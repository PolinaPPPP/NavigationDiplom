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
import com.example.navigationv10.R;
import com.example.navigationv10.RecyclerInterface;
import com.example.navigationv10.databinding.FragmentGKBinding;
import java.util.ArrayList;

public class GKFragment extends Fragment implements RecyclerInterface{

    ArrayList<String> gk_name, name_zas;
    DBHelper DB;
    MyGKAdapter adapter;
    public static String name_zas_replace = "";


    private FragmentGKBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGKBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        DB = new DBHelper(getContext());
        gk_name = new ArrayList<>();
        name_zas = new ArrayList<>();


        adapter = new MyGKAdapter(getContext(), gk_name, name_zas, this);
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

        Cursor cursor = DB.getdatagk(name_zas_replace);
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


                gk_name.add(cursor.getString(0));
                name_zas.add(cursor.getString(1));


            }
        }
    }

    public void onItemClick(int position) {

        GK_Info_Fragment gk_info_fragment = new GK_Info_Fragment();
        GK_Info_Fragment.name_gk_replace = gk_name.get(position);
        View view = this.getView();
        Navigation.findNavController(view).navigate(R.id.list_gk_info);

    }


}