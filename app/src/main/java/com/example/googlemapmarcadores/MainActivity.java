package com.example.googlemapmarcadores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.googlemapmarcadores.AdapterWindows.AdapterInfo;
import com.example.googlemapmarcadores.Modelo.Marcadores;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private GoogleMap map;
    List<Marcadores> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady( GoogleMap googleMap) {
        map=googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
    }
    private void getInfoFacultades()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://my-json-server.typicode.com/LisLeoMeraC/FacultadesUteq/Facultades";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0) {
                            info= new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject Obj = response.getJSONObject(i);
                                    System.out.println("Facultad: "+Obj.get("facultad"));
                                    info.add(new Marcadores(Obj.get("facultad").toString(), Obj.get("decano").toString(),
                                            Obj.get("email").toString(), Obj.get("logo").toString(), Double.parseDouble(Obj.get("latitud").toString())
                                            , Double.parseDouble(Obj.get("longitud").toString()),Obj.get("tel").toString()));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            setMarkerFacultades(info);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void setMarkerFacultades(List<Marcadores> datos)
    {
        for(int i=0;i<datos.size();i++)
        {
            final LatLng latLng = new LatLng(datos.get(i).getLat(),datos.get(i).getLonguitud());
            map.addMarker(
                    new MarkerOptions()
                            .position(latLng)
                            .title(datos.get(i).getLogo())
                            .snippet("\n FACULTAD: "+datos.get(i).getFacultad()+ "\n DECANO/A: "+datos.get(i).getDecano()+
                                    "\n CORREO: "+datos.get(i).getEmail()+ "\n LAT: "+datos.get(i).getLat()+
                                    "\n LONG: "+datos.get(i).getLonguitud()+ "\n TELÉFONO: "+datos.get(i).getTel()+""));
        }
    }

    @Override
    public boolean onMarkerClick( Marker marker) {
        Integer clickCount = (Integer) marker.getTag();
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void campusCentral(View view)
    {
        LatLng latLng = new LatLng(-1.0128684338088096,-79.46930575553893 );
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(19)
                .bearing(45)
                .tilt(90)
                .build();
        CameraUpdate update =
                CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(update);
        map.setInfoWindowAdapter(new AdapterInfo(MainActivity.this));
        map.setMapType(1);
        getInfoFacultades();
    }
    public void campusMaria(View view)
    {
        LatLng latLng = new LatLng(-1.079549085059133, -79.50058063876239);

        CameraPosition camaraP = new CameraPosition.Builder()
                .target(latLng)
                .zoom(19)
                .bearing(45)
                .tilt(90)
                .build();
        CameraUpdate update =
                CameraUpdateFactory.newCameraPosition(camaraP);
        map.animateCamera(update);
        map.setInfoWindowAdapter(new AdapterInfo(MainActivity.this));
        map.setMapType(1);
        getInfoFacultades();
    }
}