package com.example.serviciostema3;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class WirelessTester extends Service {
    final String tag="Demo Servicio";
    public boolean enEjecucion=false;
    public boolean wifi_activo=false;
    /** Llamado cuando se crea el servicio. */
    @Override
    public void onCreate() {
        Log.i(tag, "Servicio WirelessTester creado!");
    }
    /** El servicio se arranca mediante una llamada startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!enEjecucion) {
            enEjecucion = true;
            Log.i(tag, "Servicio WirelessTester arrancado!");
        }
        else {
            Log.i(tag, "El servicio WirelessTester ya estaba arrancado!");
        }
        return START_STICKY;
    }
    /** un cliente se vincula cuando llama a bindService()
     * Como es un servicio no vinculado, devolvemos null */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /** Llamado cuando se destruye el servicio */
    @Override
    public void onDestroy() {
        Log.i(tag, "Servicio WirelessTester destruido!");
    }

    public class Tester extends Thread{
        @Override
        public void run() {
            while(enEjecucion) {
                try {
                    Log.i(tag, "servicio ejecutándose....");
                    if(wifi_activo!=CompruebaConexionWifi()){
                        wifi_activo=!wifi_activo; //Cambio de estado
                        if(wifi_activo)
                            Log.i(tag,"Conexión wifi activada");
                        else
                            Log.i(tag,"Conexión wifi desactivada");
                    }
                    this.sleep(3000);
                }
                catch (InterruptedException e) {
                    enEjecucion=false;
                    Log.i(tag, "hilo del servicio interrumpido....");
                }
            }
        }
        public boolean CompruebaConexionWifi(){
            //crea un objeto ConnectivityManager que nos da información de la red
            ConnectivityManager connectivity = (ConnectivityManager)
                    getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                //Objtener información de la red: acceso vía WIFI
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null) {
                    //Mirar si el dispositivo está conectado por WIFI
                    if (info.isConnected()) {
                        return true;//hay conexión
                    }
                }
            }
            return false; //no hay conexión
        }
    }


}
