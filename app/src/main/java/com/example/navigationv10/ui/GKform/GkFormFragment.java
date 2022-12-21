package com.example.navigationv10.ui.GKform;


import static java.sql.Types.NULL;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.navigationv10.DBHelper;
import com.example.navigationv10.R;
import com.example.navigationv10.databinding.FragmentFormGkBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;


public class GkFormFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    DBHelper DB;
    View view2;
    Spinner spinner;
    List<String> zastroishiki;
    String save_name_zas = null;
    private FragmentFormGkBinding binding;

    private static int RESULT_LOAD_IMAGE = 1;



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
        binding.btnimageplane.setOnClickListener(this::onClick);
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
                String gk_bild = binding.gkBild.getText().toString();
                String gk_territory = binding.gkTerritory.getText().toString();
                String gk_parking = binding.gkParking.getText().toString();

                if(save_name_zas == null || ("Выберите застройщика" == save_name_zas)) {
                    Toast.makeText(getContext(), "Please select ZASTROISHIK", Toast.LENGTH_SHORT).show();
                    break;
                }


                Boolean checkinsertdata  = DB.insertgkdata(gk_nameTXT, gk_adressTXT,save_name_zas,gk_website, gk_bild, gk_territory, gk_parking);
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
            case R.id.btnimageplane:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Context context = null;
            Cursor cursor = context.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            binding.imgplane.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            binding.btnimageplane.setEnabled(true);
            cursor.close();

        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT)
                    .show();
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