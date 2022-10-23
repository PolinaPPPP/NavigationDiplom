package com.example.navigationv10.ui.form;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.navigationv10.DBHelper;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentFormBinding;


public class FormFragment extends Fragment implements View.OnClickListener{

    DBHelper DB;
    View view1;

    private FragmentFormBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view1 = inflater.inflate(R.layout.fragment_form, container, false);
        binding = FragmentFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnInsert.setOnClickListener(this::onClick);
        binding.btnView.setOnClickListener(this::onClick);

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
                String phoneTXT = binding.phone.getText().toString();


                Boolean checkinsertdata  = DB.insertuserdata(nameTXT, emailTXT, phoneTXT);
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



                break;
        }
    }
}