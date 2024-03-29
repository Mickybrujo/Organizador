package com.cdp.organizador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "organizador.db";
    public static final String TABLE_Tareas = "t_tareas";
    public static final String TABLE_Clasificacion = "t_clasificacion";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_Clasificacion + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL" +
                ")"
        );
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE " + TABLE_Tareas + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL,"+
                "fecha TEXT NOT NULL,"+
                "hora TEXT NOT NULL,"+
                "id_categoria INTEGER,"+
                "FOREIGN KEY (id_categoria) REFERENCES "+TABLE_Clasificacion+"(id) " +
                ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_Clasificacion);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_Tareas);
        onCreate(sqLiteDatabase);
    }
}
