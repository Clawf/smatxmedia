package com.smatxmedia.nedi.smatxmedia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smatxmedia.nedi.smatxmedia.categories.CategoriesDisplayListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String json_string;
    TextView responseView;
    ProgressBar progressBar;
    //  static final String API_KEY = "USE_YOUR_OWN_API_KEY";
    //  static final String API_URL = "https://api.fullcontact.com/v2/person.json?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseView = (TextView) findViewById(R.id.responseView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        json_string = sp.getString("json_string", null);
        if(json_string!=null){


            System.out.println(000000000000000000000000+json_string);
            responseView.setText(json_string);

        }

        Button getJsonButton = (Button) findViewById(R.id.getJsonButton);
        getJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });

        Button praseJsonButton = (Button) findViewById(R.id.parseJsonButton);
        praseJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(json_string==null){

                    Toast.makeText(getApplicationContext(),"Get JSON at least 1 time ", Toast.LENGTH_LONG).show();



                }else {

                    Intent intent = new Intent(getBaseContext(), CategoriesDisplayListView.class);
                    intent.putExtra("json_string", json_string);
                    startActivity(intent);

                }

            }
        });


    }


    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
              //  URL url = new URL("http://dev.mobiletv.bg/4P1/kidsvod/json.php?user=veroun1@gmail.com&pass=test1&mode=categories");
                String urlString = new String("http://dev.mobiletv.bg/4P1/kidsvod/json.php?user=veroun1@gmail.com&pass=test1&mode=categories");

                Authenticator.setDefault(new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("test1","test1".toCharArray());
                    }});
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(urlString).openConnection();
                urlConnection. setUseCaches(false);
                urlConnection.connect();

                //HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            json_string = response;
            responseView.setText(response);

            SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("json_string", response);
            editor.commit();

        }
    }
}

