package id.go.lapan.majalahlapan.model.archive;

import com.google.gson.annotations.SerializedName;

public class ResponseArchive{
	public ResponseArchive(String journalId, String issueId, String year, String title) {
		this.journalId = journalId;
		this.issueId = issueId;
		this.year = year;
		this.title = title;
	}

	@SerializedName("journal_id")
	private String journalId;

	@SerializedName("issue_id")
	private String issueId;

	@SerializedName("year")
	private String year;

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

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
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
			"ResponseArchive{" + 
			"journal_id = '" + journalId + '\'' + 
			",issue_id = '" + issueId + '\'' + 
			",year = '" + year + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}