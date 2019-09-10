package id.go.lapan.majalahlapan.ui.current.articles;

import id.go.lapan.majalahlapan.data.array.current.DataCurrentDetail;
import id.go.lapan.majalahlapan.data.remote.Api;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.data.remote.ServiceGenerator;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.ui.current.CurrentContract;
import id.go.lapan.majalahlapan.utils.Constant;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.CONST_ARTICLE;

public class ArticlesPresenter implements CurrentContract.PresenterChild {

    private List<DataCurrentDetail> dataCurrentDetails = new ArrayList<>();
    private CurrentContract.ViewChild view;
    List<ResponseCurrent> dataArticle;

    public ArticlesPresenter(CurrentContract.ViewChild view) {
        this.view = view;
    }

    // Mengisi data Current Articles
    @Override
    public void fillingData(int idPart) {
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Judul Artikel 1", "Penulis 1","1", "20", "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Judul Artikel 2", "Penulis 2","21", "35", "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Judul Artikel 3", "Penulis 3","36", "41", "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Judul Artikel 4", "Penulis 4","42", "61", "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Judul Artikel 5", "Penulis 5","62", "69", "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
    }

    // Jika request code ialah constant CONST_ARTICLE maka akan mengisi data yang kemudian data itu akan dikirimkan ke
    // onSuccessGetDataChild di CurrentFragment
    @Override
    public void getData(int idPart, int requestCode) {
//        view.showProgress();
        if (requestCode == CONST_ARTICLE) {
            Api api = ServiceGenerator.createService(Api.class, Constant.username,Constant.pass);

            api.requestChildArticlePages(String.valueOf(idPart)).enqueue(new Callback<List<ResponseCurrent>>() {
                @Override
                public void onResponse(@NotNull Call<List<ResponseCurrent>> call, @NotNull Response<List<ResponseCurrent>> response) {
                    view.hideProgress();
                    view.onSuccessGetDataChild(response.body(), CONST_ARTICLE);
                }

                @Override
                public void onFailure(Call<List<ResponseCurrent>> call, Throwable t) {
                    t.getMessage();
                    view.hideProgress();
                }
            });
        }
    }
}
