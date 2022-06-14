package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestionnairedelicence.DAO.ClientDAO;
import com.example.gestionnairedelicence.DAO.VilleDAO;
import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.METIER.VILLE;
import com.example.gestionnairedelicence.R;

public class ClientActivity extends AppCompatActivity {
    EditText etNom, etPrenom, etAdresse, etTelephone, etMail, etCodePostal, etVille;
    Button btnValider, btnSupprimer;
    CLIENT clientSelectionne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Intent intent = getIntent();
        int idClient = intent.getIntExtra("idClient", 0);

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etAdresse = findViewById(R.id.etAdresse);
        etVille = findViewById(R.id.etVille);
        etCodePostal = findViewById(R.id.etCodePostal);
        etTelephone = findViewById(R.id.etTel);
        etMail = findViewById(R.id.etMail);

        btnValider = findViewById(R.id.btnValider);
        btnSupprimer = findViewById(R.id.btnSupprimer);

        btnValider.setOnClickListener(btValiderClic);
        btnSupprimer.setOnClickListener(btnSupprimerClic);

        if (idClient != 0) {
            ClientDAO clientDAO = new ClientDAO(this);
            clientDAO.open();

            clientSelectionne = clientDAO.read(idClient);
            clientDAO.close();

            etNom.setText(clientSelectionne.getNom());
            etPrenom.setText(clientSelectionne.getPrenom());
            etAdresse.setText(clientSelectionne.getAdresse());
            etMail.setText(clientSelectionne.getMail());
            etTelephone.setText(clientSelectionne.getTel());
            etCodePostal.setText(clientSelectionne.getVille().getCp());
            etVille.setText(clientSelectionne.getVille().getLibelle());
        } else {
            btnSupprimer.setVisibility(View.INVISIBLE);
        }
    }

    private final View.OnClickListener btnSupprimerClic = view -> {
        ClientDAO clientDAO = new ClientDAO(this);
        clientDAO.open();

        clientDAO.delete(clientSelectionne);
        clientDAO.close();

        finish();
    };

    private final View.OnClickListener btValiderClic = view -> {
        VilleDAO villeDAO = new VilleDAO(this);
        villeDAO.open();

        VILLE ville = new VILLE();

        ville.setCp(etCodePostal.getText().toString());
        ville.setLibelle(etVille.getText().toString());

        int idVille = villeDAO.create(ville);
        ville = villeDAO.read(idVille);
        villeDAO.close();

        ClientDAO clientDAO = new ClientDAO(this);
        clientDAO.open();

        if (clientSelectionne != null) {
            CLIENT client = new CLIENT(
                    clientSelectionne.getIdClient(),
                    etNom.getText().toString(),
                    etPrenom.getText().toString(),
                    etAdresse.getText().toString(),
                    etTelephone.getText().toString(),
                    etMail.getText().toString(),
                    ville
            );
            clientDAO.update(client);
        } else {
            CLIENT client = new CLIENT(
                    0,
                    etNom.getText().toString(),
                    etPrenom.getText().toString(),
                    etAdresse.getText().toString(),
                    etTelephone.getText().toString(),
                    etMail.getText().toString(),
                    ville
            );
            clientDAO.create(client);
        }

        clientDAO.close();

        finish();
    };
}