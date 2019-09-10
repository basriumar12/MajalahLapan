package id.go.lapan.majalahlapan.ui.archive.child;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import id.go.lapan.majalahlapan.data.array.archive.DataArchive;
import id.go.lapan.majalahlapan.data.remote.Api;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.data.remote.ServiceGenerator;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import id.go.lapan.majalahlapan.ui.archive.ArchiveContract;
import id.go.lapan.majalahlapan.utils.Constant;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ArchivePresenterChild extends IntentService implements ArchiveContract.PresenterChild {

    private final ArchiveContract.View view;
    List<ResponseArchive> dataChild;
    List<DataArchive> dataArchives;
    String year;
    RecyclerView rvListChild;
    SparseBooleanArray expandState;

    public ArchivePresenterChild(ArchiveContract.View view1) {
        super("ArchivePresenterChild");
        this.view = view1;
    }

    @Override
    public void getDataListYear(List<ResponseArchive> data, List<DataArchive> dataArchives, String year, RecyclerView rvListChild, SparseBooleanArray expandState) {
        // Mengisi Data berdasarkan Tahun Archive yang kemudian data akan dilempar ke fungsi onsuccessdatachild
        // dengan membawa data
        this.dataArchives = dataArchives;
        this.dataChild = data;
        this.rvListChild = rvListChild;
        this.year = year;
        this.expandState = expandState;

//        Intent a =

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            for (int index = 0; index < dataArchives.size(); index++) {
                Api api = ServiceGenerator.createService(Api.class, Constant.username,Constant.pass);


                this.dataChild = api.requestDataChildArchive(dataArchives.get(index).getYear()).execute().body();
                view.onSuccessGetDataChild(dataChild, rvListChild, expandState);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
