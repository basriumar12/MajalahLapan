package id.go.lapan.majalahlapan.data.array.archive;

import android.os.Parcel;
import android.os.Parcelable;

// Model Data Detail Archive
// id, year, title, image, description

public class DataArchiveChild implements Parcelable{
    private int id;
    private String year;
    private String title;
    private String imgUrl;
    private String description;

    public DataArchiveChild(int id, String year, String title, String imgUrl, String description) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }


    public String getTitle() {
        return title;
    }


    public String getImgUrl() {
        return imgUrl;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.year);
        dest.writeString(this.title);
        dest.writeString(this.imgUrl);
        dest.writeString(this.description);
    }

    private DataArchiveChild(Parcel in) {
        this.id = in.readInt();
        this.year = in.readString();
        this.title = in.readString();
        this.imgUrl = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<DataArchiveChild> CREATOR = new Parcelable.Creator<DataArchiveChild>() {
        @Override
        public DataArchiveChild createFromParcel(Parcel source) {
            return new DataArchiveChild(source);
        }

        @Override
        public DataArchiveChild[] newArray(int size) {
            return new DataArchiveChild[size];
        }
    };
}
