package com.example.user.lcmisnotas.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.lcmisnotas.data.Nota;

import java.util.List;

@Dao
public interface Dao_Nota {

    @Insert
    public void addNota(Nota nota);

    @Query("SELECT * FROM NOTA")
    public List<Nota> getNota();


}
