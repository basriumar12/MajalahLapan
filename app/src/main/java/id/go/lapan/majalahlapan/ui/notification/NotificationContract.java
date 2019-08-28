package id.go.lapan.majalahlapan.ui.notification;

import id.go.lapan.majalahlapan.model.notification.ResponseNotification;

import java.util.List;

public interface NotificationContract {
    interface View {
        void onNotificationFound(List<ResponseNotification> dataNotification);
        void onErrorData(String msg);
    }

    interface Presenter {
        void getNotification(String today);
    }
}
