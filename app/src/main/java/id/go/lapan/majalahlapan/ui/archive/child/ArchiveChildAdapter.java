package id.go.lapan.majalahlapan.ui.archive.child;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.data.array.archive.DataArchiveChild;
import id.go.lapan.majalahlapan.model.archive.ResponseArchive;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArchiveChildAdapter extends RecyclerView.Adapter<ArchiveChildAdapter.ArchiveChildHolder> {

    private final List<ResponseArchive> data;
    private final Context context;
    private final FragmentManager fm;
    private DataArchiveChild dataArchiveChild;

    public ArchiveChildAdapter(Context context, List<ResponseArchive> data, FragmentManager fragmentManager) {
        this.data = data;
        this.context = context;
        fm = fragmentManager;
    }

    @NotNull
    @Override
    public ArchiveChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ArchiveChildHolder(LayoutInflater.from(context).inflate(R.layout.archive_list_child, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveChildHolder archiveChildHolder, @SuppressLint("RecyclerView") final int i) {
        archiveChildHolder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        archiveChildHolder.tvArchiveTitle.setText(data.get(i).getTitle());
        archiveChildHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ResponseArchive dataIndex = data.get(i);
//                Bundle dataBundle = new Bundle();
//                dataArchiveChild = new DataArchiveChild(dataIndex.getIssueId(), dataIndex.getYear(), dataIndex.getTitle(), dataIndex.getImgUrl(), dataIndex.getDescription());
//                dataBundle.putParcelable(CONST_SERIALIZABLE, dataArchiveChild);
//                CurrentFragment currentFragment = new CurrentFragment();
//                currentFragment.setArguments(dataBundle);
//                fm.beginTransaction().remove(new ArchiveFragment()).replace(R.id.main_container, currentFragment, "any").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ArchiveChildHolder extends RecyclerView.ViewHolder {

        TextView tvArchiveTitle;

        ArchiveChildHolder(@NonNull View itemView) {
            super(itemView);
            tvArchiveTitle = itemView.findViewById(R.id.tv_archive_child_title);
        }
    }
}
