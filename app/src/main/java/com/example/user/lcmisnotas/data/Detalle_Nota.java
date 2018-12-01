package com.example.user.lcmisnotas.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "DetalleNota",
        foreignKeys = @ForeignKey(entity = Nota.class,
                parentColumns = "id_Nota",
                childColumns = "id_NotaF",
                onDelete = CASCADE))
public class Detalle_Nota {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_Detalle_Nota")
    private int idDetalleNota;

    @ColumnInfo(name = "nombre_Detalle_Nota")
    private String nombreDetalleNota ;

    @ColumnInfo(name = "activo_Detalle_Nota")
    private int activoDetalleNota;

    @ColumnInfo(name = "id_NotaF")
    private int idNotaF;

    public int getIdDetalleNota() {
        return idDetalleNota;
    }

    public void setIdDetalleNota(int idDetalleNota) {
        this.idDetalleNota = idDetalleNota;
    }

    public String getNombreDetalleNota() {
        return nombreDetalleNota;
    }

    public void setNombreDetalleNota(String nombreDetalleNota) {
        this.nombreDetalleNota = nombreDetalleNota;
    }

    public int getActivoDetalleNota() {
        return activoDetalleNota;
    }

    public void setActivoDetalleNota(int activoDetalleNota) {
        this.activoDetalleNota = activoDetalleNota;
    }

    public int getIdNotaF() {
        return idNotaF;
    }

    public void setIdNotaF(int idNotaF) {
        this.idNotaF = idNotaF;
    }




}
