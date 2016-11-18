package com.kitswgl.into;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Peer_Finder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peer_finder);

        final WifiManager mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.setWifiEnabled(true);//enabling Wi-fi

        Button p = (Button) findViewById(R.id.btn_peers);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class WifiScanReceiver extends BroadcastReceiver {
                    public void onReceive(Context c, Intent intent) {
                    }
                }

                WifiScanReceiver wifiReciever = new WifiScanReceiver();

                registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                List<ScanResult> wifiScanList = mainWifiObj.getScanResults();


                final AlertDialog alertDialog = new AlertDialog.Builder(Peer_Finder.this).create();
                alertDialog.setTitle("Wi-Fi networks");

                if (wifiScanList != null) {
                    alertDialog.setMessage("Total devices: " + wifiScanList.size());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    for (int i = 0; i < wifiScanList.size(); i++) {
                        AlertDialog alertDialo = new AlertDialog.Builder(Peer_Finder.this).create();
                        alertDialo.setTitle("Device: " + i);
                        alertDialo.setMessage(wifiScanList.get(i).SSID.toString());
                        alertDialo.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        alertDialo.show();
                    }
                } else
                    alertDialog.setMessage("No devices found.");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                alertDialog.show();
            }
        });

        // initialize listview

        //ArrayAdapter arrayAdapter = new ArrayAdapter(PeerFinder.this, android.R.layout.simple_list_item_1, wifiScanList );
        //lv.setAdapter(arrayAdapter);
    }


}
