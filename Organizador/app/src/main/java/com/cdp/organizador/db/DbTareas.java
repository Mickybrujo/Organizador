package com.cdp.organizador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.organizador.entidades.Tareas;

import java.util.ArrayList;

public class DbTareas extends DbHelper {

    Context context;

    public DbTareas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarTarea(String titulo, String descripcion, String fecha, String hora) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("titulo", titulo);
            values.put("descripcion", descripcion);
            values.put("fecha", fecha);
            values.put("hora", hora);
            values.put("hora", hora);

            id = db.insert(TABLE_Tareas, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Tareas> mostrarTareas() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Tareas> listaTareas = new ArrayList<>();
        Tareas tarea;
        Cursor cursorTareas;

        cursorTareas = db.rawQuery("SELECT * FROM " + TABLE_Tareas, null);

        if (cursorTareas.moveToFirst()) {
            do {
                tarea = new Tareas();
                tarea.setId(cursorTareas.getInt(0));
                tarea.setTitulo(cursorTareas.getString(1));
                tarea.setDescripcion(cursorTareas.getString(2));

                listaTareas.add(tarea);
            } while (cursorTareas.moveToNext());
        }

        cursorTareas.close();

        return listaTareas;
    }

    public Tareas verTarea(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Tareas tarea = null;
        Cursor cursorTareas;

        cursorTareas = db.rawQuery("SELECT * FROM " + TABLE_Tareas + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorTareas.moveToFirst()) {
            tarea = new Tareas();
            tarea.setId(cursorTareas.getInt(0));
            tarea.setTitulo(cursorTareas.getString(1));
            tarea.setDescripcion(cursorTareas.getString(2));
            tarea.setFecha(cursorTareas.getString(3));
            tarea.setHora(cursorTareas.getString(4));
        }

        cursorTareas.close();

        return tarea;
    }

    public boolean editarTarea(int id, String titulo, String descripcion, String fecha, String hora) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_Tareas + " SET titulo = '" + titulo + "', descripcion = '" + descripcion + "', fecha = '"+ fecha +"', hora = '"+ hora +"' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarTarea(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_Tareas + " WHERE id = '" + id + "'");
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
