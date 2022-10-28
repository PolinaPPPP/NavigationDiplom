package com.example.navigationv10.ui.HouseForm;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentFormGkBinding;
import com.example.navigationv10.databinding.FragmentHouseFormBinding;

import java.util.ArrayList;
import java.util.List;


public class HouseFormFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DBHelper DB;
    View view3;
    Spinner spinner;
    List<String> gk_complexk;
    String save_name_gk = null;

    private FragmentHouseFormBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view3 = inflater.inflate(R.layout.fragment_house_form, container, false);
        binding = FragmentHouseFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gk_complexk = new ArrayList<>();
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdatagkname();
        int i;
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            gk_complexk.add("Выберите жилой комплекс");
            for (i = 0; cursor.moveToNext(); i++) {
                gk_complexk.add(cursor.getString(0));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gk_complexk);

        binding.spinnerGk.setAdapter(adapter);

        binding.spinnerGk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save_name_gk = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        binding.btnInsertHouse.setOnClickListener(this::onClick);
        binding.btnViewHouse.setOnClickListener(this::onClick);
        DB = new DBHelper(getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnInsert_house:
                String house_nameTXT = binding.houseName.getText().toString();




                if(save_name_gk == null || ("Выберите жилой комплекс" == save_name_gk)) {
                    Toast.makeText(getContext(), "Please select GK", Toast.LENGTH_SHORT).show();
                    break;
                }


                Boolean checkinsertdata  = DB.inserthousedata(house_nameTXT, save_name_gk);
                if(checkinsertdata==true)
                {
                    Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnView_GK:
                View view = this.getView();
                Navigation.findNavController(view).navigate(R.id.list_gk);

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v("item", (String) parent.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}