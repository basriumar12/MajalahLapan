package id.go.lapan.majalahlapan.data.array.announcement;

import android.os.Parcel;
import android.os.Parcelable;

// MODEL DATA ANNOUNCEMENT (Menyimpan struktur data Announcement)
// id , Title, Date, Description

public class DataAnnoucement implements Parcelable {
    private int id;
    private String titleAnnoucmenet;
    private String dateAnnoucmenet;
    private String descAnnoucmenet;

    public DataAnnoucement(int id, String titleAnnoucmenet, String dateAnnoucmenet, String descAnnoucmenet) {
        this.id = id;
        this.titleAnnoucmenet = titleAnnoucmenet;
        this.dateAnnoucmenet = dateAnnoucmenet;
        this.descAnnoucmenet = descAnnoucmenet;
    }

    public int getId() {
        return id;
    }


    public String getTitleAnnoucmenet() {
        return titleAnnoucmenet;
    }


    public String getDateAnnoucmenet() {
        return dateAnnoucmenet;
    }


    public String getDescAnnoucmenet() {
        return descAnnoucmenet;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.titleAnnoucmenet);
        dest.writeString(this.dateAnnoucmenet);
        dest.writeString(this.descAnnoucmenet);
    }

    private DataAnnoucement(Parcel in) {
        this.id = in.readInt();
        this.titleAnnoucmenet = in.readString();
        this.dateAnnoucmenet = in.readString();
        this.descAnnoucmenet = in.readString();
    }

    public static final Parcelable.Creator<DataAnnoucement> CREATOR = new Parcelable.Creator<DataAnnoucement>() {
        @Override
        public DataAnnoucement createFromParcel(Parcel source) {
            return new DataAnnoucement(source);
        }

        @Override
        public DataAnnoucement[] newArray(int size) {
            return new DataAnnoucement[size];
        }
    };
}
