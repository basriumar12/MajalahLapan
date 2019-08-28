package id.go.lapan.majalahlapan.ui.archive;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.utils.ArchiveClickListener;

// Class yang digunakan untuk menginisialisasi view dengan variable untuk kemudian digunakan untuk menempelkan data ke view

class ArchiveViewHolder extends RecyclerView.ViewHolder {

    private ArchiveClickListener archiveClickListener;
    TextView tvArchiveYearWithoutChild, tvArchiveYearWithChild;
    RelativeLayout btnUpDown;
    RecyclerView rvListChild;
    ExpandableLinearLayout expandableLinearLayout;

    void setArchiveClickListener(ArchiveClickListener archiveClickListener) {
        this.archiveClickListener = archiveClickListener;
    }

    ArchiveViewHolder(@NonNull View itemView, boolean isExpandable) {
        super(itemView);
        if (isExpandable) {
            rvListChild = itemView.findViewById(R.id.list_archive_child);
            tvArchiveYearWithChild = itemView.findViewById(R.id.tv_archive_year);
            btnUpDown = itemView.findViewById(R.id.btn_updown);
            expandableLinearLayout = itemView.findViewById(R.id.expandable_archive);
        } else {
            tvArchiveYearWithoutChild = itemView.findViewById(R.id.tv_archive_year_without);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                archiveClickListener.onClick(v, getAdapterPosition());
            }
        });
    }
}