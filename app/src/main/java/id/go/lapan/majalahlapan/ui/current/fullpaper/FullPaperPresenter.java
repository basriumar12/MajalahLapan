package id.go.lapan.majalahlapan.ui.current.fullpaper;

import id.go.lapan.majalahlapan.data.array.current.DataCurrentDetail;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.ui.current.CurrentContract;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.CONST_FULLPAPER;

public class FullPaperPresenter implements CurrentContract.PresenterChild {
    private List<DataCurrentDetail> dataCurrentDetails = new ArrayList<>();
    private CurrentContract.ViewChild view;
    List<ResponseCurrent> dataFrontPage;

    public FullPaperPresenter(CurrentContract.ViewChild view) {
        this.view = view;
    }

    // Mengisi data Current ArchiveFrontPages
    @Override
    public void fillingData(int idPart) {
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Front Page Vol. 19 No. 12 April 2019", "Redaction Team", null, null, "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
    }

    // Jika request code ialah constant CONST_FRONTPAGES maka akan mengisi data yang kemudian data itu akan dikirimkan ke
    // onSuccessGetDataChild di CurrentFragment
    @Override
    public void getData(int latestIssueID, int requestCode) {
        if (requestCode == CONST_FULLPAPER) {
            ConfigRetrofit.service().requestFullEditionPages(String.valueOf(latestIssueID)).enqueue(new Callback<List<ResponseCurrent>>() {
                public void onResponse(@NotNull Call<List<ResponseCurrent>> call, @NotNull Response<List<ResponseCurrent>> response) {
                    view.hideProgress();
                    view.onSuccessGetDataChild(response.body(), CONST_FULLPAPER);
                }

                @Override
                public void onFailure(@NotNull Call<List<ResponseCurrent>> call, @NotNull Throwable t) {
                    t.getLocalizedMessage();
                }
            });
        }
    }
}
