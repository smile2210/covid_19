package com.example.covid_19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19_tracker.Adapter.MyAdapter;
import com.example.covid_19_tracker.Model.ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    
    EditText search;
    RecyclerView countrylist;

    String URL = "https://disease.sh/v3/covid-19/countries";

    public static List<ModelClass> modelList = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        search = findViewById(R.id.search);
        countrylist = findViewById(R.id.countrylist);

        getSupportActionBar().setTitle("Affected Countries");


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                adapter.getFilter().filter(charSequence);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        
        showData();
    }

    @Override
    public void onBackPressed() {
        adapter.modelListfiltered.clear();
        finish();
    }

    private void showData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ModelClass model = new ModelClass();
                        model.setCountry(jsonObject.getString("country"));
                        model.setCases(jsonObject.getString("cases"));
                        model.setTodaycase(jsonObject.getString("todayCases"));
                        model.setDeath(jsonObject.getString("deaths"));
                        model.setTodaydeath(jsonObject.getString("todayDeaths"));
                        model.setRecovered(jsonObject.getString("recovered"));
                        model.setTodayrecovered(jsonObject.getString("todayRecovered"));
                        model.setActive(jsonObject.getString("active"));
                        model.setCritical(jsonObject.getString("critical"));
                        model.setPopulation(jsonObject.getString("population"));
                        model.setContinent(jsonObject.getString("continent"));

                        JSONObject object = jsonObject.getJSONObject("countryInfo");

                        model.setFlag(object.getString("flag"));

                        modelList.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                RecyclerView.LayoutManager manager = new LinearLayoutManager(SecondActivity.this,RecyclerView.VERTICAL,false);
                adapter = new MyAdapter(SecondActivity.this,modelList);
                countrylist.setAdapter(adapter);
                countrylist.setLayoutManager(manager);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(SecondActivity.this);
        queue.add(jsonArrayRequest);
    }
}