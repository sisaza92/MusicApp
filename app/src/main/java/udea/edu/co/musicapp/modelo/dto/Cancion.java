package udea.edu.co.musicapp.modelo.dto;

/**
 * Created by CristianCamilo on 20/09/2016.
 */
import java.io.Serializable;

/**
 * Representa una Cancion de la aplicación
 * @author CristianCamilo
 */
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idCancion;
    private String tituloCancion;
    private String album;
    private String artista;
    private String rutaCancion;

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public String getTituloCancion() {
        return tituloCancion;
    }

    public void setTituloCancion(String tituloCancion) {
        this.tituloCancion = tituloCancion;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getRutaCancion() {
        return rutaCancion;
    }

    public void setRutaCancion(String rutaCancion) {
        this.rutaCancion = rutaCancion;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", tituloCancion='" + tituloCancion + '\'' +
                ", album='" + album + '\'' +
                ", artista='" + artista + '\'' +
                ", rutaCancion='" + rutaCancion + '\'' +
                '}';
    }
}