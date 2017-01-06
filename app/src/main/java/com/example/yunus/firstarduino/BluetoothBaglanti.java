package com.example.yunus.firstarduino;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;
import mehdi.sakout.fancybuttons.FancyButton;

public class BluetoothBaglanti extends AppCompatActivity {

    FancyButton  connectBtn;
    TextView deviceInfo;
    public static BluetoothSPP bt;
    public boolean isFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_baglanti);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                //  Create a new boolean and preference and set it to true
                isFirstStart = getPrefs.getBoolean("firstStart", true);
                //  If the activity has never started before...
                if (isFirstStart) {
                    //  Launch app intro
                    Intent i = new Intent(BluetoothBaglanti.this, MyIntro.class);
                    startActivity(i);
                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();
                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);
                    //  Apply changes
                    e.apply();
                }
            }
        });

// Start the thread
        t.start();

        connectBtn = (FancyButton) findViewById(R.id.button5);
        deviceInfo = (TextView) findViewById(R.id.textView13);

        if(isFirstStart==false)
            setup();

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                deviceInfo.setText(name + "- MAC Adresi: " + address);
                startActivity(new Intent(BluetoothBaglanti.this,MainActivity.class));
            }

            public void onDeviceDisconnected()
            {
                deviceInfo.setText("Cihaz ile bağlantınız koptu.");
            }

            public void onDeviceConnectionFailed() {
                deviceInfo.setText("Cihaz ile bağlantı kurulamadı.");
            }
        });



        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);

                    intent.putExtra("bluetooth_devices", "Bluetooth Cihazları:");
                    intent.putExtra("no_devices_found", "Cihaz bulunamadı!");
                    intent.putExtra("scanning", "Taranıyor...");
                    intent.putExtra("scan_for_devices", "Tara");
                    intent.putExtra("select_device", "Seç");
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);


                }
            }
        });








    }

    public static void bolme1Yak() {
        String data = "A"; //yak

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }
    public static void bolme2Yak() {
        String data = "B"; //yak

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }

    public static void bolme3Yak() {
        String data = "C"; //yak

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }
    public static void bolme4Yak() {
        String data = "D"; //yak

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }

    public static void bolme1Sondur() {
        String data = "W"; //sondür

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }
    public static void bolme2Sondur() {
        String data = "X"; //sondür

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }
    public static void bolme3Sondur() {
        String data = "Y"; //sondür

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }
    public static void bolme4Sondur() {
        String data = "Z"; //sondür

        if (data.length() > 0) {
            bt.send(data.toString() + "\n", false);

        }
    }

    public void setup() {
        bt = new BluetoothSPP(getApplicationContext());

        if (!bt.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(), "Bluetooth mevcut değil!", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (!bt.isBluetoothEnabled()) {
            bt.enable();
        }
        bt.setupService();
        bt.startService(false);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK) {
                deviceInfo.setText("Bağlanılıyor...");
                bt.connect(data);
            }
        }
    }

}


