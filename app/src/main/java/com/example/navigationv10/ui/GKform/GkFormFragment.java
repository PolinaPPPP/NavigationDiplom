package com.example.navigationv10.ui.GKform;


import static java.sql.Types.NULL;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.navigationv10.DBHelper;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentFormGkBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GkFormFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DBHelper DB;
    View view2;
    Spinner spinner;
    List<String> zastroishiki;
    String save_name_zas = null;
    private FragmentFormGkBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view2 = inflater.inflate(R.layout.fragment_form_gk, container, false);
        binding = FragmentFormGkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        zastroishiki = new ArrayList<>();
        DB = new DBHelper(getContext());
        Cursor cursor = DB.getdata();
        int i;
        if(cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            zastroishiki.add("Выберите застройщика");
            for(i=0;cursor.moveToNext();i++) {
                zastroishiki.add(cursor.getString(0));
            }
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, zastroishiki);

        binding.spinnerZas.setAdapter(adapter);

        binding.spinnerZas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save_name_zas = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        binding.btnInsertGK.setOnClickListener(this::onClick);
        binding.btnViewGK.setOnClickListener(this::onClick);
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
            case R.id.btnInsert_GK:
                String gk_nameTXT = binding.gkName.getText().toString();
                String gk_adressTXT = binding.gkAdress.getText().toString();
                String gk_website = binding.gkWebsite.getText().toString();


                if(save_name_zas == null || ("Выберите застройщика" == save_name_zas)) {
                    Toast.makeText(getContext(), "Please select ZASTROISHIK", Toast.LENGTH_SHORT).show();
                    break;
                }


                Boolean checkinsertdata  = DB.insertgkdata(gk_nameTXT, gk_adressTXT,save_name_zas,gk_website );
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