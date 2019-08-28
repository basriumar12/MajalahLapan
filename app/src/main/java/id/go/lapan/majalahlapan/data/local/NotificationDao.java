//package id.go.lapan.majalahlapan.data.local;
//
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Query;
//import id.go.lapan.majalahlapan.model.notification.ResponseNotification;
//
//import java.util.List;
//
//@Dao
//public interface NotificationDao {
//
//    @Query("SELECT * FROM NOTIFACTION ORDER BY ID")
//    List<ResponseNotification> loadAllNotification();
//
//    @Insert
//    void insertNotif(ResponseNotification notif);
//
//    @Query("SELECT * FROM NOTIFACTION WHERE id = :id")
//    ResponseNotification loadNotificationById(int id);
//}
