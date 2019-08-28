package id.go.lapan.majalahlapan.ui.announcement.annoucementdetail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.data.array.announcement.DataAnnoucement;
import id.go.lapan.majalahlapan.model.announcement.ResponseAnnouncement;

import java.util.Objects;

import static id.go.lapan.majalahlapan.utils.DataConstant.ANNOUNCEMENT_DETAIL;

// AnnoucementDetail ialah Class Activity untuk menampilkan detail dari announcement

public class AnnoucementDetail extends AppCompatActivity {

    ResponseAnnouncement dataAnnoucement;
    @BindView(R.id.tv_annoucement_detail_title)
    TextView tvAnnoucementDetailTitle;
    @BindView(R.id.tv_annoucement_detail_date)
    TextView tvAnnoucementDetailDate;
    @BindView(R.id.tv_annoucement_detail_desc)
    TextView tvAnnoucementDetailDesc;

    @Override
    protected void onStart() {
        super.onStart();
        setTitle(R.string.annoucement_detail);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoucement_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Mendapatkan data dari page Current
        dataAnnoucement = getIntent().getParcelableExtra(ANNOUNCEMENT_DETAIL);

        // Melakukan set-data pada layout dan menampilkan
        tvAnnoucementDetailTitle.setText(dataAnnoucement.getTitle());
        tvAnnoucementDetailDate.setText(dataAnnoucement.getDatePosted());
        tvAnnoucementDetailDesc.setText(Html.fromHtml(dataAnnoucement.getDescription()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pada saat back button diklik maka akan kembali ke page sebelumnya
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
