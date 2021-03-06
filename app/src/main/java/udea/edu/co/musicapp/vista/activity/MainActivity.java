package udea.edu.co.musicapp.vista.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import udea.edu.co.musicapp.R;
import udea.edu.co.musicapp.modelo.dao.CancionDaoInterface;
import udea.edu.co.musicapp.modelo.dao.impl.CancionDaoImpl;
import udea.edu.co.musicapp.modelo.dto.Cancion;
import udea.edu.co.musicapp.service.CancionServiceImpl;
import udea.edu.co.musicapp.vista.adapter.CancionListAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private IntentFilter filtro;
    private BroadcastReceiver receptor;

    ListView listaCanciones;
    CancionDaoInterface cancionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("REGISTRO -->"," Clase: MainActivity Metodo: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCanciones = (ListView)findViewById(R.id.listview_canciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filtro = new IntentFilter("udea.edu.co.musicapp.NUEVA_LISTA");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent a = new Intent(MainActivity.this,CancionServiceImpl.class);
                a.putExtra("show", true);
                startService(a);



                return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {



            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        receptor =  new TimelineReceiver();
        registerReceiver(receptor, filtro);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receptor);
    }

    class TimelineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TimelineReceiver", "onReceived");
            CancionDaoInterface cancionDaoInterface = new CancionDaoImpl();
            List<Cancion> canciones = cancionDaoInterface.getAllSongs();
            Log.d("BROADCAST RECIBIDO", "onReceived");
            listaCanciones.setAdapter(new CancionListAdapter((Activity) context, (ArrayList<Cancion>)canciones));
        }
    }
}