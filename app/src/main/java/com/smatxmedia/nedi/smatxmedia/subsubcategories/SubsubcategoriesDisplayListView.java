package com.smatxmedia.nedi.smatxmedia.subsubcategories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.smatxmedia.nedi.smatxmedia.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubsubcategoriesDisplayListView extends AppCompatActivity {

    //JSONObject jsonObject;
   JSONArray jsonArrayCategories;
    SubsubcategoriesAdapter subsubcategoriesAdapter;
    ListView listView;
    String subcategories_category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subsubcategories_display_listview_layout);
        listView = (ListView) findViewById(R.id.sub_subcategories__listview);

        //getting the json_string from the storage
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        //json_string = sp.getString("json_string", null);

        //getting the categories_category_id from the other activity
        subcategories_category_id = getIntent().getStringExtra("subsubcategories");


        subsubcategoriesAdapter = new SubsubcategoriesAdapter(this,R.layout.subsubcategories_row_layout);
        listView.setAdapter(subsubcategoriesAdapter);
        try {
           // jsonObject = new JSONObject(subcategories_category_id);
            jsonArrayCategories = new JSONArray(subcategories_category_id);
            int countCategories = 0;
            String id;
            String sub_id;
            String name;

            while(countCategories< jsonArrayCategories.length()){
                JSONObject JO = jsonArrayCategories.getJSONObject(countCategories);
                id =  JO.getString("sub_subcategory_id");
                sub_id = JO.getString("subcategory_id");
                name = JO.getString("sub_subcategory_name");

                Subsubcategories subsubcategories = new Subsubcategories(id,sub_id,name);
                subsubcategoriesAdapter.add(subsubcategories);
                countCategories ++;

            }
            if (subsubcategoriesAdapter.getCount() <=0){

                Toast.makeText(getBaseContext(), "List is empty!",
                        Toast.LENGTH_LONG).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}