package id.go.lapan.majalahlapan.ui.archive;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import id.go.lapan.majalahlapan.data.array.archive.DataArchive;
import id.go.lapan.majalahlapan.data.array.archive.DataArchiveChild;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import id.go.lapan.majalahlapan.model.archive.ResponseArchives;

import java.util.List;

public interface ArchiveContract {
    interface View {
        void showProgress();
        void hideProgress();
        void onSuccessGetData(List<ResponseArchive> dataArchives, List<DataArchive> dataYear);
        void onSuccessGetDatas(List<ResponseArchives> dataArchives);
        void onSuccessGetDataChild(List<ResponseArchive> dataChild, RecyclerView rvListChild, SparseBooleanArray expandState);
        void onSuccessGetDataRemote(List<ResponseArchive> data);
        void onErrorGetDat(String msg);
    }

    interface Presenter {
        void fillingData(int index, String year);
        void getDataListYear();
        void getDataListYearById(String id);
    }

    interface PresenterChild {
        void getDataListYear(List<ResponseArchive> data, List<DataArchive> dataArchives, String year, RecyclerView rvListChild, SparseBooleanArray expandState);
    }
}
