package com.example.navigationv10.ui.GKform;


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
import com.example.navigationv10.databinding.FragmentFormGkBinding;



public class GkFormFragment extends Fragment implements View.OnClickListener{

    DBHelper DB;
    View view2;

    private FragmentFormGkBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view2 = inflater.inflate(R.layout.fragment_form_gk, container, false);
        binding = FragmentFormGkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
        Log.d("debug", "Open OnClick");
        switch (v.getId()) {
            case R.id.btnInsert_GK:
                String gk_nameTXT = binding.gkName.getText().toString();
                String gk_adressTXT = binding.gkAdress.getText().toString();
                Log.d("test test", gk_nameTXT);
                Log.d("test test", gk_adressTXT);


                Boolean checkinsertdata  = DB.insertgkdata(gk_nameTXT, gk_adressTXT);
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
                Log.d("test", "before intent");


                break;
        }
    }
}