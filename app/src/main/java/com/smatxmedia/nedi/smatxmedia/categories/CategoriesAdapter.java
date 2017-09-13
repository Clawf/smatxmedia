package com.smatxmedia.nedi.smatxmedia.categories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smatxmedia.nedi.smatxmedia.R;
import com.smatxmedia.nedi.smatxmedia.subcategories.SubcategoriesDisplayListView;

import java.util.ArrayList;
import java.util.List;

import static com.smatxmedia.nedi.smatxmedia.R.id.tx_id;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class CategoriesAdapter extends ArrayAdapter {

    private Context context;
    List list = new ArrayList();

    public CategoriesAdapter(@NonNull Context context, @LayoutRes int resource) {

        super(context, resource);
        this.context = context;
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
            row = layoutInflater.inflate(R.layout.categories_row_layout,parent,false);
            categoriesHolder = new CategoriesHolder();
            categoriesHolder.tx_id=row.findViewById(tx_id);
            categoriesHolder.tx_name=row.findViewById(R.id.tx_name);
            row.setTag(categoriesHolder);
        }
        else {

            categoriesHolder = (CategoriesHolder)row.getTag();

        }
        final Categories categories= (Categories) this.getItem(position);
        categoriesHolder.tx_id.setText(categories.getId());
        categoriesHolder.tx_name.setText(categories.getName());


        row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(context, SubcategoriesDisplayListView.class);
                    intent.putExtra("tx_id", categories.getId());
                    context.startActivity(intent);


            }
        });

       return row;
    }

    static class CategoriesHolder {

        TextView tx_id,tx_name;

    }
}
