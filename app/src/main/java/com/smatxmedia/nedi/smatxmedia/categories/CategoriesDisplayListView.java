package com.smatxmedia.nedi.smatxmedia.categories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.smatxmedia.nedi.smatxmedia.R;
import com.smatxmedia.nedi.smatxmedia.subcategories.SubcategoriesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class CategoriesDisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArrayCategories;
    JSONArray jsonArraySubcategories;
    CategoriesAdapter categoriesAdapter;
    SubcategoriesAdapter subcategoriesAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_display_listview_layout);
        listView = (ListView) findViewById(R.id.listview);

        json_string = getIntent().getStringExtra("json_string");
        categoriesAdapter = new CategoriesAdapter(this,R.layout.categories_row_layout);
        listView.setAdapter(categoriesAdapter);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArrayCategories = jsonObject.getJSONArray("categories");
            int countCategories = 0;
            String id;
            String name;

            while(countCategories< jsonArrayCategories.length()){
                JSONObject JO = jsonArrayCategories.getJSONObject(countCategories);
                id =  JO.getString("category_id");
                name = JO.getString("category_name");
                Categories categories = new Categories(id,name);
                categoriesAdapter.add(categories);
                countCategories ++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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