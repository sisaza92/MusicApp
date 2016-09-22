package udea.edu.co.musicapp.modelo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import udea.edu.co.musicapp.modelo.Contract;
import udea.edu.co.musicapp.utils.ContextProvider;


/**
 * Created by alejandro.isazad on 20/09/16.
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String TAG=DbHelper.class.getSimpleName();

    public DbHelper(){
        super(ContextProvider.getContext(),Contract.DB_NAME,null, Contract.DB_VERSION);

        //
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql=String
                .format("create table %s(%s int,%s text, %s text, %s text, %s text)",
                        Contract.CANCION,
                        Contract.Column.ID,
                        Contract.Column.TITULO,
                        Contract.Column.ALBUM,
                        Contract.Column.ARTISTA,
                        Contract.Column.RUTA
                        );
        //Sentencia para crear tabla
        Log.d(TAG, "onCreate with SQL: " + sql);
        db.execSQL(sql);//Ejecución de la sentencia


    }

    //Se llama cada que el schema cambie(nueva versión)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("drop table if exists "+ Contract.CANCION);//Borrar datos
        onCreate(db);//Crear Tabla de nuevo
    }

}
