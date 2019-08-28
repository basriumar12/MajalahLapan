package id.go.lapan.majalahlapan.ui.announcement;

import id.go.lapan.majalahlapan.model.announcement.ResponseAnnouncement;

import java.util.List;

// Mendeklarasikan dan Menyimpan Fungsi yang digunakan untuk View (Fragment) dan Presenter

public interface AnnouncementContract {
    interface View {
        void showProgress(String msg);

        void hideProgress();

        void onSuccessGetData(List<ResponseAnnouncement> dataAnnouncement);

        void onErrorGetData(String msg);
    }

    interface Presenter {
        void getDataAnnouncement(int journalID);
    }
}
