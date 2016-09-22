package udea.edu.co.musicapp.modelo.dao.impl;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import udea.edu.co.musicapp.modelo.Contract;
import udea.edu.co.musicapp.modelo.dao.CancionDao;
import udea.edu.co.musicapp.modelo.dao.DbHelper;
import udea.edu.co.musicapp.modelo.dto.Cancion;
import udea.edu.co.musicapp.utils.ContextProvider;


/**
 * Created by alejandro.isazad on 20/09/16.
 */
public class CancionDaoImpl implements CancionDao {
    @Override
    public void guardar(List<Cancion> canciones) {
        int size = canciones.size();
        Cancion cancion = null;

        DbHelper dbHelper= new DbHelper(ContextProvider.getContext());//Instancia de DbHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i=0;i<size;i++){
            cancion = canciones.get(i);
            values.put(Contract.Column.ID,cancion.getIdCancion());
            values.put(Contract.Column.TITULO,cancion.getTituloCancion());
            values.put(Contract.Column.ALBUM,cancion.getAlbum());
            values.put(Contract.Column.ARTISTA,cancion.getArtista());
            values.put(Contract.Column.RUTA,cancion.getRutaCancion());
        }

        db.insert(Contract.CANCION,null,values);
    }
}
