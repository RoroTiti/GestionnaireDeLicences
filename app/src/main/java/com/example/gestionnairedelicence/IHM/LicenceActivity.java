package com.example.gestionnairedelicence.IHM;

import android.content.Intent;
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
    RadioButton rbEset, rbWindows;
    EditText etActivationKey, etDateAchat, etESETDateFinValidite;
    Button btnValider, btnSupprimer;
    WINDOWS windowsSelectionne;
    ESET esetSelectionne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);

        Intent intent = getIntent();
        int idWindows = intent.getIntExtra("idWindows", 0);
        int idEset = intent.getIntExtra("idEset", 0);

        rbEset = findViewById(R.id.rbEset);
        rbWindows = findViewById(R.id.rbWindows);

        etActivationKey = findViewById(R.id.etActivationKey);
        etDateAchat = findViewById(R.id.etDateAchat);
        etESETDateFinValidite = findViewById(R.id.etESETDateFinValidite);

        btnValider = findViewById(R.id.btnValider);
        btnSupprimer = findViewById(R.id.btnSupprimer);

        rbEset.setOnCheckedChangeListener(rbEsetCoche);
        rbWindows.setOnCheckedChangeListener(rbWindowsCoche);

        btnValider.setOnClickListener(btLicenceValiderClic);
        btnSupprimer.setOnClickListener(btnSupprimerClic);

        if (idWindows != 0 || idEset != 0) {
            rbWindows.setEnabled(false);
            rbEset.setEnabled(false);
        }

        if (idWindows != 0) {
            WindowsDAO windowsDAO = new WindowsDAO(this);
            windowsDAO.open();

            windowsSelectionne = windowsDAO.read(idWindows);
            windowsDAO.close();

            rbWindows.setChecked(true);

            etActivationKey.setText(windowsSelectionne.getActivationKey());
            etDateAchat.setText(windowsSelectionne.getDateAchat());

            etESETDateFinValidite.setVisibility(View.INVISIBLE);
        } else if (idEset != 0) {
            EsetDAO esetDAO = new EsetDAO(this);
            esetDAO.open();

            esetSelectionne = esetDAO.read(idEset);
            esetDAO.close();

            rbEset.setChecked(true);

            etActivationKey.setText(esetSelectionne.getActivationKey());
            etDateAchat.setText(esetSelectionne.getDateAchat());

            etESETDateFinValidite.setText(esetSelectionne.getDateDeFInDeValidite());
        } else {
            rbWindows.setChecked(true);
            etESETDateFinValidite.setVisibility(View.INVISIBLE);
            btnSupprimer.setVisibility(View.INVISIBLE);
        }
    }

    private final CompoundButton.OnCheckedChangeListener rbWindowsCoche = (button, checked) -> {
        etESETDateFinValidite.setVisibility(checked ? View.INVISIBLE : View.VISIBLE);
    };

    private final CompoundButton.OnCheckedChangeListener rbEsetCoche = (button, checked) -> {
        etESETDateFinValidite.setVisibility(checked ? View.VISIBLE : View.INVISIBLE);
    };

    private final View.OnClickListener btnSupprimerClic = view -> {
        if (windowsSelectionne != null) {
            WindowsDAO windowsDAO = new WindowsDAO(this);
            windowsDAO.open();

            windowsDAO.delete(windowsSelectionne);
            windowsDAO.close();
        } else {
            EsetDAO esetDAO = new EsetDAO(this);
            esetDAO.open();

            esetDAO.delete(esetSelectionne);
            esetDAO.close();
        }

        finish();
    };

    private final View.OnClickListener btLicenceValiderClic = view -> {
        if (rbEset.isChecked()) {
            EsetDAO esetDAO = new EsetDAO(this);
            esetDAO.open();

            if (esetSelectionne != null) {
                esetDAO.update(new ESET(
                                esetSelectionne.getIdEset(),
                                etDateAchat.getText().toString(),
                                etActivationKey.getText().toString(),
                                etESETDateFinValidite.getText().toString()
                        )
                );
            } else {
                esetDAO.create(new ESET(
                                0,
                                etDateAchat.getText().toString(),
                                etActivationKey.getText().toString(),
                                etESETDateFinValidite.getText().toString()
                        )
                );
            }
        } else {
            WindowsDAO windowsDAO = new WindowsDAO(this);
            windowsDAO.open();

            if (windowsSelectionne != null) {
                windowsDAO.update(new WINDOWS(
                                windowsSelectionne.getIdWindows(),
                                etDateAchat.getText().toString(),
                                etActivationKey.getText().toString()
                        )
                );
            } else {
                windowsDAO.create(new WINDOWS(
                                0,
                                etDateAchat.getText().toString(),
                                etActivationKey.getText().toString()
                        )
                );
            }
        }

        finish();
    };
}