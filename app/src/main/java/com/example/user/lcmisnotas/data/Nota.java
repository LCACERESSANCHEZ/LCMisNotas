package com.example.user.lcmisnotas.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "Nota")
public class Nota {


    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id_Nota")
    private int idNota;

    @ColumnInfo (name = "nombre_Nota")
    private String nombreNota;

    @ColumnInfo (name = "activo_Nota")
    private int activoNota;


    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getNombreNota() {
        return nombreNota;
    }

    public void setNombreNota(String nombreNota) {
        this.nombreNota = nombreNota;
    }

    public int getActivoNota() {
        return activoNota;
    }

    public void setActivoNota(int activoNota) {
        this.activoNota = activoNota;
    }

}
