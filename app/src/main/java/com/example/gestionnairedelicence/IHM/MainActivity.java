package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairedelicence.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLicences = findViewById(R.id.btnLicences);
        Button btnNouvelleLicence = findViewById(R.id.btnNouvelleLicence);
        Button btnClients = findViewById(R.id.btnClients);
        Button btnNouveauClient = findViewById(R.id.btnNouveauClient);
        Button btnAffectations = findViewById(R.id.btnAffectations);
        Button btnNouvelleAffectation = findViewById(R.id.btnNouvelleAffectation);
        Button btnQuitter = findViewById(R.id.btnQuitter);

        btnLicences.setOnClickListener(btnLicencesClic);
        btnNouvelleLicence.setOnClickListener(btnNouvelleLicenceClic);
        btnClients.setOnClickListener(btnClientsClic);
        btnNouveauClient.setOnClickListener(btnNouveauClientClic);
        btnAffectations.setOnClickListener(btnAffectationsClic);
        btnNouvelleAffectation.setOnClickListener(btnNouvelleAffectationClic);
        btnQuitter.setOnClickListener(btnQuitterClic);
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

    private final View.OnClickListener btnLicencesClic = view -> {
        Intent iConsult = new Intent(this, LicencesActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener btnNouvelleLicenceClic = view -> {
        Intent iConsult = new Intent(this, LicenceActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener btnClientsClic = view -> {
        Intent iConsult = new Intent(this, ClientsActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener btnNouveauClientClic = view -> {
        Intent iConsult = new Intent(this, ClientActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener btnAffectationsClic = view -> {
        Intent iConsult = new Intent(this, AffectationsActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener btnNouvelleAffectationClic = view -> {
        Intent iConsult = new Intent(this, AffectationActivity.class);
        startActivity(iConsult);
    };

    private final View.OnClickListener btnQuitterClic = view -> quitter();
}