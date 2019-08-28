package id.go.lapan.majalahlapan.ui.announcement;

import android.util.Log;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.announcement.ResponseAnnouncement;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class AnnouncementPresenter implements AnnouncementContract.Presenter {

    private AnnouncementContract.View view;

    AnnouncementPresenter(AnnouncementContract.View view) {
        this.view = view;
    }


    @Override
    public void getDataAnnouncement(int journalID) {
        view.showProgress("Loading");
        ConfigRetrofit.service().requestDataAnnouncement(String.valueOf(journalID)).enqueue(new Callback<List<ResponseAnnouncement>>() {
            @Override
            public void onResponse(@NotNull Call<List<ResponseAnnouncement>> call, @NotNull Response<List<ResponseAnnouncement>> response) {
                if (response.isSuccessful()) {
                    view.onSuccessGetData(response.body());
                    view.hideProgress();
                } else {
                    view.onErrorGetData("Ada Masalah jaringan, request time out");
                }
            }

            @Override
            public void onFailure(Call<List<ResponseAnnouncement>> call, Throwable t) {
                Log.d("OPOKOWE", t.getLocalizedMessage());
                view.hideProgress();
                view.onErrorGetData("Ada Masalah jaringan, request time out");
            }
        });
    }
}
