package id.go.lapan.majalahlapan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import id.go.lapan.majalahlapan.data.remote.Api;
import id.go.lapan.majalahlapan.data.remote.ConfigRetrofit;
import id.go.lapan.majalahlapan.data.remote.ServiceGenerator;
import id.go.lapan.majalahlapan.model.archive.ResponseArchives;
import id.go.lapan.majalahlapan.model.last_issue.ResponseLastIssue;
import id.go.lapan.majalahlapan.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView tvJurnalId3, tvJurnalId4;
    String issueId;
    SharedPreferences preferencesIssue;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editorIssue;
    RelativeLayout main_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvJurnalId3 = (TextView) findViewById(R.id.tv_jurnal_id_3);
        main_container = (RelativeLayout) findViewById(R.id.main_container);
        tvJurnalId4 = (TextView) findViewById(R.id.tv_jurnal_id_4);

        preferencesIssue = getSharedPreferences("temps", getApplicationContext().MODE_PRIVATE);
        preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);

        editor = preferences.edit();
        editorIssue = preferencesIssue.edit();

        tvJurnalId3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("jurnal", "3");
                editor.commit();
                getLastDataIssue("3");


            }
        });
        tvJurnalId4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("jurnal", "4");
                editor.commit();
                getLastDataIssue("4");


            }
        });



    }

    private void getLastDataIssue(String jurnalId) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Load Data");
        progressDialog.show();
        Api api = ServiceGenerator.createService(Api.class, Constant.username,Constant.pass);
        api.requestLastissueId(jurnalId).enqueue(new Callback<List<ResponseLastIssue>>() {
            @Override
            public void onResponse(Call<List<ResponseLastIssue>> call, Response<List<ResponseLastIssue>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        issueId = response.body().get(0).getIssueId();

                        progressDialog.dismiss();

                        editorIssue.putString("issueId", issueId);
                        editorIssue.commit();



                        if (issueId != null) {
                            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        Snackbar.make(main_container,"Belum Dapat Issue Id, periksa jaringan",Snackbar.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResponseLastIssue>> call, Throwable t) {

                progressDialog.dismiss();
                Snackbar.make(main_container,"Belum Dapat Issue Id, periksa jaringan",Snackbar.LENGTH_SHORT).show();

            }
        });


    }
}
