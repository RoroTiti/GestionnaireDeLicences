package com.example.gestionnairedelicence.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionnairedelicence.R;

public class MainActivity extends AppCompatActivity {
    private Button btNewLicence;
    private Button btNewClient;
    private Button btConsult;
    private Button btQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btConsult.setOnClickListener(bConsultLicences);
        btNewLicence.setOnClickListener(bNewLicence);
        btNewClient.setOnClickListener(bNewClient);
        btQuitter.setOnClickListener(quitterListener);
    }

    private void init() {
        btConsult = (Button) findViewById(R.id.btConsult);
        btNewClient = (Button) findViewById(R.id.BtNewClient);
        btNewLicence = (Button) findViewById(R.id.BtNewLicence);
        btQuitter = (Button) findViewById(R.id.btQuitter3);
    }

    public Button getBtNewLicence() {
        return btNewLicence;
    }

    public void setBtNewLicence(Button btNewLicence) {
        this.btNewLicence = btNewLicence;
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

    private View.OnClickListener quitterListener = view -> quitter();

    private View.OnClickListener bConsultLicences = view -> {
        Intent iConsult = new Intent(this, ConsultActivity.class);
        startActivity(iConsult);
    };

    private View.OnClickListener bNewClient = view -> {
        Intent iConsult = new Intent(this, ClientActivity.class);
        startActivity(iConsult);
    };

    private View.OnClickListener bNewLicence = view -> {
        Intent iConsult = new Intent(this, LicenceActivity.class);
        startActivity(iConsult);
    };
}