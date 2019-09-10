package id.go.lapan.majalahlapan.ui.current.backpages;

import id.go.lapan.majalahlapan.data.array.current.DataCurrentDetail;
import id.go.lapan.majalahlapan.data.remote.Api;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.data.remote.ServiceGenerator;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.ui.current.CurrentContract;
import id.go.lapan.majalahlapan.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.CONST_BACKPAGES;

public class BackpagesPresenter implements CurrentContract.PresenterChild {
    private List<DataCurrentDetail> dataCurrentDetails = new ArrayList<>();
    private CurrentContract.ViewChild view;
    List<ResponseCurrent> dataBackPage;

    public BackpagesPresenter(CurrentContract.ViewChild view) {
        this.view = view;
    }

    // Mengisi data Current ArchiveBackPages
    @Override
    public void fillingData(int idPart) {
        dataCurrentDetails.add(new DataCurrentDetail(idPart, "Back Page Vol. 16 No. 02 Desember 2018", "Redaction Team",null, null, "https://capd.mit.edu/sites/default/files/about/files/career-handbook.pdf"));
    }

    // Jika request code ialah constant CONST_BACKPAGES maka akan mengisi data yang kemudian data itu akan dikirimkan ke
    // onSuccessGetDataChild di CurrentFragment
    @Override
    public void getData(int idPart, int requestCode) {
        if (requestCode == CONST_BACKPAGES) {
            Api api = ServiceGenerator.createService(Api.class, Constant.username,Constant.pass);

            api.requestChildBackPages(String.valueOf(idPart)).enqueue(new Callback<List<ResponseCurrent>>() {
                @Override
                public void onResponse(Call<List<ResponseCurrent>> call, Response<List<ResponseCurrent>> response) {
                    view.onSuccessGetDataChild(response.body(), CONST_BACKPAGES);
                }

                @Override
                public void onFailure(Call<List<ResponseCurrent>> call, Throwable t) {
                    t.getMessage();
                }
            });
        }
    }
}
