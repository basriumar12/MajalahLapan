package id.go.lapan.majalahlapan.ui.current;

import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.current.DataCurrent;
import id.go.lapan.majalahlapan.model.current.ResponseCurrentNew;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrentPresenter implements CurrentContract.Presenter {

    private final CurrentContract.View view;
    private List<ResponseCurrentNew> dataArchive = new ArrayList<>();
    private List<DataCurrent> dataCurrents = new ArrayList<>();

    CurrentPresenter(CurrentContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataIssue(String journalID, String issueID) {
        if (issueID != null && journalID != null) {
            view.showProgress("Loading...");
            ConfigRetrofit.service().requestDataArchivebyJournalID(journalID).enqueue(new Callback<List<ResponseCurrentNew>>() {
                @Override
                public void onResponse(Call<List<ResponseCurrentNew>> call, Response<List<ResponseCurrentNew>> response) {
                    if (response.isSuccessful()) {
                        dataArchive = response.body();
                        if (dataArchive != null && dataArchive.size() > 1) {
                            for (int i = 0; i < dataArchive.size(); i++) {
                                getIssueByYear(dataArchive.get(i).getYear(), Integer.valueOf(issueID));
                            }
                        }

                        if (dataArchive != null) {
                            sortToLatestYear(Integer.valueOf(issueID), dataArchive.size(), dataArchive);
                        }
                    } else {
                        if (response.errorBody() != null) {
                            view.hideProgress();
                            view.onErrorGetData("Ada Masalah dengan jaringan, request time out");
                        }
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<ResponseCurrentNew>> call, @NotNull Throwable t) {
                    view.hideProgress();
                    view.onErrorGetData("Ada Masalah dengan jaringan, request time out");
                }
            });
        }
    }

    private void sortToLatestYear(int issueID, int size, List<ResponseCurrentNew> dataArchive) {
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            int latestYear = Integer.parseInt(dataArchive.get(i).getYear());
            intArray[i] = latestYear;
        }
        int max = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            max = Arrays.stream(intArray).max().getAsInt();
        }
        getIssueByYear(String.valueOf(max), issueID);
    }

    private void getIssueByYear(String max, int issueID) {
        ConfigRetrofit.service().requestDataIssueByYear(String.valueOf(max)).enqueue(new Callback<List<ResponseCurrentNew>>() {
            @Override
            public void onResponse(@NotNull Call<List<ResponseCurrentNew>> call, @NotNull Response<List<ResponseCurrentNew>> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        dataCurrents = response.body().get(0).getData();
                        if (dataCurrents != null) {
                            view.hideProgress();
                            sortToLatestIssueID(dataCurrents, issueID, dataCurrents.size());
                        } else {
                            view.onErrorGetData("Ada Masalah jaringan, request time out");
                        }
//                        dataCurrents = response.body().get(0).getData();
                        sortToLatestIssueID(response.body().get(0).getData(), issueID,response.body().get(0).getData().size() );
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<ResponseCurrentNew>> call, @NotNull Throwable t) {
                t.getMessage();
                view.hideProgress();
                view.onErrorGetData("Ada Masalah jaringan, request time out");
            }
        });
    }

    private void sortToLatestIssueID(List<DataCurrent> dataCurrents, int issueID, int size) {
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
//            int latestIssue = Integer.parseInt(dataCurrents.get(size).getIssueId());
//            intArray[i] = latestIssue;
            if (issueID == Integer.valueOf(dataCurrents.get(i).getIssueId())) {
                view.onSuccessGetData(dataCurrents.get(i).getTitle(), issueID);
            }
        }

//        Log.d("myDataCurrents", dataCurrents.toString());
//        Log.d("MyArray", Arrays.toString(intArray));
//
//        for (int index = 0; index < intArray.length; index++) {
//            if (issueID == intArray[index]) {
//                view.onSuccessGetData(dataCurrents.get(index).getTitle(), index);
//            }
//
//        }
    }


}
