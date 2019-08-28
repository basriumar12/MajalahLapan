package id.go.lapan.majalahlapan.ui.announcement;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.announcement.ResponseAnnouncement;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementFragment extends Fragment implements AnnouncementContract.View {


    @BindView(R.id.rv_announcement)
    RecyclerView rvAnnouncement;
    Unbinder unbinder;
    AnnouncementPresenter presenter = new AnnouncementPresenter(this);
    @BindView(R.id.progress_bar_announcement)
    ProgressBar progressBarAnnouncement;

    @BindView(R.id.tv_null)
    TextView tvNull;
    @BindView(R.id.main_container)
    RelativeLayout main_container;
    Unbinder unbinder1;
    ProgressDialog progressDialog;

    String journalID = "";
    public AnnouncementFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getActivity()).setTitle(getString(R.string.menu_announcement));
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Menentukan layout dari AnnouncementFragment
        View view = inflater.inflate(R.layout.fragment_announcement, container, false);
        unbinder1 = ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        tvNull.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences preferences= this.getActivity().getSharedPreferences("temp", Context.MODE_PRIVATE);
        journalID =preferences.getString("jurnal",null);

        if (journalID != null) {
            presenter.getDataAnnouncement(Integer.valueOf(journalID));
        }
    }



    @Override
    public void showProgress(String msg) {
        progressBarAnnouncement.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        // Akan ditambahkan pada saat koneksi api
        progressBarAnnouncement.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessGetData(List<ResponseAnnouncement> dataAnnouncement) {
        if (dataAnnouncement != null) {
            AnnouncementAdapter adapter = new AnnouncementAdapter(dataAnnouncement, getContext());
            rvAnnouncement.setLayoutManager(new LinearLayoutManager(getContext()));
            rvAnnouncement.setHasFixedSize(true);
            rvAnnouncement.setAdapter(adapter);
        } else {
            rvAnnouncement.setVisibility(View.GONE);
            tvNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onErrorGetData(String msg) {
        // Akan ditambahkan pada saat koneksi api
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        tvNull.setVisibility(View.VISIBLE);
        Snackbar.make(main_container,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
