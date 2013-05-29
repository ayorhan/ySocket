package com.ayorhan.android.ySocket;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketNotificationHandler {
    private Notifier notifier;
    private YSocketUpdateNotification notification;

    public YSocketNotificationHandler(Notifier notifier){
        this.notifier = notifier;
    }

    public void notify(YSocketUpdateNotification notification){
        this.notification = notification;
        notifier.onMessageReceived(notification);
    }

    public static abstract class Notifier{
        public abstract void onMessageReceived(YSocketUpdateNotification message);
    }
}
