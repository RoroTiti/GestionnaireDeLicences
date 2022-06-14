package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionnairedelicence.DAO.ClientDAO;
import com.example.gestionnairedelicence.DAO.EsetDAO;
import com.example.gestionnairedelicence.DAO.LiaisonDAO;
import com.example.gestionnairedelicence.DAO.WindowsDAO;
import com.example.gestionnairedelicence.IHM.Adapters.ClientsSpinnerAdapter;
import com.example.gestionnairedelicence.IHM.Adapters.ESETSpinnerAdapter;
import com.example.gestionnairedelicence.IHM.Adapters.WindowsSpinnerAdapter;
import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.METIER.ESET;
import com.example.gestionnairedelicence.METIER.LIAISON;
import com.example.gestionnairedelicence.METIER.WINDOWS;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class AffectationActivity extends AppCompatActivity {
    private Spinner spLicences, spClients;
    RadioButton rbEset, rbWindows;
    Button btValider;
    LIAISON liaisonSelectionnee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affectation);

        Intent intent = getIntent();
        int idLiaison = intent.getIntExtra("idLiaison", 0);

        if (idLiaison != 0) {
            LiaisonDAO liaisonDAO = new LiaisonDAO(this);
            liaisonDAO.open();

            liaisonSelectionnee = liaisonDAO.read(idLiaison);
            liaisonDAO.close();
        }

        rbEset = findViewById(R.id.rbEset);
        rbWindows = findViewById(R.id.rbWindows);
        spLicences = findViewById(R.id.spLicences);
        spClients = findViewById(R.id.spClients);
        btValider = findViewById(R.id.btValider);

        rbWindows.setOnCheckedChangeListener(cocheChange);
        rbEset.setOnCheckedChangeListener(cocheChange);

        btValider.setOnClickListener(btValiderAffectationClic);

        ClientDAO clientDAO = new ClientDAO(this);
        clientDAO.open();

        ArrayList<CLIENT> clients = clientDAO.read();
        clientDAO.close();

        ClientsSpinnerAdapter adapter = new ClientsSpinnerAdapter(this, android.R.layout.simple_spinner_item, clients);
        spClients.setAdapter(adapter); // Set the custom adapter to the spinner

        if (idLiaison != 0) {
            btValider.setText("Modifier");

            if (liaisonSelectionnee.getWindows() != null) {
                rbWindows.setChecked(true);
            } else {
                rbEset.setChecked(true);
            }

            int index = clients.indexOf(liaisonSelectionnee.getClient());
            spClients.setSelection(index);
        } else {
            rbWindows.setChecked(true);
        }
    }

    private final CompoundButton.OnCheckedChangeListener cocheChange = (button, checked) -> {
        if (rbEset.isChecked()) {
            EsetDAO esetDAO = new EsetDAO(this);
            esetDAO.open();

            ArrayList<ESET> esets = esetDAO.read();
            esetDAO.close();

            ESETSpinnerAdapter adapter = new ESETSpinnerAdapter(this, android.R.layout.simple_spinner_item, esets);
            spLicences.setAdapter(adapter); // Set the custom adapter to the spinner

            if (liaisonSelectionnee != null) {
                int index = esets.indexOf(liaisonSelectionnee.getEset());
                spLicences.setSelection(index);
            }
        } else {
            WindowsDAO windowsDAO = new WindowsDAO(this);
            windowsDAO.open();

            ArrayList<WINDOWS> windows = windowsDAO.read();
            windowsDAO.close();

            WindowsSpinnerAdapter adapter = new WindowsSpinnerAdapter(this, android.R.layout.simple_spinner_item, windows);
            spLicences.setAdapter(adapter); // Set the custom adapter to the spinner

            if (liaisonSelectionnee != null) {
                int index = windows.indexOf(liaisonSelectionnee.getWindows());
                spLicences.setSelection(index);
            }
        }
    };

    private final View.OnClickListener btValiderAffectationClic = view -> {
        LiaisonDAO liaisonDAO = new LiaisonDAO(this);
        liaisonDAO.open();

        ESET eset = null;
        WINDOWS windows = null;

        if (rbEset.isChecked()) {
            EsetDAO esetDAO = new EsetDAO(this);
            esetDAO.open();

            int idEset = ((ESET) spLicences.getSelectedItem()).getIdEset();
            eset = esetDAO.read(idEset);
            esetDAO.close();
        } else {
            WindowsDAO windowsDAO = new WindowsDAO(this);
            windowsDAO.open();

            int idWindows = ((WINDOWS) spLicences.getSelectedItem()).getIdWindows();
            windows = windowsDAO.read(idWindows);
            windowsDAO.close();
        }

        ClientDAO clientDAO = new ClientDAO(this);
        clientDAO.open();

        int idClient = ((CLIENT) spClients.getSelectedItem()).getIdClient();
        CLIENT client = clientDAO.read(idClient);
        clientDAO.close();

        if (liaisonSelectionnee != null) {
            LIAISON liaison = new LIAISON(liaisonSelectionnee.getIdLiaison(), windows, eset, client);
            liaisonDAO.update(liaison);
        } else {
            LIAISON liaison = new LIAISON(0, windows, eset, client);
            liaisonDAO.create(liaison);
        }

        liaisonDAO.close();

        this.finish();
    };
}