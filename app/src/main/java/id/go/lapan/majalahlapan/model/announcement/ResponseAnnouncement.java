package id.go.lapan.majalahlapan.model.announcement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ResponseAnnouncement implements Parcelable {

	@SerializedName("journal_id")
	private String journalId;

	@SerializedName("date_posted")
	private String datePosted;

	@SerializedName("type_id")
	private String typeId;

	@SerializedName("announcement_id")
	private String announcementId;

	@SerializedName("description")
	private String description;

	@SerializedName("date_expire")
	private Object dateExpire;

	@SerializedName("title")
	private String title;

	@SerializedName("descriptionShort")
	private String descriptionShort;

	private ResponseAnnouncement(Parcel in) {
		journalId = in.readString();
		datePosted = in.readString();
		typeId = in.readString();
		announcementId = in.readString();
		description = in.readString();
		title = in.readString();
		descriptionShort = in.readString();
	}

	public static final Creator<ResponseAnnouncement> CREATOR = new Creator<ResponseAnnouncement>() {
		@Override
		public ResponseAnnouncement createFromParcel(Parcel in) {
			return new ResponseAnnouncement(in);
		}

		@Override
		public ResponseAnnouncement[] newArray(int size) {
			return new ResponseAnnouncement[size];
		}
	};

	public void setJournalId(String journalId){
		this.journalId = journalId;
	}

	public String getJournalId(){
		return journalId;
	}

	public void setDatePosted(String datePosted){
		this.datePosted = datePosted;
	}

	public String getDatePosted(){
		return datePosted;
	}

	public void setTypeId(String typeId){
		this.typeId = typeId;
	}

	public String getTypeId(){
		return typeId;
	}

	public void setAnnouncementId(String announcementId){
		this.announcementId = announcementId;
	}

	public String getAnnouncementId(){
		return announcementId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDateExpire(Object dateExpire){
		this.dateExpire = dateExpire;
	}

	public Object getDateExpire(){
		return dateExpire;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDescriptionShort(String descriptionShort){
		this.descriptionShort = descriptionShort;
	}

	public String getDescriptionShort(){
		return descriptionShort;
	}

	@Override
 	public String toString(){
		return 
			"ResponseAnnouncement{" + 
			"journal_id = '" + journalId + '\'' + 
			",date_posted = '" + datePosted + '\'' + 
			",type_id = '" + typeId + '\'' + 
			",announcement_id = '" + announcementId + '\'' + 
			",description = '" + description + '\'' + 
			",date_expire = '" + dateExpire + '\'' + 
			",title = '" + title + '\'' + 
			",descriptionShort = '" + descriptionShort + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.announcementId);
		dest.writeString(this.datePosted);
		dest.writeString(this.description);
		dest.writeString(this.journalId);
		dest.writeString(this.descriptionShort);
		dest.writeString(this.title);
		dest.writeString(this.typeId);
	}
}