package id.go.lapan.majalahlapan.ui.viewdownload;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.system.ErrnoException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import id.go.lapan.majalahlapan.MainActivity;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.utils.CheckForSDCard;
import id.go.lapan.majalahlapan.utils.DownloadTask;
import id.go.lapan.majalahlapan.utils.Utils;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDownloadActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button btnViewdownloadDownload;
    public static String CONST_URL = "url";
    String url;
    ProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdownload);
        webView = findViewById(R.id.viewdownload_webview);
        btnViewdownloadDownload = findViewById(R.id.btn_viewdownload_download);

        Toolbar toolbar = findViewById(R.id.viewdownload_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // Mengaktifkan Javascript diwebview
        webView.getSettings().setJavaScriptEnabled(true);
        // Membuat variable link url pdf
        String pdf = getIntent().getStringExtra(CONST_URL);
        //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
        String getUrl = "http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf;

//        webView.setWebViewClient(new WebViewClient());

        url = getIntent().getStringExtra(CONST_URL);

        startWebView(getUrl);

        // Pada saat button download dikilik statement berikutnya dilanjutkan pada fungsi onclick
        btnViewdownloadDownload.setOnClickListener(this);
    }

    private void startWebView(String url) {
//
//        WebSettings settings = webView.getSettings();
//
//        settings.setJavaScriptEnabled(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
//
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(ViewDownloadActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(ViewDownloadActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.view_download));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_viewdownload_download:

                String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                if (EasyPermissions.hasPermissions(this, perms)) {

                    // Determine where to save your file



                        if (isConnectingToInternet()) {

                            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);

                                request.setAllowedNetworkTypes(
                                        DownloadManager.Request.NETWORK_WIFI
                                                | DownloadManager.Request.NETWORK_MOBILE)
                                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                Long refrence = downloadManager.enqueue(request);

                            //download jika ada sd card
                            new DownloadTask(ViewDownloadActivity.this, btnViewdownloadDownload, url);
                            openDownloadedFolder();
                        } else {
                            Toast.makeText(this, "Internet tidak aktiv", Toast.LENGTH_SHORT).show();
                        }






                } else {
                    // tampilkan permission request saat belum mendapat permission dari user
                    EasyPermissions.requestPermissions(this, "This application need your permission to access save data.", 991, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    EasyPermissions.requestPermissions(this, "This application need your permission to access save data.", 992, Manifest.permission.WRITE_EXTERNAL_STORAGE);


                }

                break;


        }


    }


    //Open downloaded folder
    private void openDownloadedFolder() {
        //First check if SD Card is present or not
        if (new CheckForSDCard().isSDCardPresent()) {

            //Get Download Directory File
            File apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + Utils.downloadDirectory);

            //If file is not present then display Toast
            if (!apkStorage.exists())
                Toast.makeText(ViewDownloadActivity.this, "Sekarang tidak ada sdcard di hp anda, periksa notifkasi untuk akses file pdf", Toast.LENGTH_SHORT).show();

            else {

                //If directory is present Open Folder

                /** Note: Directory will open only if there is a app to open directory like File Manager, etc.  **/

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/" + Utils.downloadDirectory);
                intent.setDataAndType(uri, "file/*");
                startActivity(Intent.createChooser(intent, "Open Download Folder"));
            }

        } else
            Toast.makeText(ViewDownloadActivity.this, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

    }


    //Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
