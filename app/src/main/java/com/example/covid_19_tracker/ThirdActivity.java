package com.example.covid_19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.covid_19_tracker.Model.CountryDatum;

public class ThirdActivity extends AppCompatActivity {

    private int position;
    TextView case1,todaycase1,totaldeaths1,todaydeath1,totalrecovered1,todayrecovered1,active1,critical1,population,continent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        case1 = findViewById(R.id.cases1);
        todaycase1 = findViewById(R.id.todaycases1);
        totaldeaths1 = findViewById(R.id.totaldeath1);
        todaydeath1 = findViewById(R.id.todaydeath1);
        totalrecovered1 = findViewById(R.id.totalrecoverd1);
        todayrecovered1 = findViewById(R.id.todayrecovered1);
        active1 = findViewById(R.id.active1);
        critical1 = findViewById(R.id.critical1);
        population = findViewById(R.id.population);
        continent = findViewById(R.id.continent);

        Intent intent = getIntent();

        position = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle(SecondActivity.modelList.get(position).getCountry());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        case1.setText(SecondActivity.modelList.get(position).getCases());
        todaycase1.setText(SecondActivity.modelList.get(position).getTodaycase());
        totaldeaths1.setText(SecondActivity.modelList.get(position).getDeath());
        todaydeath1.setText(SecondActivity.modelList.get(position).getTodaydeath());
        totalrecovered1.setText(SecondActivity.modelList.get(position).getRecovered());
        todayrecovered1.setText(SecondActivity.modelList.get(position).getTodayrecovered());
        active1.setText(SecondActivity.modelList.get(position).getActive());
        critical1.setText(SecondActivity.modelList.get(position).getCritical());
        population.setText(SecondActivity.modelList.get(position).getPopulation());
        continent.setText(SecondActivity.modelList.get(position).getContinent());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}