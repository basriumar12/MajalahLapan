package id.go.lapan.majalahlapan.ui.current.author;

import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.current.ResponseAuthor;
import id.go.lapan.majalahlapan.ui.current.CurrentContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class AuthorPresenter implements CurrentContract.PresenterAuthor {

    private List<ResponseAuthor> dataAuthor = new ArrayList<>();
    private CurrentContract.AdapterData view;

//    public ArticlesPresenter(CurrentContract.ViewChild view) {
//        this.view = view;
//    }

    @Override
    public List<ResponseAuthor> getData(String submissionID) {
        ConfigRetrofit.service().requestAuthor(submissionID).enqueue(new Callback<List<ResponseAuthor>>() {
            @Override
            public void onResponse(Call<List<ResponseAuthor>> call, Response<List<ResponseAuthor>> response) {
                dataAuthor = response.body();
            }

            @Override
            public void onFailure(Call<List<ResponseAuthor>> call, Throwable t) {
                t.getMessage();
            }
        });
        return dataAuthor;
    }
}
