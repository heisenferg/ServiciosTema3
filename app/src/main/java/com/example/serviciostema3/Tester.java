package com.example.serviciostema3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


    public class Tester extends Thread{
        private WirelessTester wirelessTester;


        @Override
        public void run(){
            while (wirelessTester.enEjecucion){
                try{
                    Log.i(wirelessTester.tag, "Servicio ejecutándose...");
                    if(wirelessTester.wifi_activo!=CompruebaConexionWifi()){
                        wirelessTester.wifi_activo=!wirelessTester.wifi_activo; //Cambio de estado
                        if (wirelessTester.wifi_activo){
                            Log.i(wirelessTester.tag, "Conexión wifi activada.");

                        } else
                            Log.i(wirelessTester.tag, "Conexión wifi Desactivada");
                    }
                    this.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean CompruebaConexionWifi(){
            ConnectivityManager connectivityManager = (ConnectivityManager) wirelessTester.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager!=null){
                NetworkInfo info = connectivityManager.getNetworkInfo((ConnectivityManager.TYPE_WIFI));
                if (info!=null){
                    //Mirar si el dispositivo está conectado por wifi
                    if (info.isConnected()){
                        return true;
                    }
                }

            }
            return false;
        }
    }

