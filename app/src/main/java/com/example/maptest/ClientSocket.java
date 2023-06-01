package com.example.maptest;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.UUID;

import okhttp3.WebSocket;

public class ClientSocket {
    private WebSocket clientSocket;
    private UUID uuid = UUID.randomUUID(); // 클라이언트 소켓이 생성될때마다 고유 식별자 생성
    private JSONObject object = new JSONObject();

    public void requestCallTaxi(){
        try{
            object.put("type","call");
            object.put("uuid",uuid);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("택시 호출 에러");
        }

    }

    public void sendToServerLocation(LatLng latLng){

    }

}
