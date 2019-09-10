package id.go.lapan.majalahlapan.ui.notification;

import android.content.Context;
import android.util.Log;
import id.go.lapan.majalahlapan.data.remote.Api;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.data.remote.ServiceGenerator;
import id.go.lapan.majalahlapan.model.notification.ResponseNotification;
import id.go.lapan.majalahlapan.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class NotificationPresenter implements NotificationContract.Presenter {

    private NotificationContract.View view;
    private List<ResponseNotification> dataNotification;
    private List<ResponseNotification> dataForNotification;
    Context context;

    public NotificationPresenter(NotificationContract.View view) {
        this.view = view;
    }

    @Override
    public void getNotification(String journalID) {
        Api api = ServiceGenerator.createService(Api.class, Constant.username,Constant.pass);

        api.requestNotifiicationByJournalID(journalID).enqueue(new Callback<List<ResponseNotification>>() {
            @Override
            public void onResponse(Call<List<ResponseNotification>> call, Response<List<ResponseNotification>> response) {
                if (response.isSuccessful()){

                    dataNotification = response.body();
                    view.onNotificationFound(dataNotification);
                } else {
                    view.onErrorData("Ada masalah jaringan, request time out");
                }
            }

            @Override
            public void onFailure(Call<List<ResponseNotification>> call, Throwable t) {
                Log.d("NOTIFA ERROR", t.getMessage());
                view.onErrorData("Ada masalah jaringan, request time out");

            }
        });
    }
}
