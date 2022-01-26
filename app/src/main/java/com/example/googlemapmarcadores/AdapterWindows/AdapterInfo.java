package com.example.googlemapmarcadores.AdapterWindows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.example.googlemapmarcadores.R;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class AdapterInfo  implements GoogleMap.InfoWindowAdapter {

    Context contex;

    public AdapterInfo(Context contex) {
        this.contex = contex;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        TextView info;
        ImageView imglogo;

        View view= LayoutInflater.from(contex).inflate(R.layout.diseno_info_window,null);
        info=view.findViewById(R.id.txtInfo);

        imglogo=view.findViewById(R.id.imgLogo);

        Picasso.get().load(marker.getTitle())
                .resize(100,100)
                .centerCrop().into(imglogo);
        info.setText(marker.getSnippet());
        return view;

    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}

