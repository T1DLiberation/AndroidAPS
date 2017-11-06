package info.nightscout.androidaps.plugins.Overview.notifications;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;

import info.nightscout.androidaps.MainApp;
import info.nightscout.androidaps.plugins.Overview.events.EventDismissNotification;

public class DismissNotificationService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DismissNotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MainApp.bus().post(new EventDismissNotification(intent.getIntExtra("alertID", -1)));
    }

    public static PendingIntent deleteIntent(int id){
        Intent intent = new Intent(MainApp.instance(), DismissNotificationService.class);
        intent.putExtra("alertID", id);
        return PendingIntent.getService(MainApp.instance(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
