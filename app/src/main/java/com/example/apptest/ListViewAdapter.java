package com.example.apptest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.form, parent, false);
        }
        ImageView image = (ImageView)convertView.findViewById(R.id.userimage);
        RatingBar stars = (RatingBar)convertView.findViewById(R.id.star);
        TextView text = (TextView)convertView.findViewById(R.id.comment);
        TextView name = (TextView)convertView.findViewById(R.id.username);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        image.setImageDrawable(listViewItem.getIcon());
        stars.setNumStars(5);
        stars.setRating(listViewItem.getNumStar());
        text.setText(listViewItem.getText());
        name.setText(listViewItem.getName());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(Drawable icon, float stars, String name, String text)
    {
        ListViewItem item = new ListViewItem();
        item.setIcon(icon);
        item.setNumStar(stars);
        item.setName(name);
        item.setText(text);


        listViewItemList.add(item);
    }
}
