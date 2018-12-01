package com.example.user.lcmisnotas.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.lcmisnotas.data.Detalle_Nota;

import java.util.List;

@Dao
public interface Dao_Detalle_Nota {

    @Insert
    public void addDetalleNota (Detalle_Nota detalle_nota);

    @Query("SELECT * FROM DetalleNota DN " +
            "INNER JOIN Nota N ON DN.id_NotaF = N.id_Nota " +
            "WHERE DN.id_NotaF = :id ")
    public List<Detalle_Nota> getDetalleNota(final int id);

}
