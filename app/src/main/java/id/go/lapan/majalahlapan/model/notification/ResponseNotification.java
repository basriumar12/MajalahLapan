package id.go.lapan.majalahlapan.model.notification;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "notifaction")
public class ResponseNotification{


	@PrimaryKey(autoGenerate = true)
	int id;

	@SerializedName("journal_id")
	private String journalId;

	@SerializedName("notif")
	private String notif;

	@SerializedName("issue_id")
	private String issueId;

	@SerializedName("date_published")
	private String datePublished;

	@Ignore
	public ResponseNotification(String journalId, String notif, String issueId, String datePublished) {
		this.journalId = journalId;
		this.notif = notif;
		this.issueId = issueId;
		this.datePublished = datePublished;
	}

	public ResponseNotification(int id, String journalId, String notif, String issueId, String datePublished) {
		this.id = id;
		this.journalId = journalId;
		this.notif = notif;
		this.issueId = issueId;
		this.datePublished = datePublished;
	}

	public void setJournalId(String journalId){
		this.journalId = journalId;
	}

	public String getJournalId(){
		return journalId;
	}

	public void setNotif(String notif){
		this.notif = notif;
	}

	public String getNotif(){
		return notif;
	}

	public void setIssueId(String issueId){
		this.issueId = issueId;
	}

	public String getIssueId(){
		return issueId;
	}

	public void setDatePublished(String datePublished){
		this.datePublished = datePublished;
	}

	public String getDatePublished(){
		return datePublished;
	}

	@Override
 	public String toString(){
		return 
			"ResponseNotification{" + 
			"journal_id = '" + journalId + '\'' + 
			",notif = '" + notif + '\'' + 
			",issue_id = '" + issueId + '\'' + 
			",date_published = '" + datePublished + '\'' + 
			"}";
		}
}