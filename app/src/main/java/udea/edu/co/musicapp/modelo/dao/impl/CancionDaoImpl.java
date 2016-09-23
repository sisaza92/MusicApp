package udea.edu.co.musicapp.modelo.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import udea.edu.co.musicapp.utils.database.Contract;
import udea.edu.co.musicapp.modelo.dao.CancionDaoInterface;
import udea.edu.co.musicapp.utils.database.DbHelper;
import udea.edu.co.musicapp.modelo.dto.Cancion;


/**
 * Created by alejandro.isazad on 20/09/16.
 */
public class CancionDaoImpl implements CancionDaoInterface {

    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public void guardar(List<Cancion> canciones) {

        dbHelper = new DbHelper();//Instancia de DbHelper
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(Contract.CANCION,null,null);


        int size = canciones.size();
        Cancion cancion = null;


        for (int i=0;i<size;i++){
            cancion = canciones.get(i);

            values.put(Contract.Column.ID, cancion.getIdCancion());
            values.put(Contract.Column.TITULO, cancion.getTituloCancion());
            values.put(Contract.Column.ALBUM, cancion.getAlbum());
            values.put(Contract.Column.ARTISTA, cancion.getArtista());
            values.put(Contract.Column.RUTA, cancion.getRutaCancion());

            db.insertWithOnConflict(Contract.CANCION,null,values,SQLiteDatabase.CONFLICT_IGNORE);
        }
        db.close();
    }

    @Override
    public List<Cancion> getAllSongs() {

        dbHelper = new DbHelper();//Instancia de DbHelper
        db = dbHelper.getWritableDatabase();

        List<Cancion> canciones = new ArrayList<>();

        Cursor cursor = db.query(Contract.CANCION,null,null,null,null,null,null);
        int nroRegistros = cursor.getCount();

        if( nroRegistros > 0){

            while (cursor.moveToNext()){
                Cancion cancion = new Cancion();

                int indxIdCancion = cursor.getColumnIndex(Contract.Column.ID);
                int indxTituloCancion = cursor.getColumnIndex(Contract.Column.TITULO);
                int indxAlbum = cursor.getColumnIndex(Contract.Column.ALBUM);
                int indxArtista = cursor.getColumnIndex(Contract.Column.ARTISTA);

                cancion.setIdCancion(cursor.getInt(indxIdCancion));
                cancion.setTituloCancion(cursor.getString(indxTituloCancion));
                cancion.setArtista(cursor.getString(indxArtista));
                cancion.setAlbum(cursor.getString(indxAlbum));
                Log.d("CONSULTANDO", cancion.toString());
                canciones.add(cancion);
            }
        }

        return canciones;
    }
}
