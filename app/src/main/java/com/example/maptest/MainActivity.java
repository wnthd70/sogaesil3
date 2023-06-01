package com.example.maptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.maps.model.LatLng;

import okhttp3.*;

public class MainActivity extends AppCompatActivity{
//        implements OnMapReadyCallback
//    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000; // 권한 요청 식별에 사용되는 값
    private static final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 1001; // 백그라운드 위치 권한 요청 코드

    private WebSocket webSocket;
    private OkHttpClient client;
    private static final String SERVER_URL = "ws://IP:PORT";
    private String clientType = "승객";



    Map myMap;
    Button btn, callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        callBtn = (Button) findViewById(R.id.callBtn);

        FragmentManager fragmentManager = getSupportFragmentManager();
        myMap = new Map(this, fragmentManager);

//        client = new OkHttpClient();

//        Request request = new Request.Builder().url(SERVER_URL).build(); // WebSocket 연결 요청 생성
//        WebSocketListener webSocketClient = new WebSocketListener() {
//            @Override
//            public void onOpen(WebSocket webSocket, Response response) {
//                webSocket.send(clientType);
//                System.out.println("서버 연결 성공");
//            }
//        };
//        webSocket = client.newWebSocket(request, webSocketClient); // 웹소켓 연결

        // 위치 권한 요청 //
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){ //포그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        int permissionCheck2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION);

        if(permissionCheck2 == PackageManager.PERMISSION_DENIED){ //백그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE);
        }

        btn.setOnClickListener(new View.OnClickListener() { // 내 위치 버튼(GPS 관련 부분)
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "내 위치를 찾습니다.", Toast.LENGTH_SHORT).show();
                final LocationManager locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            System.out.println(latLng);

                            myMap.addMarker(latLng);

                            // 위치 업데이트를 한 번만 받고자 한다면, 여기서 위치 업데이트 리스너를 해제할 수 있습니다.
                            locationManager.removeUpdates(this);
                        }
                    });
                }

            }
        });

//        callBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Thread call = new Thread(){
//                    public void run(){
//
//                    }
//                };
//            }
//        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "위치 권한을 허용합니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "위치 권한을 거부합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

//        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

    }

//    @Override
//    public void onMapReady(final GoogleMap googleMap) {
//        mMap = googleMap;
//
//        LatLng SEOUL = new LatLng(37.556, 126.97);
//
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(SEOUL);
//        markerOptions.title("서울");
//        markerOptions.snippet("한국 수도");
//
//        mMap.addMarker(markerOptions);
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
//    }
