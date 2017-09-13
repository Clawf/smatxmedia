package com.smatxmedia.nedi.smatxmedia.subcategories;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smatxmedia.nedi.smatxmedia.R;
import com.smatxmedia.nedi.smatxmedia.subsubcategories.SubsubcategoriesDisplayListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.smatxmedia.nedi.smatxmedia.R.id.cover;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class SubcategoriesAdapter extends ArrayAdapter {


    private Context context;
    List list = new ArrayList();

    public SubcategoriesAdapter(@NonNull Context context, @LayoutRes int resource) {

        super(context, resource);
        this.context = context;
    }


    public void add(Subcategories object) {
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
        SubcategoriesHolder subcategoriesHolder;
        if(row==null){
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.subcategories_row_layout,parent,false);
            subcategoriesHolder = new SubcategoriesHolder();

            subcategoriesHolder.cover=row.findViewById(cover);

            subcategoriesHolder.category_id=row.findViewById(R.id.category_id);
            subcategoriesHolder.subcategory_name=row.findViewById(R.id.subcategory_name);
            subcategoriesHolder.subcategory_id=row.findViewById(R.id.subcategory_id);
            row.setTag(subcategoriesHolder);
        }
        else {

            subcategoriesHolder = (SubcategoriesHolder)row.getTag();

        }
        final Subcategories subcategories= (Subcategories) this.getItem(position);
        new DownloadImageTask((ImageView) row.findViewById(cover))
                .execute("https://tvc.mobiletv.bg/sxm/images/subcategory/"+subcategories.getCover());
      //  subcategoriesHolder.cover.setText(subcategories.getCover());

        subcategoriesHolder.category_id.setText("ID " + subcategories.getCategory_id());
        subcategoriesHolder.subcategory_name.setText( subcategories.getSubcategory_name());
        subcategoriesHolder.subcategory_id.setText("SubID " + subcategories.getSubcategory_id());


        row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, SubsubcategoriesDisplayListView.class);
                intent.putExtra("subsubcategories", subcategories.getSubsubcategories());
                context.startActivity(intent);


            }
        });

       return row;
    }

    static class SubcategoriesHolder {
        ImageView cover;
        TextView category_id,subcategory_name,subcategory_id;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
