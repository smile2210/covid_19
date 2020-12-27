package com.example.covid_19_tracker;

import com.example.covid_19_tracker.Model.CountryDatum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v3/covid-19/countries")
    Call<List<CountryDatum>> getData();
}
