package com.example.navigationv10.ui.SectionForm;


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
import com.example.navigationv10.databinding.FragmentSectionFormBinding;

import java.util.ArrayList;
import java.util.List;


public class SectionFormFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DBHelper DB;
    View view3;
    Spinner spinner;
    List<String> house_complexk;
    String save_name_house = null;

    private FragmentSectionFormBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view3 = inflater.inflate(R.layout.fragment_section_form, container, false);
        binding = FragmentSectionFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        house_complexk = new ArrayList<>();
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdatahousename();
        int i;
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            house_complexk.add("Выберите дом");
            for (i = 0; cursor.moveToNext(); i++) {
                house_complexk.add(cursor.getString(0));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, house_complexk);

        binding.spinnerHouse.setAdapter(adapter);

        binding.spinnerHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save_name_house = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        binding.btnInsertSection.setOnClickListener(this::onClick);
        binding.btnViewSection.setOnClickListener(this::onClick);
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
            case R.id.btnInsert_section:
                String section_namberTXT = binding.sectionNamber.getText().toString();
                String section_floorTXT = binding.sectionFloor.getText().toString();
                String section_sumTXT = binding.sectionSum.getText().toString();


                if(save_name_house == null || ("Выберите дом" == save_name_house)) {
                    Toast.makeText(getContext(), "Please select section", Toast.LENGTH_SHORT).show();
                    break;
                }


                Boolean checkinsertdata  = DB.insertsectiondata(section_namberTXT,save_name_house,section_floorTXT,section_sumTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnView_section:
                View view = this.getView();
                Navigation.findNavController(view).navigate(R.id.list_section);

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
