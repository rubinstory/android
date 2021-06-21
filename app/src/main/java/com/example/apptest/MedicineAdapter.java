package com.example.apptest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.apptest.MedicineItem;
import com.example.apptest.MedicineItemView;

import java.util.ArrayList;

class MedicineAdapter extends BaseAdapter { // 약품 리스트뷰 출력을 위한 클래스
    ArrayList<MedicineItem> itemList = new ArrayList<MedicineItem>(); // 약품 정보를 리스트로 저장하는 변수
    Context context;
    public MedicineAdapter(Context context) { this.context = context; }

    public void addItem(MedicineItem item) { itemList.add(item); }
    @Override public int getCount() { return itemList.size(); }
    @Override public Object getItem(int index) { return itemList.get(index); }
    @Override public long getItemId(int index) { return index; }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        MedicineItemView medicineView;
        if (convertView == null) { medicineView = new MedicineItemView(context); }
        else medicineView = (MedicineItemView) convertView;

        MedicineItem item = itemList.get(index);
        medicineView.setName(item.getName());
        medicineView.setRate(item.getRate());
        medicineView.setImage(item.getImage());
        return medicineView;
    }
}

