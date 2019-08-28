package id.go.lapan.majalahlapan.ui.current;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.ui.current.articles.ArticlesPresenter;
import id.go.lapan.majalahlapan.ui.current.backpages.BackpagesPresenter;
import id.go.lapan.majalahlapan.ui.current.frontpages.FrontpagesPresenter;
import id.go.lapan.majalahlapan.ui.current.fullpaper.FullPaperPresenter;

import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment implements CurrentContract.View, CurrentContract.ViewChild {
    //////

    @BindView(R.id.archive_tv_journal_title)
    TextView archiveTvJournalTitle;
    @BindView(R.id.archive_tv_frontpage)
    TextView archiveTvFrontpage;
    @BindView(R.id.archive_rv_frontpage)
    RecyclerView archiveRvFrontpage;
    @BindView(R.id.archive_tv_articles)
    TextView archiveTvArticles;
    @BindView(R.id.current_rv_articles)
    RecyclerView currentRvArticles;
    @BindView(R.id.archive_tv_backpages)
    TextView archiveTvBackpages;
    @BindView(R.id.archive_rv_backpages)
    RecyclerView archiveRvBackpages;
    @BindView(R.id.archive_rv_fullpaper)
    RecyclerView archiveRvFullpaper;
    Unbinder unbinder;

    FragmentManager fm;
    DrawerLayout drawerLayout;

    CurrentPresenter presenter = new CurrentPresenter(this);

    ArticlesPresenter articlesPresenter = new ArticlesPresenter(CurrentFragment.this);
    BackpagesPresenter backpagesPresenter = new BackpagesPresenter(this);
    FrontpagesPresenter frontpagesPresenter = new FrontpagesPresenter(this);
    FullPaperPresenter fullpaperPresenter = new FullPaperPresenter(this);

    CurrentAdapterChild adapterFullPaper;
    String journalID = "";
    String issueID = "";


    // note tanggal 18 agustus
    //parsing data dari halaman archive ke current kirim issue id
    // bikin halaman home, tampilkan jurnal id dummy, klik jurnal dan simpan id nya untuk dipakai di semua akses
    // current, archve, jurnal dan notif
    // dokumentasi pdf + programm
    // layout navigation
    // layout footer
    // gambar di archive di hapus


    //logic notif
    //buka sesuai tanggal sekarang,
    // api berdasarkan tanggal,
    // api notif berdasarkan jurnal id,
    // api yang ambil last issue id berdasarkan endpoint jurnalid


    public static String ISSUE_EXTRAS = "extra submission";
    public static String JOURNAL_EXTRAS = "journal submission";
    @BindView(R.id.tv_current_error)
    TextView tvCurrentError;
    @BindView(R.id.archive_tv_full_paper)
    TextView archiveTvFullPaper;
    @BindView(R.id.current_main)
    NestedScrollView currentMain;
    SharedPreferences preferences;
    SharedPreferences preferencesIssue;
    @BindView(R.id.progress_bsr_current)
    ProgressBar progressBsrCurrent;
    @BindView(R.id.main_container)
    RelativeLayout main_container;

    public CurrentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle(getString(R.string.menu_current));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        fm = getFragmentManager();
        drawerLayout = view.findViewById(R.id.drawer_layout);
        unbinder = ButterKnife.bind(this, view);

        tvCurrentError.setVisibility(View.INVISIBLE);
        currentMain.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preferences = this.getActivity().getSharedPreferences("temp", Context.MODE_PRIVATE);
        preferencesIssue = this.getActivity().getSharedPreferences("temps", Context.MODE_PRIVATE);

        journalID = preferences.getString("jurnal", null);
        issueID = preferencesIssue.getString("issueId", null);

        Log.e("Tag", "data jurnal " + journalID);
        Log.e("Tag", "data  issue " + issueID);

        if (journalID != null && issueID != null) {
            presenter.getDataIssue(journalID, issueID);
        } else {
            currentMain.setVisibility(View.INVISIBLE);
            tvCurrentError.setVisibility(View.VISIBLE);
            tvCurrentError.setText("Error JournalID");

            currentMain.setVisibility(View.INVISIBLE);
            tvCurrentError.setVisibility(View.VISIBLE);
            tvCurrentError.setText("Error IssueID");
        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress(String msg) {
        currentMain.setVisibility(View.INVISIBLE);
        progressBsrCurrent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        currentMain.setVisibility(View.VISIBLE);
        progressBsrCurrent.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessGetData(String title, int latestIssueID) {
        archiveTvJournalTitle.setText(title);
        frontpagesPresenter.getData(latestIssueID, CONST_FRONTPAGES);
        articlesPresenter.getData(latestIssueID, CONST_ARTICLE);
        backpagesPresenter.getData(latestIssueID, CONST_BACKPAGES);
        fullpaperPresenter.getData(latestIssueID, CONST_FULLPAPER);
    }

    @Override
    public void onSuccessGetDataChild(List<ResponseCurrent> dataCurrentDetails, int requestCode) {
        switch (requestCode) {
            case CONST_FRONTPAGES:
                CurrentAdapterChild adapterFrontPage = new CurrentAdapterChild(dataCurrentDetails, getContext(), CONST_FRONTPAGES);
                archiveRvFrontpage.setHasFixedSize(true);
                archiveRvFrontpage.setLayoutManager(new LinearLayoutManager(getContext()));
                archiveRvFrontpage.setAdapter(adapterFrontPage);
                break;
            case CONST_ARTICLE:
                CurrentAdapterChild adapterArticles = new CurrentAdapterChild(dataCurrentDetails, getContext(), CONST_ARTICLE);
                currentRvArticles.setHasFixedSize(true);
                currentRvArticles.setLayoutManager(new LinearLayoutManager(getContext()));
                currentRvArticles.setAdapter(adapterArticles);
                break;
            case CONST_BACKPAGES:
                CurrentAdapterChild adapterBackPage = new CurrentAdapterChild(dataCurrentDetails, getContext(), CONST_BACKPAGES);
                archiveRvBackpages.setHasFixedSize(true);
                archiveRvBackpages.setLayoutManager(new LinearLayoutManager(getContext()));
                archiveRvBackpages.setAdapter(adapterBackPage);
                break;
            case CONST_FULLPAPER:
                adapterFullPaper = new CurrentAdapterChild(dataCurrentDetails, getContext(), CONST_FULLPAPER);
                archiveRvFullpaper.setHasFixedSize(true);
                archiveRvFullpaper.setLayoutManager(new LinearLayoutManager(getContext()));
                archiveRvFullpaper.setAdapter(adapterFullPaper);
                break;
        }
    }

    @Override
    public void onErrorGetData(String msg) {
        tvCurrentError.setVisibility(View.VISIBLE);
        currentMain.setVisibility(View.INVISIBLE);
        tvCurrentError.setText(msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Snackbar.make(main_container,msg,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
