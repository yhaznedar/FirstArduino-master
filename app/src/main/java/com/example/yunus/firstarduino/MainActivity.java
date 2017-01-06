package com.example.yunus.firstarduino;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
/*
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;*/ //bunlar Arduino için kütüphaneler


public class MainActivity extends AppCompatActivity {


    public Button btn1, btn2, btn3, btn4;
    public static Spinner sp1, sp2, sp3, sp4;
    public EditText saat1, saat2, saat3, saat4;
    public static Switch bolme1,bolme2,bolme3,bolme4;
    public TimePickerDialog timePickerDialog;





    private void init() {
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        sp1 = (Spinner) findViewById(R.id.spinner);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp3 = (Spinner) findViewById(R.id.spinner3);
        sp4 = (Spinner) findViewById(R.id.spinner4);

        saat1 = (EditText) findViewById(R.id.bolme1saat);
        saat1.setInputType(0); //klavye focus edemiyor.
        saat2 = (EditText) findViewById(R.id.bolme2saat);
        saat2.setInputType(0);
        saat3 = (EditText) findViewById(R.id.bolme3saat);
        saat3.setInputType(0);
        saat4 = (EditText) findViewById(R.id.bolme4saat);
        saat4.setInputType(0);

        ArrayAdapter<String> spinnerTekrarArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.tekrar));
        sp1.setAdapter(spinnerTekrarArrayAdapter);
        sp2.setAdapter(spinnerTekrarArrayAdapter);
        sp3.setAdapter(spinnerTekrarArrayAdapter);
        sp4.setAdapter(spinnerTekrarArrayAdapter);

        bolme1= (Switch) findViewById(R.id.switch1);
        bolme2= (Switch) findViewById(R.id.switch2);
        bolme3= (Switch) findViewById(R.id.switch3);
        bolme4= (Switch) findViewById(R.id.switch4);





    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); //ekrana elemanları yerleştir



    }

    public void selectTimeToDisplay(View view) {
            Calendar calendar = Calendar.getInstance();

            timePickerDialog = new TimePickerDialog(
                    MainActivity.this,
                    onTimeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    DateFormat.is24HourFormat(this));
            timePickerDialog.show();

        }
    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            final Calendar calNow = Calendar.getInstance();
            final Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);
            if(calSet.compareTo(calNow) <= 0){

                calSet.add(Calendar.DATE, 1);
            }  setSelectedTime(hourOfDay,minute);

            bolme1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean aktiflestirmisse)
                {
                    if(aktiflestirmisse)
                    {
                        bolme1setAlarm(calSet);
                        btn1.setBackgroundColor(getResources().getColor(R.color.aktifdurum));
                        Toast t= Toast.makeText(MainActivity.this,"Bölme 1 aktif hale getirildi.",Toast.LENGTH_SHORT);
                        t.show();


                    }

                    else
                    {
                        cancelAlarm(0);
                        btn1.setBackgroundColor(getResources().getColor(R.color.pasifdurum));
                        Toast t = Toast.makeText(MainActivity.this, "Bölme 1 pasif hale getirildi.", Toast.LENGTH_SHORT);
                        t.show();

                    }

                }
            });

            bolme2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean aktiflestirmisse)
                {
                    if(aktiflestirmisse)
                    {
                        bolme2setAlarm(calSet);
                        btn2.setBackgroundColor(getResources().getColor(R.color.aktifdurum));
                        Toast t= Toast.makeText(MainActivity.this,"Bölme 2 aktif hale getirildi.",Toast.LENGTH_SHORT);
                        t.show();

                    }

                    else
                    {
                        cancelAlarm(1);
                        btn2.setBackgroundColor(getResources().getColor(R.color.pasifdurum));
                        Toast t = Toast.makeText(MainActivity.this, "Bölme 2 pasif hale getirildi.", Toast.LENGTH_SHORT);
                        t.show();

                    }
                }
            });

            bolme3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean aktiflestirmisse)
                {
                    if(aktiflestirmisse)
                    {
                        bolme3setAlarm(calSet);
                        btn3.setBackgroundColor(getResources().getColor(R.color.aktifdurum));
                        Toast t= Toast.makeText(MainActivity.this,"Bölme 3 aktif hale getirildi.",Toast.LENGTH_SHORT);
                        t.show();


                    }

                    else
                    {
                        cancelAlarm(2);
                        btn3.setBackgroundColor(getResources().getColor(R.color.pasifdurum));
                        Toast t = Toast.makeText(MainActivity.this, "Bölme 3 pasif hale getirildi.", Toast.LENGTH_SHORT);
                        t.show();

                    }
                }
            });

            bolme4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean aktiflestirmisse)
                {
                    if(aktiflestirmisse)
                    {
                        bolme4setAlarm(calSet);
                        btn4.setBackgroundColor(getResources().getColor(R.color.aktifdurum));
                        Toast t= Toast.makeText(MainActivity.this,"Bölme 4 aktif hale getirildi.",Toast.LENGTH_SHORT);
                        t.show();

                    }

                    else
                    {
                        cancelAlarm(3);
                        btn4.setBackgroundColor(getResources().getColor(R.color.pasifdurum));
                        Toast t = Toast.makeText(MainActivity.this, "Bölme 4 pasif hale getirildi.", Toast.LENGTH_SHORT);
                        t.show();

                    }
                }
            });


        }};

    public void setSelectedTime(int hourOfDay,int minute)
    {

        if(saat1.hasFocus())
            saat1.setText(hourOfDay+":"+minute);
        else if (saat2.hasFocus())
            saat2.setText(hourOfDay+":"+minute);
        else if (saat3.hasFocus())
            saat3.setText(hourOfDay+":"+minute);
        else if (saat4.hasFocus())
            saat4.setText(hourOfDay+":"+minute);
    }

    public void bolme1setAlarm(Calendar alarmCalender)
    {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);
        if(sp1.getSelectedItem().toString().equals("12 saatte bir"))
        {

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(),(1000*60*720), pendingIntent);
            //720dakika=12 saat

        }
        if(sp1.getSelectedItem().toString().equals("24 saatte bir"))
        {

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(),(1000*60*1440), pendingIntent);
            //1440dakika=24 saat

        }


    }

    public void bolme2setAlarm(Calendar alarmCalender)
    {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }

    public void bolme3setAlarm(Calendar alarmCalender)
    {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }

    public void bolme4setAlarm(Calendar alarmCalender)
    {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }

    public void cancelAlarm(long id)
    {

            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), (int)id, intent,  PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(sender);
        }
    }







