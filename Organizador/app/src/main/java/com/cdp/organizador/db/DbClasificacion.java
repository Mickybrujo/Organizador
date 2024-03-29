package com.cdp.organizador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.organizador.entidades.Clasificacion;

import java.util.ArrayList;

public class DbClasificacion extends DbHelper {

    Context context;

    public DbClasificacion(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarClasificacion(String nombre) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);

            id = db.insert(TABLE_Clasificacion, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Clasificacion> mostrarClasificaciones() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clasificacion> listaClasificacions = new ArrayList<>();
        Clasificacion Clasificacion;
        Cursor cursorClasificacions;

        cursorClasificacions = db.rawQuery("SELECT * FROM " + TABLE_Clasificacion, null);

        if (cursorClasificacions.moveToFirst()) {
            do {
                Clasificacion = new Clasificacion();
                Clasificacion.setId(cursorClasificacions.getInt(0));
                Clasificacion.setNombre(cursorClasificacions.getString(1));

                listaClasificacions.add(Clasificacion);
            } while (cursorClasificacions.moveToNext());
        }

        cursorClasificacions.close();

        return listaClasificacions;
    }

    public Clasificacion verClasificacion(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Clasificacion Clasificacion = null;
        Cursor cursorClasificacions;

        cursorClasificacions = db.rawQuery("SELECT * FROM " + TABLE_Clasificacion + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorClasificacions.moveToFirst()) {
            Clasificacion = new Clasificacion();
            Clasificacion.setId(cursorClasificacions.getInt(0));
            Clasificacion.setNombre(cursorClasificacions.getString(1));
        }

        cursorClasificacions.close();

        return Clasificacion;
    }

    public boolean editarClasificacion(int id, String nombre) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_Clasificacion + " SET nombre = '" + nombre +"' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarClasificacion(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_Clasificacion + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
