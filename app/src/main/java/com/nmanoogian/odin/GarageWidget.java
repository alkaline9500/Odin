package com.nmanoogian.odin;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Implementation of App Widget functionality.
 */
public class GarageWidget extends AppWidgetProvider implements ValhallaAsyncDelegate {
    public static String GARAGE_TOGGLE_ACTION = "com.nmanoogian.WIDGET_TOGGLE_GARAGE";
    private Context currentContext;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < N; i++)
        {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.garage_widget);

            Intent intent = new Intent(context, GarageWidget.class);
            intent.setAction(GARAGE_TOGGLE_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            views.setOnClickPendingIntent(R.id.garage_widget_button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);
        if (intent.getAction().equals(GARAGE_TOGGLE_ACTION))
        {
            this.currentContext = context;
            ValhallaAPIManager.toggleGarage(this);
        }
    }

    //@Override
    public void didFinishTask(ValhallaResponse response)
    {
        // Null response means some kind of failure
        if (response == null )
        {
            Toast.makeText(this.currentContext, "There was a problem.", Toast.LENGTH_LONG).show();
            return;
        }

        // Success

        // We got a response back
        if (response.getResponse() != null)
        {
            Toast.makeText(this.currentContext, response.getResponse(), Toast.LENGTH_LONG).show();
        }
    }
}


