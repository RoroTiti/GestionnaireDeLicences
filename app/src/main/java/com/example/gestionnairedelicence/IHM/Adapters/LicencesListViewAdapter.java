package com.example.gestionnairedelicence.IHM.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class LicencesListViewAdapter extends ArrayAdapter<Licence> {
    // Your custom values for the spinner (User)
    private final ArrayList<Licence> values;
    private final Context context;

    public LicencesListViewAdapter(Context context, int textViewResourceId, ArrayList<Licence> values) {
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
    public Licence getItem(int position) {
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
        Licence user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.affectation_listview_item, parent, false);
        }

        TextView lblCleActivation = convertView.findViewById(R.id.lblLicence);
        lblCleActivation.setText(user.getActivationKey());

        TextView lblLogiciel = convertView.findViewById(R.id.lblLogiciel);
        lblLogiciel.setText(user.getLogiciel());

        TextView lblDateAchat = convertView.findViewById(R.id.lblDateAchat);
        lblDateAchat.setText(user.getDateAchat());

        TextView lblClient = convertView.findViewById(R.id.lblLicence);
        lblClient.setText(user.getClient());

        return convertView;
    }
}
