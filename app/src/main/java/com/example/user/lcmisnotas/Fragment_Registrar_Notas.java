package com.example.user.lcmisnotas;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lcmisnotas.conexion.ConexionBD;
import com.example.user.lcmisnotas.dao.Dao_Detalle_Nota;
import com.example.user.lcmisnotas.dao.Dao_Nota;
import com.example.user.lcmisnotas.data.Detalle_Nota;
import com.example.user.lcmisnotas.data.Nota;
import com.example.user.lcmisnotas.util.Adapter_Detalle_Nota;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Registrar_Notas extends Fragment {
    private Dao_Nota dao_nota;
    private Dao_Detalle_Nota dao_detalle_nota;

    private Button btnNuevaNota;
    private TextInputEditText tvNuevaNota, tvNuevaNotaDetalle;

    public long codigoSpinner ;

    View view;

    private RecyclerView recyclerView_DetalleNota;
    private List<Detalle_Nota> detalle_notaList;
    private Adapter_Detalle_Nota adapter_detalle_nota;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dao_nota = ConexionBD.getAppDataBase(context).dao_Nota();
        dao_detalle_nota = ConexionBD.getAppDataBase(context). dao_DetalleNota();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment__registrar__notas, container, false);
        setHasOptionsMenu(true);
        Spinner idSpinner = (Spinner)view.findViewById(R.id.idSpinner);
        tvNuevaNotaDetalle = (TextInputEditText)view.findViewById(R.id.tvNuevaNotaDetalle);
        ImageButton btnShowDialog = (ImageButton)view.findViewById(R.id.btnShowDialog);
        ImageButton btnAniadirDetalleNota = (ImageButton)view.findViewById(R.id.btnAniadirDetalleNota);

        /*int idintNota= (int) codigoSpinner + 1;
        detalle_notaList = dao_detalle_nota.getDetalleNota(idintNota);
        adapter_detalle_nota = new Adapter_Detalle_Nota(detalle_notaList);
        recyclerView_DetalleNota = view.findViewById(R.id.recyclerView_DetalleNota);
        recyclerView_DetalleNota.setLayoutManager((new LinearLayoutManager(getContext())));
        recyclerView_DetalleNota.setHasFixedSize(true);
        recyclerView_DetalleNota.setAdapter(adapter_detalle_nota);*/
        mCreateSpinner();
        mCreateRecyclerView();

        //SPINNER
        idSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codigoSpinner = id;
                mCreateRecyclerView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //ABRE EL ALERT_DIALOG
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View sView = getLayoutInflater().inflate(R.layout.show_dialog,null);
                btnNuevaNota = (Button)sView.findViewById(R.id.btnNuevaNota);
                Button btnCancelarNuevaNota = (Button)sView.findViewById(R.id.btnCancelarNuevaNota);
                tvNuevaNota = (TextInputEditText)sView.findViewById(R.id.tvNuevaNota);

                mBuilder.setView(sView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btnNuevaNota.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nuevaNota = tvNuevaNota.getText().toString();
                        final int activo = 1;
                        //GUARDAR NOTA
                        if(!nuevaNota.isEmpty()){
                            Nota nota = new Nota();
                            nota.setNombreNota(nuevaNota);
                            nota.setActivoNota(activo);
                            dao_nota.addNota(nota);
                            Toast.makeText(getActivity(),"NOTA GUARDADA",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            mCreateSpinner();
                        }else {
                            Toast.makeText(getActivity(),"INGRESE NOTA",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelarNuevaNota.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });

        //GUARDAR DETALLE NOTA
        btnAniadirDetalleNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DetalleNota idAutoincrementable
                String nuevaNotaDetalle = tvNuevaNotaDetalle.getText().toString();
                final int activo = 1;
                int idintNota= (int) codigoSpinner + 1;

                Detalle_Nota detalle_nota = new Detalle_Nota();
                detalle_nota.setNombreDetalleNota(nuevaNotaDetalle);
                detalle_nota.setActivoDetalleNota(activo);
                detalle_nota.setIdNotaF(idintNota);

                dao_detalle_nota.addDetalleNota(detalle_nota);
                Toast.makeText(getActivity(), "DETALLE DE NOTA GUARDADA", Toast.LENGTH_SHORT).show();
                mCreateRecyclerView();
            }
        });

        return view;
    }


    private Spinner mCreateSpinner(){
        Spinner spinner = view.findViewById(R.id.idSpinner);
        List<Nota> notas = dao_nota.getNota();
        List<String>notaStrings = new ArrayList<>();
        for(int i = 0; i < notas.size(); i++){
            notaStrings.add(notas.get(i).getNombreNota());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                notaStrings);
        adapter.setDropDownViewResource(R.layout.show_spinner);
        spinner.setAdapter(adapter);
        //spinner.setSelection(obtenerPosicionItem(spinner,"abcdario" ));
        return spinner;
    }

    private RecyclerView mCreateRecyclerView(){
        int idintNota= (int) codigoSpinner + 1;
        detalle_notaList = dao_detalle_nota.getDetalleNota(idintNota);
        adapter_detalle_nota = new Adapter_Detalle_Nota(detalle_notaList);
        recyclerView_DetalleNota = view.findViewById(R.id.recyclerView_DetalleNota);
        recyclerView_DetalleNota.setLayoutManager((new LinearLayoutManager(getContext())));
        recyclerView_DetalleNota.setHasFixedSize(true);
        recyclerView_DetalleNota.setAdapter(adapter_detalle_nota);
        return recyclerView_DetalleNota;
    }

    /*public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }*/


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnGuardar) {
            Intent i = new Intent(getActivity(), ActivityPrincipal.class);
            startActivity(i);
            Toast.makeText(getActivity(),"Nota Guardada" + codigoSpinner ,Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }



}
