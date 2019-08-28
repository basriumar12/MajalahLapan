package id.go.lapan.majalahlapan.ui.current;

import id.go.lapan.majalahlapan.model.current.ResponseAuthor;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;

import java.util.List;

public interface CurrentContract {
    interface View {
        void showProgress(String msg);

        void hideProgress();

        void onSuccessGetData(String title, int latestIssueID);

        void onErrorGetData(String msg);
    }

    interface AdapterData {
        void authorData(List<ResponseAuthor> dataAuthor);
    }

    interface Presenter {
        void getDataIssue(String journalID ,String submissionID);
    }

    interface ViewChild {
        void showProgress(String msg);

        void hideProgress();

        void onSuccessGetDataChild(List<ResponseCurrent> dataCurrentDetails, int requestCode);

        void onErrorGetData(String msg);
    }

    interface PresenterChild {
        void fillingData(int latestIssueID);

        void getData(int idPart, int requestCode);
    }

    interface PresenterAuthor {
        List<ResponseAuthor> getData(String submissionID);
    }
}
