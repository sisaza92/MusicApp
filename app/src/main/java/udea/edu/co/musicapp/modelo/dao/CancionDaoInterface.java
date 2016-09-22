package udea.edu.co.musicapp.modelo.dao;

import java.util.List;

import udea.edu.co.musicapp.modelo.dto.Cancion;

/**
 * Created by alejandro.isazad on 20/09/16.
 */
public interface CancionDaoInterface {

    public void guardar(List<Cancion> canciones);
    public List<Cancion> getAllSongs();

}
