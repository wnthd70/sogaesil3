//package com.example.maptest;
//
//import androidx.annotation.NonNull;
//
//import okhttp3.*;
//
//public abstract class WebSocketClient extends WebSocketListener {
//    private WebSocket webSocket;
//
//    public WebSocketClient(){
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url("ws://localhost:8080").build();
//
//        webSocket = client.newWebSocket(request, new WebSocketListener() {
//            @Override
//            public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
//
//            }
//
//        });
//    }
//
//    public abstract void onOpen(WebSocket webSocket, Response response);
//}
