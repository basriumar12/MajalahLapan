package id.go.lapan.majalahlapan.model.archive;

import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("journal_id")
	private String journalId;

	@SerializedName("issue_id")
	private String issueId;

	@SerializedName("title")
	private String title;

	public void setJournalId(String journalId){
		this.journalId = journalId;
	}

	public String getJournalId(){
		return journalId;
	}

	public void setIssueId(String issueId){
		this.issueId = issueId;
	}

	public String getIssueId(){
		return issueId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"journal_id = '" + journalId + '\'' + 
			",issue_id = '" + issueId + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}