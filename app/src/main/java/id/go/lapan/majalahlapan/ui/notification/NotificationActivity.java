package id.go.lapan.majalahlapan.ui.notification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.go.lapan.majalahlapan.MainActivity;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.notification.ResponseNotification;

import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationContract.View{

    @BindView(R.id.notif_rv)
    RecyclerView notifRv;
    @BindView(R.id.main_container)
    LinearLayout main_container;
    NotificattionAdapter adapter;
    String jurnalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
        jurnalId = preferences.getString("jurnal", null);

        NotificationPresenter notificationPresenter = new NotificationPresenter(NotificationActivity.this);
        notificationPresenter.getNotification(jurnalId);
    }

    @Override
    public void onNotificationFound(List<ResponseNotification> dataNotification) {
        notifRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificattionAdapter(dataNotification, NotificationActivity.this);
        notifRv.setAdapter(adapter);
    }

    @Override
    public void onErrorData(String msg) {
        Snackbar.make(main_container,msg,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                finish();
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
