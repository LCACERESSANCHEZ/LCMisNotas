package com.example.user.lcmisnotas.conexion;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.user.lcmisnotas.dao.Dao_Detalle_Nota;
import com.example.user.lcmisnotas.dao.Dao_Nota;
import com.example.user.lcmisnotas.data.Detalle_Nota;
import com.example.user.lcmisnotas.data.Nota;

@Database(entities = {Nota.class, Detalle_Nota.class}, version = 1)
public abstract class ConexionBD extends RoomDatabase {

    private static ConexionBD INSTANCE;

    public abstract Dao_Nota dao_Nota();

    public abstract Dao_Detalle_Nota dao_DetalleNota();

    public static ConexionBD getAppDataBase(Context context){
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ConexionBD.class,"NOTAS_BD").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
