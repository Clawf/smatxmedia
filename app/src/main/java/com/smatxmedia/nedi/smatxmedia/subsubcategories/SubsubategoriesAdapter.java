package com.smatxmedia.nedi.smatxmedia.subsubcategories;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smatxmedia.nedi.smatxmedia.R;

import java.util.ArrayList;
import java.util.List;

import static com.smatxmedia.nedi.smatxmedia.R.id.tx_id;
import static com.smatxmedia.nedi.smatxmedia.R.id.tx_name;
import static com.smatxmedia.nedi.smatxmedia.R.id.tx_sub_id;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class SubsubategoriesAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public SubsubategoriesAdapter(@NonNull Context context, @LayoutRes int resource) {

        super(context, resource);

    }


    public void add(Subsubategories object) {
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
        SubsubcategoriesHolder subsubcategoriesHolder;
        if(row==null){
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.subsubcategories_row_layout,parent,false);
            subsubcategoriesHolder = new SubsubcategoriesHolder();
            subsubcategoriesHolder.tx_id=row.findViewById(tx_id);
            subsubcategoriesHolder.tx_sub_id=row.findViewById(tx_sub_id);
            subsubcategoriesHolder.tx_name=row.findViewById(tx_name);

            row.setTag(subsubcategoriesHolder);
        }
        else {

            subsubcategoriesHolder = (SubsubcategoriesHolder)row.getTag();

        }
        final Subsubategories subsubategories = (Subsubategories) this.getItem(position);
        subsubcategoriesHolder.tx_id.setText(subsubategories.getId());
        subsubcategoriesHolder.tx_name.setText(subsubategories.getName());
        subsubcategoriesHolder.tx_sub_id.setText(subsubategories.getSub_id());




       return row;
    }

    static class SubsubcategoriesHolder {

        TextView tx_id,tx_name,tx_sub_id;

    }
}
