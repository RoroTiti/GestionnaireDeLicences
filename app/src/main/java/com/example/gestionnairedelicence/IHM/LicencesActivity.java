package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionnairedelicence.DAO.LiaisonDAO;
import com.example.gestionnairedelicence.IHM.Adapters.Licence;
import com.example.gestionnairedelicence.IHM.Adapters.LicencesListViewAdapter;
import com.example.gestionnairedelicence.METIER.LIAISON;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class LicencesActivity extends AppCompatActivity {
    ListView lvLicences;
    ArrayList<Licence> licences;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> init()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licences);

        lvLicences = findViewById(R.id.lvClients);

        init();

        lvLicences.setOnItemClickListener(elementSectionne);
    }

    private void init() {
        LiaisonDAO liaisonDAO = new LiaisonDAO(this);
        liaisonDAO.open();

        ArrayList<LIAISON> liaisons = liaisonDAO.read();
        liaisonDAO.close();

        licences = new ArrayList<>();

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

        LicencesListViewAdapter licencesAdapter = new LicencesListViewAdapter(this, android.R.layout.simple_spinner_item, licences);
        lvLicences.setAdapter(licencesAdapter);
    }

    private final AdapterView.OnItemClickListener elementSectionne = (adapterView, view, i, l) -> {
        int idLiaison = licences.get(i).getIdLiaison();

        Intent iConsult = new Intent(this, AffectationActivity.class);
        iConsult.putExtra("idLiaison", idLiaison);
        someActivityResultLauncher.launch(iConsult);
    };
}