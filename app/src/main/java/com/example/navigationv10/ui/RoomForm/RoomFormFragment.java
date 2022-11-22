package com.example.navigationv10.ui.RoomForm;

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
import com.example.navigationv10.databinding.FragmentRoomFormBinding;
import com.example.navigationv10.databinding.FragmentSectionFormBinding;

import java.util.ArrayList;
import java.util.List;


public class RoomFormFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DBHelper DB;
    View view4;
    Spinner spinner;
    List<String> section_complexk;
    String save_number_section = null;

    private FragmentRoomFormBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view4 = inflater.inflate(R.layout.fragment_room_form, container, false);
        binding = FragmentRoomFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        section_complexk = new ArrayList<>();
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdatasectionnumber();
        int i;
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            section_complexk.add("Выберите секцию");
            for (i = 0; cursor.moveToNext(); i++) {
                section_complexk.add(cursor.getString(0));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, section_complexk);

        binding.spinnerSection.setAdapter(adapter);

        binding.spinnerSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save_number_section = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        binding.btnInsertRoom.setOnClickListener(this::onClick);
        binding.btnViewRoom.setOnClickListener(this::onClick);
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
            case R.id.btnInsert_room:
                String room_nameTXT = binding.roomName.getText().toString();
                String room_priceTXT = binding.roomPrice.getText().toString();
                String room_squareTXT = binding.roomSquare.getText().toString();


                if(save_number_section == null || ("Выберите секцию" == save_number_section)) {
                    Toast.makeText(getContext(), "Please select section", Toast.LENGTH_SHORT).show();
                    break;
                }


                Boolean checkinsertdata  = DB.insertroomdata(room_nameTXT,save_number_section,room_priceTXT,room_squareTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnView_room:
                View view = this.getView();
                Navigation.findNavController(view).navigate(R.id.list_room);

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