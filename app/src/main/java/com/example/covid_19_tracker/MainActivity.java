package com.example.covid_19_tracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    Button affectedcountry;
    LinearLayout scrollView;
    TextView cases,todaycase,totaldeath,todaydeath,recovered,todayrecovered,active,critical,affectedcountries;

    String URL = "https://disease.sh/v3/covid-19/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.piechart);
        affectedcountry = findViewById(R.id.affectedcountry);
        scrollView = findViewById(R.id.globelstates);

        cases = findViewById(R.id.cases);
        todaycase = findViewById(R.id.todaycase);
        totaldeath = findViewById(R.id.death);
        todaydeath = findViewById(R.id.todaydeath);
        recovered = findViewById(R.id.recovered);
        todayrecovered = findViewById(R.id.todayrecovered);
        active = findViewById(R.id.active);
        critical = findViewById(R.id.critical);
        affectedcountries = findViewById(R.id.affectedcountries);

        fatchData();

        affectedcountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Are you sure you want to Exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("No", null);
        dialog.create();
        dialog.show();
    }

    private void fatchData() {
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    cases.setText(jsonObject.getString("cases"));
                    todaycase.setText(jsonObject.getString("todayCases"));
                    totaldeath.setText(jsonObject.getString("deaths"));
                    todaydeath.setText(jsonObject.getString("todayDeaths"));
                    recovered.setText(jsonObject.getString("recovered"));
                    todayrecovered.setText(jsonObject.getString("todayRecovered"));
                    active.setText(jsonObject.getString("active"));
                    critical.setText(jsonObject.getString("critical"));
                    affectedcountries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(cases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Death",Integer.parseInt(totaldeath.getText().toString()), Color.parseColor("#Ef5350")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#2986F6")));
                    pieChart.startAnimation();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);




    }
}