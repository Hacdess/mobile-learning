package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {
    Context context;
    Integer[] thumbnails;
    String[] names;
    public ListAdapter( Context context, int layoutToBeInflated, String[] names, Integer[] thumbnails) {
        super(context, R.layout.list_item, items);
        this.context= context;
        this.thumbnails= thumbnails;
        this.names= names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item, null);
        TextView name = (TextView) row.findViewById(R.id.name);
        TextView phone = (TextView) row.findViewById(R.id.phone);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);
        name.setText(names[position]);
        icon.setImageResource(thumbnails[position]);
        return(row);
    }
}
