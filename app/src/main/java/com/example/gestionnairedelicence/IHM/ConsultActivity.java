package com.example.gestionnairedelicence.IHM;

import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gestionnairedelicence.DAO.LiaisonDAO;
import com.example.gestionnairedelicence.METIER.LIAISON;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class ConsultActivity extends AppCompatActivity {
    ListView lvLicences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        lvLicences = findViewById(R.id.lvLicences);

        LiaisonDAO liaisonDAO = new LiaisonDAO(this);
        liaisonDAO.open();

        ArrayList<LIAISON> liaisons = liaisonDAO.read();
        liaisonDAO.close();

        ArrayList<Licence> licences = new ArrayList<>();

        for (LIAISON liaison : liaisons) {
            String logiciel = liaison.getWindows() != null
                    ? "Windows"
                    : "ESET";

            String cleActivation = liaison.getWindows() != null
                    ? liaison.getWindows().getActivationKey()
                    : liaison.getEset().getActivationKey();

            String client = liaison.getClient().toString();

            String dateAchat = liaison.getWindows() != null
                    ? liaison.getWindows().getDateAchat()
                    : liaison.getEset().getDateAchat();

            licences.add(new Licence(liaison.getIdLiaison(), logiciel, client, cleActivation, dateAchat));
        }

        LicencesAdapter licencesAdapter = new LicencesAdapter(this, android.R.layout.simple_spinner_item, licences);

        lvLicences.setAdapter(licencesAdapter);
    }
}