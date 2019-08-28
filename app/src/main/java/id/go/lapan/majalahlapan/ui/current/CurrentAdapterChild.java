package id.go.lapan.majalahlapan.ui.current;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import id.go.lapan.majalahlapan.R;
import id.go.lapan.majalahlapan.model.current.ResponseAuthor;
import id.go.lapan.majalahlapan.model.current.ResponseCurrent;
import id.go.lapan.majalahlapan.ui.current.author.AuthorPresenter;
import id.go.lapan.majalahlapan.ui.viewdownload.ViewDownloadActivity;

import java.util.List;

import static id.go.lapan.majalahlapan.utils.DataConstant.*;

public class CurrentAdapterChild extends RecyclerView.Adapter<CurrentAdapterChild.CurrentViewHolderChild> {

    private List<ResponseCurrent> dataCurrentDetails;
    private Context context;
    private int requestCode;
    private String url;
    private AuthorPresenter presenter = new AuthorPresenter();
    List<ResponseAuthor> dataAuthor;


    CurrentAdapterChild(List<ResponseCurrent> dataCurrentDetails, Context context, int requestCode) {
        this.dataCurrentDetails = dataCurrentDetails;
        this.context = context;
        this.requestCode = requestCode;
    }

    @NonNull
    @Override
    public CurrentViewHolderChild onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CurrentViewHolderChild(LayoutInflater.from(context).inflate(R.layout.current_main, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CurrentViewHolderChild currentViewHolderChild, int i) {
        setBackgroundColor(currentViewHolderChild, i);
        url = dataCurrentDetails.get(i).getFiles();
//        currentViewHolderChild.tvTitle.setText(dataCurrentDetails.get(i).getSettingValue());
//        currentViewHolderChild.btnCurrentDownload.setOnClickListener(this);
        currentViewHolderChild.initData(dataCurrentDetails, dataAuthor, i);
    }

    // Melakukan setcolor berdasarkan requestcode
    // Melakukan Set backgroundcolor dengan ketentuan apabila data genap maka akan mendapatkan warna putih jika tidak akan mendapat kan warna biru
    private void setBackgroundColor(CurrentViewHolderChild view, int i) {
        int index = i % 2;
        switch (requestCode) {
            case CONST_FRONTPAGES:
                view.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case CONST_ARTICLE:
                if (index == 0) {
                    view.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                } else if (index == 1) {
                    view.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                }
                break;
            case CONST_BACKPAGES:
                view.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataCurrentDetails.size();
    }


    // Class yang digunakan untuk menginisialisasi view dengan variable
    class CurrentViewHolderChild extends RecyclerView.ViewHolder {
        TextView tvTitle, tvEditors, tvPages;
        Button btnCurrentDownload;

        CurrentViewHolderChild(@NonNull View itemView) {
            super(itemView);
            tvEditors = itemView.findViewById(R.id.tv_current_editors);
            tvPages = itemView.findViewById(R.id.tv_current_pages);
            tvTitle = itemView.findViewById(R.id.tv_current_title_part);
            btnCurrentDownload = itemView.findViewById(R.id.btn_current_viewdownload);
        }

        void initData(List<ResponseCurrent> dataCurrent, List<ResponseAuthor> dataAuthor, int i) {
            tvTitle.setText(dataCurrent.get(i).getSettingValue());
            if (dataCurrent.get(i).getAuthor() != null) {
                tvEditors.setText(dataCurrent.get(i).getAuthor().get(0).getName());
            }

            btnCurrentDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.btn_current_viewdownload) {
                        context.startActivity(new Intent(context, ViewDownloadActivity.class)
                                .putExtra(ViewDownloadActivity.CONST_URL, dataCurrent.get(i).getFiles()
                                        )
                        );
                    }
                }
            });
        }
    }
}
