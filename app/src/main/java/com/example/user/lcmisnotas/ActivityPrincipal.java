package com.example.user.lcmisnotas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.lcmisnotas.conexion.ConexionBD;
import com.example.user.lcmisnotas.dao.Dao_Nota;
import com.example.user.lcmisnotas.data.Nota;

import java.util.ArrayList;
import java.util.List;

public class ActivityPrincipal extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mCreateSpinner();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this, Activity_Registrar_Notas.class);
                startActivity(intent);
            }
        });
    }


    private Spinner mCreateSpinner(){
        Spinner spinner = findViewById(R.id.idSpinnerP);
        Dao_Nota dao_nota = ConexionBD.getAppDataBase(getApplicationContext()).dao_Nota();
        List<Nota> notas = dao_nota.getNota();
        List<String>notaStrings = new ArrayList<>();
        for(int i = 0; i < notas.size(); i++){
            notaStrings.add(notas.get(i).getNombreNota());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                notaStrings);
        adapter.setDropDownViewResource(R.layout.show_spinner);
        spinner.setAdapter(adapter);
        //spinner.setSelection(obtenerPosicionItem(spinner,"abcdario" ));
        return spinner;
    }





















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnGuardar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
