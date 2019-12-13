package com.raflyjr.recipe;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipes extends AppCompatActivity {
    private String query;
    private String KEY_QUERY = "EGG";
    ListView listNews;
    ProgressBar loader;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_MEALS = "meals";
    static final String KEY_IDMEAL = "idMeal";
    static final String KEY_STRMEAL = "strMeal";
    static final String KEY_STRCATEGORY = "strCategory";
    static final String KEY_STRINSTRUCTIONS = "strInstructions";
    static final String KEY_STRMEALTHUMB = "strMealThumb";
    static final String KEY_STRYOUTUBE = "strYoutube";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Bundle getData = getIntent().getExtras();
        query = getData.getString(KEY_QUERY);


        listNews = findViewById(R.id.listNews);
        loader = findViewById(R.id.loader);
        listNews.setEmptyView(loader);


        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }



    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        protected String doInBackground(String... args) {
            String xml = Function.excuteGet("https://www.themealdb.com/api/json/v1/1/search.php?s=EGG");
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            if (xml.length() > 1) { // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("meals");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put(KEY_IDMEAL, jsonObject.optString(KEY_IDMEAL));
                        map.put(KEY_STRMEAL, jsonObject.optString(KEY_STRMEAL));
                        map.put(KEY_STRCATEGORY, jsonObject.optString(KEY_STRCATEGORY));
                        map.put(KEY_STRINSTRUCTIONS, jsonObject.optString(KEY_STRINSTRUCTIONS));
                        map.put(KEY_STRMEALTHUMB, jsonObject.optString(KEY_STRMEALTHUMB));
                        map.put(KEY_STRYOUTUBE, jsonObject.optString(KEY_STRYOUTUBE));
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }

                ListNewsAdapter adapter = new ListNewsAdapter(Recipes.this, dataList);
                listNews.setAdapter(adapter);

                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent i = new Intent(Recipes.this, DetailsActivity.class);
                        i.putExtra("url", dataList.get(+position).get(KEY_STRYOUTUBE));
                        startActivity(i);
                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "No recipe found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}