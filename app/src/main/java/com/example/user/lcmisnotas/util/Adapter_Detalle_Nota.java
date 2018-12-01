package com.example.user.lcmisnotas.util;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lcmisnotas.R;
import com.example.user.lcmisnotas.data.Detalle_Nota;
import com.example.user.lcmisnotas.data.Nota;

import java.util.List;
public class Adapter_Detalle_Nota extends RecyclerView.Adapter<Adapter_Detalle_Nota.ViewHolder> {

    private static final String TAG = "MI_LOG";
    private List<Detalle_Nota> detalleNotaList;
    private List<Nota> notaList;

    public Adapter_Detalle_Nota(List<Detalle_Nota> detalleNotaList) {
        this.detalleNotaList = detalleNotaList;
        Log.i(TAG,"MI_LISTA"+this.detalleNotaList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Detalle_Nota detalle_nota = detalleNotaList.get(position);
       Nota nota = new Nota();
       holder.tv_1.setText("" + detalle_nota.getIdDetalleNota());
       holder.tv_2.setText("" + detalle_nota.getNombreDetalleNota());
       holder.tv_3.setText(": "+ nota.getNombreNota());//COMO PUEDO PONER PARA QUE SE PINTE EL NOMBRE TE LA TABLA NOTA
        Log.i(TAG, "detalle_nota.getIdDetalleNota " + detalle_nota.getIdDetalleNota());
        Log.i(TAG, "detalle_nota.getNombreDetalleNota() " + detalle_nota.getNombreDetalleNota());
        Log.i(TAG, "ota.getNombreNota( " + nota.getNombreNota());
    }

    private String mConsultarNota(int idNotaF) {
        String valor="";
        if(idNotaF==1){
            valor = "SI";
        }else if (idNotaF ==2){
            valor = "SI2";
        }
        return valor;
    }

    @Override
    public int getItemCount() {
        return detalleNotaList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1, tv_2, tv_3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 = itemView.findViewById(R.id.tv_2);
            tv_3 = itemView.findViewById(R.id.tv_3);
        }

    }
}
