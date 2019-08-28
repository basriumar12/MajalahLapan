package id.go.lapan.majalahlapan.ui.archive;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.Unbinder;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.data.array.archive.DataArchive;
import id.go.lapan.majalahlapan.data.array.archive.DataArchiveChild;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import id.go.lapan.majalahlapan.model.archive.ResponseArchives;
import id.go.lapan.majalahlapan.ui.archive.child.ArchiveChildAdapter;
import id.go.lapan.majalahlapan.ui.archive.child.ArchivePresenterChild;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment implements ArchiveContract.View {

    ArchivePresenter presenter = new ArchivePresenter(this);
    ArchivePresenterChild presenterChild = new ArchivePresenterChild(this);
    Unbinder unbinder;
    ArchiveAdapter archiveAdapter;
    ArchiveAdapters archiveAdapters;
    ArchiveChildAdapter archiveChildAdapter;
    RecyclerView listArchive;
    ProgressBar pb;
    String journalID;
    RelativeLayout main_container;


    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle(getString(R.string.menu_archive));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View archiveView = inflater.inflate(R.layout.fragment_archive, container, false);
        listArchive = archiveView.findViewById(R.id.list_archive);
        pb = archiveView.findViewById(R.id.progress_bsr_archive);
        main_container = archiveView.findViewById(R.id.main_container);
        SharedPreferences preferences= this.getActivity().getSharedPreferences("temp", Context.MODE_PRIVATE);
        journalID =preferences.getString("jurnal",null);
        if(journalID!=null) {
            //presenter.getDataListYear();
            presenter.getDataListYearById(journalID);
        }
        return archiveView;
    }

    @Override
    public void showProgress() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessGetData(List<ResponseArchive> dataArchives, List<DataArchive> dataYear) {
//        listArchive.setHasFixedSize(true);
//        listArchive.setLayoutManager(new LinearLayoutManager(getContext()));
//        listArchive.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
//        archiveAdapter = new ArchiveAdapter(getContext(), dataArchives, dataYear, presenterChild);
//        listArchive.setAdapter(archiveAdapter);
    }

    @Override
    public void onSuccessGetDatas(List<ResponseArchives> dataArchives) {
        listArchive.setHasFixedSize(true);
        listArchive.setLayoutManager(new LinearLayoutManager(getContext()));
        listArchive.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        archiveAdapters = new ArchiveAdapters(getContext(), dataArchives);
        listArchive.setAdapter(archiveAdapters);

    }

    @Override
    public void onSuccessGetDataChild(List<ResponseArchive> dataChild, RecyclerView rvListChild, SparseBooleanArray expandState) {
        expandState.clear();
        for (int i = 0; i < dataChild.size(); i++)
            expandState.append(i, false);
        rvListChild.setLayoutManager(new LinearLayoutManager(getContext()));
        archiveChildAdapter = new ArchiveChildAdapter(getContext(), dataChild, getFragmentManager());
        archiveChildAdapter.notifyDataSetChanged();
        rvListChild.setAdapter(archiveChildAdapter);
    }

    @Override
    public void onSuccessGetDataRemote(List<ResponseArchive> data) {
        listArchive.setHasFixedSize(true);
        listArchive.setLayoutManager(new LinearLayoutManager(getContext()));
        listArchive.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onErrorGetDat(String msg) {
 //       Toast.makeText(presenterChild, "", Toast.LENGTH_SHORT).show();
        Snackbar.make(main_container,msg,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
