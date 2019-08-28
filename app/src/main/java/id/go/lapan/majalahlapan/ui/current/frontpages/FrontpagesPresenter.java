package id.go.lapan.majalahlapan.ui.current.frontpages;

import id.go.lapan.majalahlapan.data.array.current.DataCurrentDetail;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.ui.current.CurrentContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.CONST_FRONTPAGES;

public class FrontpagesPresenter implements CurrentContract.PresenterChild {
    private List<DataCurrentDetail> dataCurrentDetails = new ArrayList<>();
    private CurrentContract.ViewChild view;
    private List<ResponseCurrent> dataFrontPage;

    public FrontpagesPresenter(CurrentContract.ViewChild view) {
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

        if (requestCode == CONST_FRONTPAGES) {
            ConfigRetrofit.service().requestChildFrontPages(String.valueOf(latestIssueID)).enqueue(new Callback<List<ResponseCurrent>>() {
                public void onResponse(Call<List<ResponseCurrent>> call, Response<List<ResponseCurrent>> response) {
                    dataFrontPage = response.body();
                    view.onSuccessGetDataChild(response.body(), CONST_FRONTPAGES);
                }

                @Override
                public void onFailure(Call<List<ResponseCurrent>> call, Throwable t) {
                    t.getLocalizedMessage();
                }


            });
        }
    }
}
