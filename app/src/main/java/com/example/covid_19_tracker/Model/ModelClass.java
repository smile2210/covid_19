package com.example.covid_19_tracker.Model;

public class ModelClass {

    String country,flag,cases,todaycase,death,todaydeath,recovered,todayrecovered,active,critical,population,continent;

    public ModelClass() {
    }

    public ModelClass(String country, String flag, String cases, String todaycase, String death, String todaydeath, String recovered, String todayrecovered, String active, String critical, String population, String continent) {
        this.country = country;
        this.flag = flag;
        this.cases = cases;
        this.todaycase = todaycase;
        this.death = death;
        this.todaydeath = todaydeath;
        this.recovered = recovered;
        this.todayrecovered = todayrecovered;
        this.active = active;
        this.critical = critical;
        this.population = population;
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycase() {
        return todaycase;
    }

    public void setTodaycase(String todaycase) {
        this.todaycase = todaycase;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getTodaydeath() {
        return todaydeath;
    }

    public void setTodaydeath(String todaydeath) {
        this.todaydeath = todaydeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayrecovered() {
        return todayrecovered;
    }

    public void setTodayrecovered(String todayrecovered) {
        this.todayrecovered = todayrecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
