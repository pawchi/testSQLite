package org.chilon.test_diffrent_solutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class City {
    String id;
    String name;
    String country;
    String date;

    public City(String id, String name, String country, String date) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDateInDateForm(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return date1;
    }
}
