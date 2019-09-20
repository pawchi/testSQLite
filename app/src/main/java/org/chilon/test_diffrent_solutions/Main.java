package org.chilon.test_diffrent_solutions;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Main extends AppCompatActivity {

    private static final String TAG = "Main";

    ListView listView;
    Button save, getFromDb, update, delete;
    EditText editCity, editCountry, editID, editDate;
    String idFromDb;
    ImageView sortId, sortCity, sortCountry, sortDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Log.d(TAG, "onCreate: Started.");
        listView = findViewById(R.id.listViewMain);
        save = findViewById(R.id.button_save_to_db);
        getFromDb = findViewById(R.id.button_get_from_db);
        update = findViewById(R.id.button_update_db);
        delete = findViewById(R.id.button_delete_from_db);
        final DataBaseHelper db = new DataBaseHelper(this);
        editCity = findViewById(R.id.editCity);
        editCountry = findViewById(R.id.editCountry);
        editID = findViewById(R.id.editID);
        editDate = findViewById(R.id.editDate);

        sortId = findViewById(R.id.sort_by_id);
        sortCity = findViewById(R.id.sort_by_city);
        sortCountry = findViewById(R.id.sort_by_country);
        sortDate = findViewById(R.id.sort_by_date);

        final ArrayList<City> cityArrayList = new ArrayList<>();
        final CityListAdapter adapter = new CityListAdapter(Main.this, R.layout.layout_adapter_list, cityArrayList);


        Cursor cursor = db.getDataFromDb();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()) {
                String id = (cursor.getString(0) + "\n");
                String city = ("CITY: " + cursor.getString(1) + "\n");
                String country = ("COUNTRY: " + cursor.getString(2) + "\n");
                String date = ("DATE: " + cursor.getString(3) + "\n");

                City cityItem = new City(id, city, country, date);
                cityArrayList.add(cityItem);
            }
        } else {
            String id = "1";
            String city = ("CITY: ");
            String country = ("COUNTRY: ");
            String date = ("DATE: ");

            City cityItem = new City(id, city, country, date);
            cityArrayList.add(cityItem);
        }

        sortId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sortCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sortCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(cityArrayList, new Comparator<City>() {
                    @Override
                    public int compare(City city1, City city2) {
                        return city1.getDateInDateForm().compareTo(city2.getDateInDateForm());
                    }
                });
                adapter.notifyDataSetChanged();

            }
        });

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                City object = cityArrayList.get(position);
                Toast toast = Toast.makeText(Main.this, object.country, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 150, -150);
                toast.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(new Date());
                db.addData(editCity.getText().toString(), editCountry.getText().toString(), strDate);
                String city = editCity.getText().toString();
                String country = editCountry.getText().toString();
                String mix = city + country;
                Toast.makeText(Main.this, mix, Toast.LENGTH_LONG ).show();
                finish();
                startActivity(getIntent());
            }
        });

        getFromDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getDataFromDb();
                if (cursor.getCount()>0){
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        stringBuffer.append("ID: " + cursor.getString(0) + "\n");
                        stringBuffer.append("CITY: " + cursor.getString(1) + "\n");
                        stringBuffer.append("COUNTRY: " + cursor.getString(2) + "\n");
                        stringBuffer.append("DATE: " + cursor.getString(3) + "\n");

                    }
                    Toast.makeText(Main.this, stringBuffer.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateById = editID.getText().toString();
                Cursor cursor = db.getDataById(updateById);

                if (cursor.getCount()>0){
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        stringBuffer.append(cursor.getString(0));
                    }
                    idFromDb = stringBuffer.toString();

                }

                if (idFromDb.equals(editID.getText().toString())){
                    db.updateDb(editID.getText().toString(), editCity.getText().toString(), editCountry.getText().toString(), editDate.getText().toString());
                    Toast.makeText(Main.this, "Update successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Main.this, "Update error, ID not found", Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(getIntent());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success;
                success = db.deleteItemFromDB(editID.getText().toString());
                if (success){
                    Toast.makeText(Main.this, "Item deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Main.this, "Delete error", Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(getIntent());
            }
        });

    }
}
