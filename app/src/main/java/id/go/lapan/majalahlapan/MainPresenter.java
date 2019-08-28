package id.go.lapan.majalahlapan;

import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.notification.ResponseNotification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainPresenter implements MainView.Presenter {

    private List<ResponseNotification> dataJournal;
    private final MainView.View view;

    MainPresenter(MainView.View view) {
        this.view = view;
    }

    @Override
    public void getDataIssue(String journalID) {
        ConfigRetrofit.service().requestNotifiicationByJournalID(journalID).enqueue(new Callback<List<ResponseNotification>>() {
            @Override
            public void onResponse(Call<List<ResponseNotification>> call, Response<List<ResponseNotification>> response) {
               if (response.isSuccessful()){

                   dataJournal = response.body();
                   view.onSuccessGetData(dataJournal.size());
               } else {
                   view.onErrorData("Ada Masalah dengan jaringan, request time out");
               }
            }

            @Override
            public void onFailure(Call<List<ResponseNotification>> call, Throwable t) {
                t.getMessage();
                view.onErrorData("Ada Masalah dengan jaringan, request time out");
            }
        });
    }
}
