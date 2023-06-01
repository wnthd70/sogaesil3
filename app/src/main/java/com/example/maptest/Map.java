package com.example.maptest;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map implements OnMapReadyCallback {
    private Context context;
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    Marker myMarker, taxiMarker;
    public Map(Context context, FragmentManager fragmentManager) {
        this.context = context;
        mapFragment = SupportMapFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.map, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        LatLng SEOUL = new LatLng(37.556, 126.97);

//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(SEOUL);
//        markerOptions.title("서울");
//        markerOptions.snippet("한국 수도");
//
//        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
    }

    public void addMarker(LatLng latLng){
        // 기존 마커 제거
        if (myMarker != null) {
            myMarker.remove();
        }
        myMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("내 위치"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
        Toast.makeText(context.getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
    }
}

