package com.smatxmedia.nedi.smatxmedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CategoriesAdapter categoriesAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView) findViewById(R.id.listview);

        json_string = getIntent().getStringExtra("json_string");
        categoriesAdapter = new CategoriesAdapter(this,R.layout.row_layout);
        listView.setAdapter(categoriesAdapter);
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("categories");
            int count = 0;
            String id;
            String name;

            while(count<jsonArray.length()){


                JSONObject JO = jsonArray.getJSONObject(count);
                id =  JO.getString("category_id");
                name = JO.getString("category_name");
                Categories categories = new Categories(id,name);
                categoriesAdapter.add(categories);
                count ++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
