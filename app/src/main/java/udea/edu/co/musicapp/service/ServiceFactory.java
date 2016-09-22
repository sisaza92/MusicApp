package udea.edu.co.musicapp.service;

import android.util.Log;

import retrofit.RestAdapter;

/**
 * Created by CristianCamilo on 20/09/2016.
 */
public class ServiceFactory {

        private static CancionServiceInterface CLIENTE_REST;
        private static String URL_ROOT = "http://192.168.43.194:8084/";

        static {
            setupRestClient();
        }

        private ServiceFactory() {}

        public static CancionServiceInterface getClienteRest() {
            Log.d("Clase: ServiceFactory","Metodo: getClienteRest");
            return CLIENTE_REST;
        }
        private static void setupRestClient() {
            Log.d("Clase: ServiceFactory","Metodo: setupRestClient");
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(URL_ROOT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            CLIENTE_REST = restAdapter.create(CancionServiceInterface.class);
        }
    }





