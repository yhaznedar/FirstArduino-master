package com.example.yunus.firstarduino;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Yunus on 16.11.2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private final int NOTIFICATION_ID =1;
    private final int NOTIFICATION_ID2 =1;
    private final int NOTIFICATION_ID3 =1;
    private final int NOTIFICATION_ID4 =1;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(MainActivity.bolme1.isChecked()) {
            NotificationManager mng = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder nb = new Notification.Builder(context);
            nb.setContentTitle("Hatırlatma");
            nb.setContentText("Bölme 1'in ilaç vakti geldi!");
            nb.setSmallIcon(R.drawable.alarmflat);
            nb.setTicker("Bildiriminiz var.");
            nb.setDefaults(Notification.DEFAULT_SOUND);
            nb.setAutoCancel(true);
            PendingIntent pi = PendingIntent.getActivity(context, NOTIFICATION_ID, intent, 0);
            nb.setContentIntent(pi);
            Notification not = nb.getNotification();
            not.vibrate = new long[]{500, 500, 500, 500};
            mng.notify(NOTIFICATION_ID, not);

        }



        if(MainActivity.bolme2.isChecked()) {
            NotificationManager mng = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder nb = new Notification.Builder(context);
            nb.setContentTitle("Hatırlatma");
            nb.setContentText("Bölme 2'nin ilaç vakti geldi!");
            nb.setSmallIcon(R.drawable.alarmflat);
            nb.setTicker("Bildiriminiz var.");
            nb.setAutoCancel(true);
            nb.setDefaults(Notification.DEFAULT_SOUND);
            PendingIntent pi = PendingIntent.getActivity(context, NOTIFICATION_ID2, intent, 0);
            nb.setContentIntent(pi);
            Notification not = nb.getNotification();
            not.vibrate = new long[]{500, 500, 500, 500};
            mng.notify(NOTIFICATION_ID2, not);

        }

        if(MainActivity.bolme3.isChecked()) {
            NotificationManager mng = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder nb = new Notification.Builder(context);
            nb.setContentTitle("Hatırlatma");
            nb.setContentText("Bölme 3'nin ilaç vakti geldi!");
            nb.setSmallIcon(R.drawable.alarmflat);
            nb.setTicker("Bildiriminiz var.");
            nb.setAutoCancel(true);
            nb.setDefaults(Notification.DEFAULT_SOUND);
            PendingIntent pi = PendingIntent.getActivity(context, NOTIFICATION_ID3, intent, 0);
            nb.setContentIntent(pi);
            Notification not = nb.getNotification();
            not.vibrate = new long[]{500, 500, 500, 500};
            mng.notify(NOTIFICATION_ID3, not);
        }

        if(MainActivity.bolme4.isChecked()) {
            NotificationManager mng = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder nb = new Notification.Builder(context);
            nb.setContentTitle("Hatırlatma");
            nb.setContentText("Bölme 4'nin ilaç vakti geldi!");
            nb.setSmallIcon(R.drawable.alarmflat);
            nb.setTicker("Bildiriminiz var.");
            nb.setAutoCancel(true);
            nb.setDefaults(Notification.DEFAULT_SOUND);
            PendingIntent pi = PendingIntent.getActivity(context, NOTIFICATION_ID4, intent, 0);
            nb.setContentIntent(pi);
            Notification not = nb.getNotification();
            not.vibrate = new long[]{500, 500, 500, 500};
            mng.notify(NOTIFICATION_ID4, not);
        }

        //Alarm ringtone
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alarmUri == null) {
                alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        final Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        new CountDownTimer(30000, 1000) { //30 saniye sonra alarm duruyor.

            public void onTick(long millisUntilFinished)
            {
                if(MainActivity.bolme1.isChecked())
                {
                    ringtone.play();
                    BluetoothBaglanti.bolme1Yak();

                }

                if(MainActivity.bolme2.isChecked())
                {
                    ringtone.play();
                    BluetoothBaglanti.bolme2Yak();

                }

                if(MainActivity.bolme3.isChecked())
                {
                    ringtone.play();
                    BluetoothBaglanti.bolme3Yak();



                }
                if(MainActivity.bolme4.isChecked())
                {
                    ringtone.play();
                    BluetoothBaglanti.bolme4Yak();


                }

            }


            public void onFinish()
            {
                if(MainActivity.bolme1.isChecked())
                {
                    ringtone.stop();
                    if(MainActivity.sp1.getSelectedItem().toString().equals("Zamanı seçin:"))
                    {
                        MainActivity.bolme1.setChecked(false);
                        BluetoothBaglanti.bolme1Sondur();
                    }
                }

                if(MainActivity.bolme2.isChecked())
                {
                    ringtone.stop();
                    if(MainActivity.sp2.getSelectedItem().toString().equals("Zamanı seçin:")) {
                        MainActivity.bolme2.setChecked(false);
                        BluetoothBaglanti.bolme2Sondur();
                    }

                }

                if(MainActivity.bolme3.isChecked())
                {
                    ringtone.stop();
                    if(MainActivity.sp3.getSelectedItem().toString().equals("Zamanı seçin:"))
                    {
                        MainActivity.bolme3.setChecked(false);
                        BluetoothBaglanti.bolme3Sondur();
                    }


                }
                if(MainActivity.bolme4.isChecked())
                {
                    ringtone.stop();
                    if(MainActivity.sp4.getSelectedItem().toString().equals("Zamanı seçin:"))
                    {

                        MainActivity.bolme4.setChecked(false);
                        BluetoothBaglanti.bolme4Sondur();
                    }

                }
            }
        }.start();



        }

}
