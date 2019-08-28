package id.go.lapan.majalahlapan.ui.archive;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.Utils;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.data.array.archive.DataArchive;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import id.go.lapan.majalahlapan.ui.archive.child.ArchivePresenterChild;
import id.go.lapan.majalahlapan.utils.ArchiveClickListener;

import java.util.List;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveViewHolder> {

    private final List<DataArchive> dataArchives;
    private final List<ResponseArchive> data;
    private final Context context;
    private ArchivePresenterChild presenter;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    ArchiveAdapter(Context c, List<ResponseArchive> data, List<DataArchive> dataArchiveChildren, ArchivePresenterChild presenter) {
        this.dataArchives = dataArchiveChildren;
        this.context = c;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataArchives.size() > 0)
            return 1;
        else
            return 0;
    }

    @NonNull
    @Override
    public ArchiveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Jika nilai 0 maka akan diberikan layout tanpa child, sedangkan jika nilai 1 maka akan diberikan layout dengan child
        if (i == 0) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.archive_without_child, viewGroup, false);
            return new ArchiveViewHolder(itemView, false);
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.archive_with_child, viewGroup, false);
            return new ArchiveViewHolder(itemView, true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveViewHolder archvieViewHolder, @SuppressLint("RecyclerView") final int i) {
        // Set Backgroundcolor
        setBackgroundColor(archvieViewHolder, i);
        switch (archvieViewHolder.getItemViewType()) {
            case 0:
//                memberikan nilai pada view apabila viewtype = 0
                archvieViewHolder.setIsRecyclable(false);
                archvieViewHolder.tvArchiveYearWithoutChild.setText(dataArchives.get(i).getYear());
                archvieViewHolder.setArchiveClickListener(new ArchiveClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(context, dataArchives.get(i).getYear(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1:
//                memberikan nilai pada view apabila viewtype = 1
                final ArchiveViewHolder viewHolderr = archvieViewHolder;
                viewHolderr.setIsRecyclable(false);
                viewHolderr.expandableLinearLayout.setInRecyclerView(true);
                viewHolderr.tvArchiveYearWithChild.setText(dataArchives.get(i).getYear());
                viewHolderr.expandableLinearLayout.setExpanded(expandState.get(i));

                // Melakukan setExpandable
                viewHolderr.expandableLinearLayout.setListener(new ExpandableLayoutListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }

                    // Fungsi handle pada saat expanding list maka button akan rotate 180 derajat
                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolderr.btnUpDown, 0f, 180f).start();
                        expandState.put(i, true);
                    }

                    // Fungsi handle pada saat menutup expand list maka button akan rotate 180 derajat ke 360 derajat
                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolderr.btnUpDown, 180f, 360f).start();
                        expandState.put(i, false);
                    }

                    @Override
                    public void onOpened() {

                    }

                    @Override
                    public void onClosed() {

                    }

                });

                // Memanggil fungsi getDataListYear untuk mendapatkan dataArchives detail Archive
                presenter.getDataListYear(data,dataArchives, dataArchives.get(i).getYear(), viewHolderr.rvListChild, expandState);
                viewHolderr.btnUpDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolderr.expandableLinearLayout.toggle();
                    }
                });
                viewHolderr.setArchiveClickListener(new ArchiveClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(context, dataArchives.get(position).getYear(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    // Fungsi setBackground color digunakan
    // Pada kondisi dimana index genap akan diberikan warna abu-abu
    // Sedangkan index ganjil akan diberikan warna kuning

    private void setBackgroundColor(ArchiveViewHolder archvieViewHolder, int i) {
        int index = i % 2;
        if (index == 0) {
            archvieViewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.bg_yellow_main));
        } else {
            archvieViewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.bg_dark_main));
        }
    }

    @Override
    public int getItemCount() {
        return dataArchives.size();
    }

    // Fungsi yang digunakan untuk melakukan rotate object button
    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}
