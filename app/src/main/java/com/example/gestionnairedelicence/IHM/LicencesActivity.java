package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionnairedelicence.DAO.EsetDAO;
import com.example.gestionnairedelicence.DAO.WindowsDAO;
import com.example.gestionnairedelicence.IHM.Adapters.ESETListViewAdapter;
import com.example.gestionnairedelicence.IHM.Adapters.WindowsListViewAdapter;
import com.example.gestionnairedelicence.METIER.ESET;
import com.example.gestionnairedelicence.METIER.WINDOWS;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class LicencesActivity extends AppCompatActivity {
    ListView lvWindows, lvEset;
    ArrayList<WINDOWS> windows;
    ArrayList<ESET> esets;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> init()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licences);

        lvWindows = findViewById(R.id.lvWindows);
        lvEset = findViewById(R.id.lvEset);

        init();

        lvWindows.setOnItemClickListener(windowsSectionne);
        lvEset.setOnItemClickListener(esetSectionne);
    }

    private void init() {
        WindowsDAO windowsDAO = new WindowsDAO(this);
        windowsDAO.open();
        windows = windowsDAO.read();
        windowsDAO.close();

        EsetDAO esetDAO = new EsetDAO(this);
        esetDAO.open();
        esets = esetDAO.read();
        esetDAO.close();

        WindowsListViewAdapter windowsAdapter = new WindowsListViewAdapter(this, android.R.layout.simple_spinner_item, windows);
        lvWindows.setAdapter(windowsAdapter);

        ESETListViewAdapter esetAdapter = new ESETListViewAdapter(this, android.R.layout.simple_spinner_item, esets);
        lvEset.setAdapter(esetAdapter);
    }

    private final AdapterView.OnItemClickListener windowsSectionne = (adapterView, view, i, l) -> {
        int idWindows = windows.get(i).getIdWindows();

        Intent iConsult = new Intent(this, ClientActivity.class);
        iConsult.putExtra("idWindows", idWindows);
        someActivityResultLauncher.launch(iConsult);
    };

    private final AdapterView.OnItemClickListener esetSectionne = (adapterView, view, i, l) -> {
        int idEset = esets.get(i).getIdEset();

        Intent iConsult = new Intent(this, ClientActivity.class);
        iConsult.putExtra("idEset", idEset);
        someActivityResultLauncher.launch(iConsult);
    };
}