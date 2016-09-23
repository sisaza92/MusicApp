package udea.edu.co.musicapp.vista.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import udea.edu.co.musicapp.R;
import udea.edu.co.musicapp.modelo.dto.Cancion;

/**
 * Created by CristianCamilo on 22/09/2016.
 */
public class CancionListAdapter extends ArrayAdapter<Cancion> {

    private Activity activity;
    ArrayList<Cancion> canciones;

    public CancionListAdapter(Activity activity, ArrayList<Cancion> listaCanciones) {

        super(activity,R.layout.item_cancion);

        this.activity = activity;
        this.canciones = listaCanciones;

    }

    static class ViewHolder {

        ImageView album_icon;
        TextView cancion_titulo;
        TextView cancion_artista;
        TextView cancion_album;
        RatingBar puntuacion;
    }

    public int getCount() {
        return canciones.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = null;
        Cancion cancion = canciones.get(position);
        LayoutInflater inflador = activity.getLayoutInflater();
        view = inflador.inflate(R.layout.item_cancion, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.album_icon = (ImageView) view.findViewById(R.id.album_icon);
        viewHolder.cancion_titulo = (TextView) view.findViewById(R.id.cancion_titulo);
        viewHolder.cancion_artista = (TextView) view.findViewById(R.id.cancion_artista);
        viewHolder.cancion_album = (TextView) view.findViewById(R.id.cancion_album);
        viewHolder.puntuacion = (RatingBar) view.findViewById(R.id.puntuacion);

        // importante!!! establecemos el mensaje
        viewHolder.album_icon.setImageResource(R.drawable.album_icon2);
        viewHolder.album_icon.setScaleType(ImageView.ScaleType.FIT_END);

        viewHolder.cancion_titulo.setText(cancion.getTituloCancion());
        viewHolder.cancion_artista.setText(cancion.getArtista());
        viewHolder.cancion_album.setText(cancion.getAlbum());

        viewHolder.puntuacion.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        viewHolder.puntuacion.setRating(5);

        return view;
    }

}
