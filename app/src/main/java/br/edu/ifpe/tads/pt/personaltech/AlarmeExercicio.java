package br.edu.ifpe.tads.pt.personaltech;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmeExercicio extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
// Crio o Intent que será associado com o toque na notificação
// esse Intent irá lançar a aplicação novamente.
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                context, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
// Contrói a notificação que será exibida ao usuário
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.notificacaoimagem);
        builder.setContentText("Bora exercitar!");
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        builder.setContentIntent(pendingNotificationIntent);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
// Obter o gerenciador de notificações do sistema e exibe a notificação
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
