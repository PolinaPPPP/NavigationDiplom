package com.example.navigationv10.ui.form;

import static androidx.navigation.Navigation.findNavController;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.navigationv10.DBHelper;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentFormBinding;
import com.example.navigationv10.databinding.FragmentHomeBinding;

import com.example.navigationv10.ui.home.HomeFragment;

public class FormFragment extends Fragment implements View.OnClickListener{


    DBHelper DB;
    View view1;

    private FragmentFormBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view1 = inflater.inflate(R.layout.fragment_form, container, false);
        FormViewModel formViewModel =
                new ViewModelProvider(this).get(FormViewModel.class);

        binding = FragmentFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.btnInsert.setOnClickListener(this::onClick);
        binding.btnView.setOnClickListener(this::onClick);
        binding.btnDelete.setOnClickListener(this::onClick);
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
            case R.id.btnInsert:
                String nameTXT = binding.name.getText().toString();
                String emailTXT = binding.email.getText().toString();
                String ageTXT = binding.age.getText().toString();
                Log.d("test test", nameTXT);
                Log.d("test test", emailTXT);
                Log.d("test test", ageTXT);

                Boolean checkinsertdata  = DB.insertuserdata(nameTXT, emailTXT, ageTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnView:
                View view = this.getView();
                Navigation.findNavController(view).navigate(R.id.nav_home);
                Log.d("test", "before intent");
                Log.d("test", "after intent");
                Log.d("test", "after startActivity");


                break;
        }
    }
}