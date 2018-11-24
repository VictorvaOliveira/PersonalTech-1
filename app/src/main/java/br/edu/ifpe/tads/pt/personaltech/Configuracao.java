package br.edu.ifpe.tads.pt.personaltech;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Configuracao extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TimePicker timePicker;
    Calendar calendar = Calendar.getInstance();
    TimePickerDialog tpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);





    }
    public void scheduleAlarm(View V) {
        timePicker = (TimePicker)findViewById(R.id.relogioConf);

        calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND,00);


        Long time = calendar.getTimeInMillis();
        System.out.println("Tempo em milissegundos "+time);


        Intent intentAlarm = new Intent(this, AlarmeExercicio.class);

        PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(
                this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager =
                (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingAlarmIntent);
        Toast.makeText(this, "Alarme agendado.", Toast.LENGTH_LONG).show();

        this.finish();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
