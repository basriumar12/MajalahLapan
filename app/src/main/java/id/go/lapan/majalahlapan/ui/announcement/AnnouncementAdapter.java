package id.go.lapan.majalahlapan.ui.announcement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.announcement.ResponseAnnouncement;
import id.go.lapan.majalahlapan.ui.announcement.annoucementdetail.AnnoucementDetail;

import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.ANNOUNCEMENT_DETAIL;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnoucementViewHolder> {

    private List<ResponseAnnouncement> data;
    private Context context;

    AnnouncementAdapter(List<ResponseAnnouncement> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public AnnoucementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Menentukan layout untuk Announcement Adapter
        return new AnnoucementViewHolder(LayoutInflater.from(context).inflate(R.layout.annoucement_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.AnnoucementViewHolder annoucementViewHolder, final int i) {
        annoucementViewHolder.tvTitle.setText(data.get(i).getTitle());
        annoucementViewHolder.tvDescSort.setText(data.get(i).getDescriptionShort());
        annoucementViewHolder.tvDate.setText(Html.fromHtml(data.get(i).getDatePosted()));
        annoucementViewHolder.init(data.get(i));
    }

    @Override
    public int getItemCount() {
        // Mengembalikan panjangnnya data
        return data.size();
    }

    class AnnoucementViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvDescSort;
        Button btnView;

        AnnoucementViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_annoucmenet_title);
            tvDescSort = itemView.findViewById(R.id.tv_desc_sort);
            tvDate = itemView.findViewById(R.id.tv_annoucement_date);
            btnView = itemView.findViewById(R.id.btn_annoucement_detail);
        }

        // Menginisialisasi pada saat button view diklik maka akan menuju AnnouncementDetail Activity
        void init(final ResponseAnnouncement responseAnnouncement) {
            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.btn_annoucement_detail) {
                        context.startActivity(new Intent(context, AnnoucementDetail.class)
                                .putExtra(ANNOUNCEMENT_DETAIL, responseAnnouncement)
                        );
                    }
                }
            });
        }

    }
}