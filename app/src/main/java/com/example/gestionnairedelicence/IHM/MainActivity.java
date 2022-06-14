package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairedelicence.R;

public class MainActivity extends AppCompatActivity {
    private Button btnLicences, btNouvelleLicence, btNouveauClient, btClients, btConsult, btAffectation, btQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConsult = findViewById(R.id.btnAffectations);
        btClients = findViewById(R.id.btnClients);
        btNouveauClient = findViewById(R.id.btnNouveauClient);
        btnLicences = findViewById(R.id.btnLicences);
        btNouvelleLicence = findViewById(R.id.btnNouvelleLicence);
        btAffectation = findViewById(R.id.btnNouvelleAffectation);
        btQuitter = findViewById(R.id.btnQuitter);

        btConsult.setOnClickListener(bConsultLicences);
        btnLicences.setOnClickListener(bLicences);
        btNouvelleLicence.setOnClickListener(bNewLicence);
        btClients.setOnClickListener(bClients);
        btNouveauClient.setOnClickListener(bNewClient);
        btAffectation.setOnClickListener(bAffectation);
        btQuitter.setOnClickListener(quitterListener);
    }

    public Button getBtNouvelleLicence() {
        return btNouvelleLicence;
    }

    public void setBtNouvelleLicence(Button btNouvelleLicence) {
        this.btNouvelleLicence = btNouvelleLicence;
    }

    public Button getBtConsult() {
        return btConsult;
    }

    public void setBtConsult(Button btConsult) {
        this.btConsult = btConsult;
    }

    public Button getBtQuitter() {
        return btQuitter;
    }

    public void setBtQuitter(Button btQuitter) {
        this.btQuitter = btQuitter;
    }

    private void quitter() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //set title
        alertDialogBuilder.setTitle("Voulez-vous vraiment quitter l'application ? ");

        //set dialog message
        alertDialogBuilder.setPositiveButton("Oui", (dialog, id) -> finish());
        alertDialogBuilder.setNegativeButton("Non ", (dialog, id) -> dialog.cancel());

        //Creation de la POPUP
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private final View.OnClickListener quitterListener = view -> quitter();

    private final View.OnClickListener bConsultLicences = view -> {
        Intent iConsult = new Intent(this, AffectationsActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener bNewClient = view -> {
        Intent iConsult = new Intent(this, ClientActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener bClients = view -> {
        Intent iConsult = new Intent(this, ClientsActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener bLicences = view -> {
        Intent iConsult = new Intent(this, LicencesActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener bNewLicence = view -> {
        Intent iConsult = new Intent(this, LicenceActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener bAffectation = view -> {
        Intent iConsult = new Intent(this, AffectationActivity.class);
        startActivity(iConsult);
    };
}