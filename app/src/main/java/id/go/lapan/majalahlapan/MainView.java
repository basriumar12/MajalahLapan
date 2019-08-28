package id.go.lapan.majalahlapan;

public interface MainView {
    interface View {
        void onSuccessGetData(int sizeIssue);
        void onErrorData( String msg);
    }

    interface Presenter {
        void getDataIssue(String journalID);
    }
}