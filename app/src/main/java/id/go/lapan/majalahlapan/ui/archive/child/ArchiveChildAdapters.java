package id.go.lapan.majalahlapan.ui.archive.child;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.archive.DataItem;
import id.go.lapan.majalahlapan.ui.current.CurrentActivity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArchiveChildAdapters extends RecyclerView.Adapter<ArchiveChildAdapters.ArchiveChildHolder> {

    private final List<DataItem> data;
    private final Context context;

    public ArchiveChildAdapters(Context context, List<DataItem> data) {
        this.data = data;
        this.context = context;

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

        Log.e("TAG", "data child archice" + new Gson().toJson(data));
        archiveChildHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = context.getSharedPreferences("tempss", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("issueIdCurrent", data.get(i).getIssueId());
                editor.commit();
                context.startActivity(new Intent(context, CurrentActivity.class));
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
//            tvArchiveDescription = itemView.findViewById(R.id.tv_archive_child_desc);
        }
    }
}
