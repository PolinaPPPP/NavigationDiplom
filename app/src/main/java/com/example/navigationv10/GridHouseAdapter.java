package com.example.navigationv10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridHouseAdapter extends BaseAdapter {
    Context context;
    List<String> house_name;
    LayoutInflater inflater;

    public GridHouseAdapter(Context context, List<String> house_name) {
        this.context = context;
        this.house_name = house_name;
    }

    @Override
    public int getCount() {
        return house_name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_house, null);
        }

        TextView textView = convertView.findViewById((R.id.id_house_name));

        textView.setText(house_name.get(position));

        return convertView;
    }
}
