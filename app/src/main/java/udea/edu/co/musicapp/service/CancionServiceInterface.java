package udea.edu.co.musicapp.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import udea.edu.co.musicapp.modelo.dto.Cancion;

/**
 * Created by CristianCamilo on 20/09/2016.
 */
public interface CancionServiceInterface {

    @GET("/MusicAppService/Canciones/getSongById/{idCancion}")
    void getSongByID(@Path("idCancion") int idCancion, Callback<Cancion> callback);

    @GET("/MusicAppService/Canciones/getAllSongs")
    void getAllSongs(Callback<List<Cancion>> callback);


}
