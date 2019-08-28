//package id.go.lapan.majalahlapan.ui.archive.child;
//
//import android.app.IntentService;
//import android.content.Intent;
//import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
//import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
//
//import java.io.IOException;
//import java.util.List;
//
//public class BackgroundTask extends IntentService {
//
//    /**
//     * Creates an IntentService.  Invoked by your subclass's constructor.
//     *
//     * @param name Used to name the worker thread, important only for debugging.
//     */
//    public BackgroundTask(String name) {
//        super("BackgroundTask");
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        try {
//            for (int index = 0; index < dataArchives.size(); index++) {
//                List<ResponseArchive> dataChild = ConfigRetrofit.service().requestDataChildArchive(dataArchives.get(index).getYear()).execute().body();
//                view.onSuccessGetDataChild(dataChild, rvListChild, expandState);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
