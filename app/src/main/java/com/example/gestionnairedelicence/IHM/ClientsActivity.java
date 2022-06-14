package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionnairedelicence.DAO.ClientDAO;
import com.example.gestionnairedelicence.IHM.Adapters.ClientsListViewAdapter;
import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.R;

import java.util.ArrayList;

public class ClientsActivity extends AppCompatActivity {
    ListView lvClients;
    ArrayList<CLIENT> clients;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> init()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        lvClients = findViewById(R.id.lvClients);

        init();

        lvClients.setOnItemClickListener(elementSectionne);
    }

    private void init() {
        ClientDAO clientDAO = new ClientDAO(this);
        clientDAO.open();

        clients = clientDAO.read();
        clientDAO.close();

        ClientsListViewAdapter clientsAdapter = new ClientsListViewAdapter(this, android.R.layout.simple_spinner_item, clients);
        lvClients.setAdapter(clientsAdapter);
    }

    private final AdapterView.OnItemClickListener elementSectionne = (adapterView, view, i, l) -> {
        int idClient = clients.get(i).getIdClient();

        Intent iConsult = new Intent(this, ClientActivity.class);
        iConsult.putExtra("idClient", idClient);
        someActivityResultLauncher.launch(iConsult);
    };
}