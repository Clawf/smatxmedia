package com.smatxmedia.nedi.smatxmedia;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class CategoriesAdapter extends ArrayAdapter {


    List list = new ArrayList();

    public CategoriesAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    public void add(Categories object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        CategoriesHolder categoriesHolder;
        if(row==null){
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            categoriesHolder = new CategoriesHolder();
            categoriesHolder.tx_id=row.findViewById(R.id.tx_id);
            categoriesHolder.tx_name=row.findViewById(R.id.tx_name);
            row.setTag(categoriesHolder);
        }
        else {

            categoriesHolder = (CategoriesHolder)row.getTag();

        }
        Categories categories= (Categories) this.getItem(position);
        categoriesHolder.tx_id.setText(categories.getId());
        categoriesHolder.tx_name.setText(categories.getName());
       return row;
    }

    static class CategoriesHolder {

        TextView tx_id,tx_name;

    }
}
