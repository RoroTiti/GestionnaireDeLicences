package com.example.gestionnairedelicence.IHM.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.gestionnairedelicence.METIER.ESET;
import com.example.gestionnairedelicence.METIER.WINDOWS;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class ESETListViewAdapter extends ArrayAdapter<ESET> {
    // Your custom values for the spinner (User)
    private final ArrayList<ESET> values;
    private final Context context;

    public ESETListViewAdapter(Context context, int textViewResourceId, ArrayList<ESET> values) {
        super(context, textViewResourceId, values);
        // Your sent context
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public ESET getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ESET client = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.licence_listview_item, parent, false);
        }

        TextView lblLicence = convertView.findViewById(R.id.lblLicence);
        lblLicence.setText(client.getActivationKey());

        return convertView;
    }
}
