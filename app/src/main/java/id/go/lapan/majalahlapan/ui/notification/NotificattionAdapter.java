package id.go.lapan.majalahlapan.ui.notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.notification.ResponseNotification;

import java.util.List;

class NotificattionAdapter extends RecyclerView.Adapter<NotificattionAdapter.NotificationViewHolder> {


    private final Context context;
    private final List<ResponseNotification> data;

    NotificattionAdapter(List<ResponseNotification> dataNotification, Context context) {
        data = dataNotification;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NotificationViewHolder(LayoutInflater.from(context).inflate(R.layout.content_notification, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int i) {
        notificationViewHolder.tvNotifDate.setText(data.get(i).getDatePublished());
        notificationViewHolder.tvNotifTitle.setText(data.get(i).getNotif());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static
    class NotificationViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_notif_title)
        TextView tvNotifTitle;
        @BindView(R.id.tv_notif_date)
        TextView tvNotifDate;

        NotificationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }
}
