package br.edu.ifpe.tads.pt.personaltech;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Configuracao extends AppCompatActivity {

    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        timePicker = (TimePicker)findViewById(R.id.relogioConf);
        Calendar calendar = Calendar.getInstance();


        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }
    public void scheduleAlarm(View V) {
       System.out.print("timePicker "+timePicker);
// Tempo atual mais 10 segundos (10 * 1000 milissegundos)
        Long time = new GregorianCalendar().getTimeInMillis()+3*1000;
        System.out.print("timePicker "+timePicker);
// Cria Intent do alarm, que será recebido pela classe AlarmReceiver
        Intent intentAlarm = new Intent(this, AlarmeExercicio.class);
// Cria um PendingIntent, usado para fazer o broadcast do alarme
        PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(
                this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
//Obtem o gerenciador de alarmes do sistema
        AlarmManager alarmManager =
                (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//Configura um alarme lançará o intent em 10 segundos
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingAlarmIntent);
        Toast.makeText(this, "Alarme agendado.", Toast.LENGTH_LONG).show();
// Finaliza a atividade
        this.finish();
    }
}
