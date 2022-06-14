package com.example.gestionnairedelicence.IHM;

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
    private Button btCreerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        btCreerClient = findViewById(R.id.btCreerClient);

        btCreerClient.setOnClickListener(bCreerClient);
    }

    private View.OnClickListener bCreerClient = view -> {
        VilleDAO villeDAO = new VilleDAO(this);
        villeDAO.open();

        VILLE ville = new VILLE();

        EditText etCodePostal = findViewById(R.id.etCodePostal);
        EditText etVille = findViewById(R.id.etVille);

        ville.setCp(etCodePostal.getText().toString());
        ville.setLibelle(etVille.getText().toString());

        int idVille = villeDAO.create(ville);
        ville = villeDAO.read(idVille);

        villeDAO.close();

        ClientDAO clientDAO = new ClientDAO(this);
        clientDAO.open();

        CLIENT client = new CLIENT();

        EditText etNom = findViewById(R.id.etNom);
        EditText etPrenom = findViewById(R.id.etPrenom);
        EditText etAdresse = findViewById(R.id.etAdresse);
        EditText etTelephone = findViewById(R.id.etTel);
        EditText etMail = findViewById(R.id.etMail);

        client.setNom(etNom.getText().toString());
        client.setPrenom(etPrenom.getText().toString());
        client.setAdresse(etAdresse.getText().toString());
        client.setTel(etTelephone.getText().toString());
        client.setMail(etMail.getText().toString());
        client.setVille(ville);

        clientDAO.create(client);

        clientDAO.close();

        this.finish();
    };
}