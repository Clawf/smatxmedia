package com.smatxmedia.nedi.smatxmedia;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubcategoriesDisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArraySubcategories;
    SubcategoriesAdapter subcategoriesAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategories_display_listview_layout);
        listView = (ListView) findViewById(R.id.subcategories_listview);

        //getting the json_string from the storage
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        json_string = sp.getString("json_string", null);

          listView.setAdapter(subcategoriesAdapter);
        try {
            jsonObject = new JSONObject(json_string);

            int countSubcategories = 0;
            String subcategory_id;
            String category_id;
            String subcategory_name;
            String cover;
            String subsubcategories;
            jsonArraySubcategories = jsonObject.getJSONArray("subcategories");
            subcategoriesAdapter = new SubcategoriesAdapter(this,R.layout.subcategories_row_layout);
            listView.setAdapter(subcategoriesAdapter);
            while(countSubcategories< jsonArraySubcategories.length()){


                JSONObject JO = jsonArraySubcategories.getJSONObject(countSubcategories);
                subcategory_id =  JO.getString("subcategory_id");
                category_id = JO.getString("category_id");
                subcategory_name =  JO.getString("subcategory_name");
                cover = JO.getString("cover");
                subsubcategories =  "wtfff";//JO.getString("subcategories");
                Subcategories subcategories = new Subcategories(subcategory_id,category_id,subcategory_name,cover,subsubcategories);
                subcategoriesAdapter.add(subcategories);
                countSubcategories ++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}