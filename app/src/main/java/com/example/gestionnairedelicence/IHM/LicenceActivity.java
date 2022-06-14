package com.example.gestionnairedelicence.IHM;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gestionnairedelicence.DAO.EsetDAO;
import com.example.gestionnairedelicence.DAO.WindowsDAO;
import com.example.gestionnairedelicence.METIER.ESET;
import com.example.gestionnairedelicence.METIER.WINDOWS;
import com.example.gestionnairedelicence.R;

public class LicenceActivity extends AppCompatActivity {
    RadioButton rbEset;
    EditText etESETDateFinValidite;
    Button btLicenceValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);

        rbEset = findViewById(R.id.rbEset);
        etESETDateFinValidite = findViewById(R.id.etESETDateFinValidite);
        btLicenceValider = findViewById(R.id.btLicenceValider);

        rbEset.setOnCheckedChangeListener(rbEsetCoche);
        btLicenceValider.setOnClickListener(btLicenceValiderClic);

        etESETDateFinValidite.setVisibility(View.INVISIBLE);
    }

    private final CompoundButton.OnCheckedChangeListener rbEsetCoche = (button, checked) -> {
        if (checked) {
            etESETDateFinValidite.setVisibility(View.VISIBLE);
        } else {
            etESETDateFinValidite.setVisibility(View.INVISIBLE);
        }
    };

    private final View.OnClickListener btLicenceValiderClic = view -> {
        EditText etActivationKey = findViewById(R.id.etActivationKey);
        EditText etDateAchat = findViewById(R.id.etDateAchat);
        EditText etESETDateFinValidite = findViewById(R.id.etESETDateFinValidite);

        if (rbEset.isChecked()) {
            EsetDAO esetDAO = new EsetDAO(this);
            esetDAO.open();

            ESET eset = new ESET();

            eset.setActivationKey(etActivationKey.getText().toString());
            eset.setDateAchat(etDateAchat.getText().toString());
            eset.setDateFinValidite(etESETDateFinValidite.getText().toString());

            esetDAO.create(eset);
        } else {
            WindowsDAO windowsDAO = new WindowsDAO(this);
            windowsDAO.open();

            WINDOWS windows = new WINDOWS();

            windows.setActivationKey(etActivationKey.getText().toString());
            windows.setDateAchat(etDateAchat.getText().toString());

            windowsDAO.create(windows);
        }

        this.finish();
    };
}