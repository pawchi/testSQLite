package org.chilon.test_diffrent_solutions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityListAdapter extends ArrayAdapter<City> {
    private static final String TAG = "CityListAdapter";
    private Context mContext;
    int mResource;

    public CityListAdapter(Context context, int resource, ArrayList<City> objects) {
        super(context, resource, objects);

        mContext=context;
        mResource=resource;
    }
        @NonNull
        @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String cityName = getItem(position).getName();
        String countryName = getItem(position).getCountry();
        String id = getItem(position).getId();
        String date = getItem(position).getDate();


            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView txId = (TextView) convertView.findViewById(R.id.textId);
            TextView txName = (TextView) convertView.findViewById(R.id.textLeftCenter);
            TextView txCountry = (TextView) convertView.findViewById(R.id.textHighRight);
            TextView txDate = (TextView) convertView.findViewById(R.id.textLowRight);

            txId.setText(id);
            txName.setText(cityName);
            txCountry.setText(countryName);
            txDate.setText(date);

            return convertView;
    }
}
