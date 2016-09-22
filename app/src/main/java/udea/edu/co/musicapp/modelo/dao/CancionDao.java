package udea.edu.co.musicapp.modelo.dao;

import java.util.List;

import udea.edu.co.musicapp.modelo.dto.Cancion;

/**
 * Created by alejandro.isazad on 20/09/16.
 */
public interface CancionDao {

    public void guardar(List<Cancion> canciones);

}
