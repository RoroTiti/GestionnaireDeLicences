package com.example.gestionnairedelicence.IHM.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class ClientsListViewAdapter extends ArrayAdapter<CLIENT> {
    // Your custom values for the spinner (User)
    private final ArrayList<CLIENT> values;
    private final Context context;

    public ClientsListViewAdapter(Context context, int textViewResourceId, ArrayList<CLIENT> values) {
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
    public CLIENT getItem(int position) {
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
        CLIENT client = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_listview_item, parent, false);
        }

        TextView lblClient = convertView.findViewById(R.id.lblClient);
        lblClient.setText(client.toString());

        return convertView;
    }
}
