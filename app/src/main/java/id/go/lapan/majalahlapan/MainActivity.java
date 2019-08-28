package id.go.lapan.majalahlapan;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.go.lapan.majalahlapan.model.notification.ResponseNotification;
import id.go.lapan.majalahlapan.ui.announcement.AnnouncementFragment;
import id.go.lapan.majalahlapan.ui.archive.ArchiveFragment;
import id.go.lapan.majalahlapan.ui.current.CurrentFragment;
import id.go.lapan.majalahlapan.ui.home.HomeFragment;
import id.go.lapan.majalahlapan.ui.notification.NotificationActivity;
import id.go.lapan.majalahlapan.ui.notification.NotificationContract;
import id.go.lapan.majalahlapan.ui.notification.NotificationPresenter;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NotificationContract.View, MainView.View {

    // Object NavigationView : handle click dan menempelkan icon navigasi
    NavigationView mNavView;
    // Object Class Fragment : handle fragment yang sedang berjalan (old) dan terpilih (choosed)
    public static Fragment choosedFragment;
    Fragment oldFragment;
    // Object FragmentManager : handle transaksi pada saat menambahkan, mengganti dan menghapus fragment
    FragmentManager fm;

    Bundle savedInstanceState;
    String jurnalId;
    String issueId;
    @BindView(R.id.nav_home)
    Button navHome;
    @BindView(R.id.nav_current)
    Button navCurrent;
    @BindView(R.id.main_container)
    ConstraintLayout main_container;
    @BindView(R.id.nav_archive)
    Button navArchive;
    @BindView(R.id.nav_announcement)
    Button navAnnouncement;
    TextView journalSizeTxt;
    int journalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar currentTime = Calendar.getInstance();
        int day = currentTime.get(Calendar.DAY_OF_MONTH);
        int month = currentTime.get(Calendar.MONTH) + 1;
        int year = currentTime.get(Calendar.YEAR);
        String mMonth = String.format("%02d", month);

        String today = (year + "-" + mMonth + "-" + day);

        this.savedInstanceState = savedInstanceState;
        //this.savedInstanceState = savedInstanceState;
        // Inisialisasi FragmentManager
        fm = getSupportFragmentManager();
       getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();


       // checkState();
        createNotificationChannel();
        SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
        jurnalId = preferences.getString("jurnal", null);
        SharedPreferences preferencess = getSharedPreferences("temps", getApplicationContext().MODE_PRIVATE);
        issueId = preferencess.getString("issueId", null);

        Log.e("Tag", " data jurnal " + jurnalId);
        Log.e("Tag", " data issue id " + issueId);


        // Inisialisasi & Menabahkan Layout untuk Navigasi Sisi layar bagian kiri (NavigasiDrawer)
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        mNavView = findViewById(R.id.nav_view);

        // Melakukan perulangan untuk menempelkan layout navigasi pada drawer
//        for (int index = 0; index <= 4; index++) {
//            mNavView.getMenu().getItem(index).setActionView(R.layout.menu_image);
//        }

        // Class ActionBarDrawerToogle digunakan untuk menghubungkan antara ActionBar dengan DrawerLayout
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        NotificationPresenter notificationPresenter = new NotificationPresenter(MainActivity.this);
        notificationPresenter.getNotification(today);
    }

    private void checkState() {

        if (savedInstanceState != null) {
            choosedFragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragment");
        } else {
            choosedFragment = new HomeFragment();
            fm.beginTransaction().add(R.id.main_container, choosedFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
        jurnalId = preferences.getString("jurnal", null);
        MainPresenter presenter = new MainPresenter(this);
        presenter.getDataIssue(jurnalId);

        final MenuItem menuItem = menu.findItem(R.id.action_notifications);

        View actionView = MenuItemCompat.getActionView(menuItem);
        journalSizeTxt = (TextView) actionView.findViewById(R.id.notification_badge);
        journalSizeTxt.setVisibility(View.GONE);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    private void setupBadge() {
        if (journalSize == 0) {
            if (journalSizeTxt.getVisibility() != View.GONE) {
                journalSizeTxt.setVisibility(View.GONE);
            }
        } else {
            journalSizeTxt.setText(String.valueOf(Math.min(journalSize, 99)));
            if (journalSizeTxt.getVisibility() != View.VISIBLE) {
                journalSizeTxt.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_home:
//                oldFragment = fm.getFragments().get(0);
//                choosedFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

                break;
            case R.id.nav_current:
//                oldFragment = fm.getFragments().get(0);
//                choosedFragment = new CurrentFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new CurrentFragment()).commit();

                break;
            case R.id.nav_archive:
//                oldFragment = fm.getFragments().get(0);
//                choosedFragment = new ArchiveFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ArchiveFragment()).commit();

                break;
            case R.id.nav_announcement:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new AnnouncementFragment()).commit();

                break;

        }
        //fm.beginTransaction().remove(oldFragment).add(R.id.main_container, choosedFragment).addToBackStack(choosedFragment.toString()).commit();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_notifications) {
            startActivity(new Intent(this, NotificationActivity.class));
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private PendingIntent prepareIntent() {
        Intent snoozeIntent = new Intent(this, NotificationActivity.class);
        snoozeIntent.setAction("snooze_action");
        snoozeIntent.putExtra("channelId", 0);
        return PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       // getSupportFragmentManager().putFragment(savedInstanceState, "myFragment", choosedFragment);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channelId";
            String description = "channelId";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channelId", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Fungsi untuk menampilkan notifikasi
    private NotificationCompat.Builder showNotification(List<ResponseNotification> data) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        return new NotificationCompat.Builder(this, "channelId")
                .setContentTitle(data.get(0).getNotif())
                .setContentText(data.get(0).getNotif())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data.get(0).getNotif()))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(prepareIntent())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(android.R.drawable.ic_notification_clear_all, "Snooze", prepareIntent());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onNotificationFound(List<ResponseNotification> dataNotification) {
        Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (dataNotification != null) {
            for (int index = 0; index < dataNotification.size(); index++) {
                notificationManager.notify(index, showNotification(dataNotification).build());
            }
        }
    }

    @OnClick({R.id.nav_home, R.id.nav_current, R.id.nav_archive, R.id.nav_announcement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nav_home:
                oldFragment = fm.getFragments().get(0);
                choosedFragment = new HomeFragment();
                fm.popBackStack();
                break;
            case R.id.nav_current:
                oldFragment = fm.getFragments().get(0);
                choosedFragment = new CurrentFragment();
                break;
            case R.id.nav_archive:
                oldFragment = fm.getFragments().get(0);
                choosedFragment = new ArchiveFragment();
                break;
            case R.id.nav_announcement:
                oldFragment = fm.getFragments().get(0);
                choosedFragment = new AnnouncementFragment();
                break;
        }
        fm.beginTransaction().remove(oldFragment).add(R.id.main_container, choosedFragment).addToBackStack(choosedFragment.toString()).commit();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onSuccessGetData(int sizeIssue) {
        journalSize = sizeIssue;
        setupBadge();
    }

    @Override
    public void onErrorData(String msg) {
        Snackbar.make(main_container,msg,Snackbar.LENGTH_SHORT).show();

    }
}
