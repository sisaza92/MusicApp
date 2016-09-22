package udea.edu.co.musicapp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import udea.edu.co.musicapp.modelo.dao.CancionDao;
import udea.edu.co.musicapp.modelo.dao.impl.CancionDaoImpl;
import udea.edu.co.musicapp.modelo.dto.Cancion;

/**
 * Created by CristianCamilo on 20/09/2016.
 */
public class CancionServiceImpl extends IntentService {

    private Handler mHandler;
    private Boolean show;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("REGISTRO -->"," Clase: CancionServiceImpl Metodo: onCreate");
        mHandler = new Handler();
    }

    public CancionServiceImpl() {

        super("CancionServiceImpl");
        Log.d("REGISTRO -->"," Clase: CancionServiceImpl Metodo: constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("REGISTRO -->"," Clase: CancionServiceImpl Metodo: onHandleIntent");
        if (intent != null) {
            show = intent.getBooleanExtra("show",false);
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if ((ni != null) && (ni.isConnected())) {

                getAllSongs();
                //getSongById();

            } else {
                // If there is no connection, let the user know the sync didn't happen
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(show) {
                            Toast.makeText(CancionServiceImpl.this, "No se sincronizo", Toast.LENGTH_LONG).show();
                        }else{
                            //sendBroadcast(new Intent("ccmovil.gr7.rapidturns.NEW_DATESv2"));
                        }
                    }
                });
            }
        }
    }

    private void getAllSongs(){
        Log.d("REGISTRO -->"," Clase: CancionServiceImpl Metodo: getAllSongs");
        ServiceFactory.getClienteRest().getAllSongs(new Callback<List<Cancion>>() {
            @Override
            public void success(List<Cancion> canciones, Response response) {
                CancionDao cancionDao = new CancionDaoImpl();
                cancionDao.guardar(canciones);
                int size = canciones.size();
                for (int i=0; i<size; i++){
                    Log.d("INFO CANCION",canciones.get(i).toString()+"\n");
                }
                Toast.makeText(CancionServiceImpl.this, "Canciones Recibidas Exitosamente", Toast.LENGTH_LONG).show();
                sendBroadcast(new Intent("udea.edu.co.musicapp.NUEVA_LISTA"));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR: ","Fallo al obtener Canciones");
                Toast.makeText(CancionServiceImpl.this, "Fallo al Obtener las Canciones", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getSongById(){
        Log.d("REGISTRO -->"," Clase: CancionServiceImpl Metodo: getSongById");
       //ServiceFactory.getClienteRest().getSongByID();
    }
}
