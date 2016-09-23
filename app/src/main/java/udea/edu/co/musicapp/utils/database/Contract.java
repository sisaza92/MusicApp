package udea.edu.co.musicapp.utils.database;

/**
 * Created by alejandro.isazad on 20/09/16.
 */
public class Contract {
        // DB specific constants
        public static final  String DB_NAME = "musicapp.db";
        public static final int DB_VERSION = 1;
        public static final String CANCION = "cancion";


        public class Column {

            public static final String ID = "idCancion";
            public static final String TITULO = "tituloCancion";
            public static final String ALBUM = "album";
            public static final String ARTISTA = "artista";
            public static final String RUTA = "rutaCancion";

        }

}
