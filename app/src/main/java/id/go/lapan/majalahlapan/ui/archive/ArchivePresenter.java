package id.go.lapan.majalahlapan.ui.archive;

import android.content.Intent;
import android.text.LoginFilter;
import android.util.Log;
import id.go.lapan.majalahlapan.data.array.archive.DataArchive;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import id.go.lapan.majalahlapan.model.archive.ResponseArchives;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static android.media.CamcorderProfile.get;

public class ArchivePresenter implements ArchiveContract.Presenter {

    private final ArchiveContract.View view;
    //    private List<ResponseArchive> dataArchives = new ArrayList<>();
    private List<DataArchive> dataArchives = new ArrayList<>();
    private String year = "a";

    ArchivePresenter(ArchiveContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListYear() {
        view.showProgress();
        ConfigRetrofit.service().requestDataArchive().enqueue(new Callback<List<ResponseArchive>>() {
            @Override
            public void onResponse(Call<List<ResponseArchive>> call, Response<List<ResponseArchive>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        getYear(response.body());

                    }
                    view.hideProgress();
                    view.onSuccessGetData(response.body(), dataArchives);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseArchive>> call, Throwable t) {
                view.onErrorGetDat(t.getMessage());
                view.hideProgress();
            }
        });
    }

    @Override
    public void getDataListYearById(String id) {
        view.showProgress();
        ConfigRetrofit.service().requestDataArchives(id).enqueue(new Callback<List<ResponseArchives>>() {
            @Override
            public void onResponse(Call<List<ResponseArchives>> call, Response<List<ResponseArchives>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
//                        getYear(response.body());


                    view.hideProgress();
                    view.onSuccessGetDatas(response.body());
                    }
                } else {
                    view.onErrorGetDat("Ada Masalah jaringan, request time out");
                }
            }

            @Override
            public void onFailure(Call<List<ResponseArchives>> call, Throwable t) {
                view.onErrorGetDat("Ada Masalah jaringan, request time out");
                view.hideProgress();
            }
        });

    }

    //@Override
    //public void getDataListByYear(String year) {
//        ConfigRetrofit.service().requestIssueByYear(year.toString()).enqueue(new Callback<List<ResponseArchive>>() {
//            @Override
//            public void onResponse(Call<List<ResponseArchive>> call, Response<List<ResponseArchive>> response) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        view.onSuccessGetDataByYear(response.body(), dataArchives);
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ResponseArchive>> call, Throwable t) {
//                view.onErrorGetDat(t.getMessage());
//            }
//        });
    //}

    private void getYear(List<ResponseArchive> body) {
        for (int x = 0; x < body.size(); x++) {
            dataArchives.add(new DataArchive(x, body.get(x).getYear(), true));
        }

        int panjangData = dataArchives.size();
        for (int i = 0; i < panjangData - 1; i++) {
            for (int j = i + 1; j < dataArchives.size(); j++) {
                if (dataArchives.get(i).getYear().equals(dataArchives.get(j).getYear())) {
                    dataArchives.remove(j);
                    j--;
                    panjangData--;
                }
            }
        }
    }


    // Mengisi data
    @Override
    public void fillingData(int position, String year) {
//        Log.d("YEAR : ", year);
//        for (int y = 0; y < dataArchives.size(); y++) {
//            if (contains(y, year)) {
//                dataArchives.add(new DataArchive(position, year, true));
//            }
//        }
    }
}
