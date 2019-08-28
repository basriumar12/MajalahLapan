package id.go.lapan.majalahlapan.model.current;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCurrent{

	@SerializedName("journal_id")
	private String journalId;

	@SerializedName("submission_id")
	private String submissionId;

	@SerializedName("issue_id")
	private String issueId;

	@SerializedName("author")
	private List<AuthorItem> author;

	@SerializedName("files")
	private String files;

	@SerializedName("setting_value")
	private String settingValue;

	public void setJournalId(String journalId){
		this.journalId = journalId;
	}

	public String getJournalId(){
		return journalId;
	}

	public void setSubmissionId(String submissionId){
		this.submissionId = submissionId;
	}

	public String getSubmissionId(){
		return submissionId;
	}

	public void setIssueId(String issueId){
		this.issueId = issueId;
	}

	public String getIssueId(){
		return issueId;
	}

	public void setAuthor(List<AuthorItem> author){
		this.author = author;
	}

	public List<AuthorItem> getAuthor(){
		return author;
	}

	public void setFiles(String files){
		this.files = files;
	}

	public String getFiles(){
		return files;
	}

	public void setSettingValue(String settingValue){
		this.settingValue = settingValue;
	}

	public String getSettingValue(){
		return settingValue;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCurrent{" + 
			"journal_id = '" + journalId + '\'' + 
			",submission_id = '" + submissionId + '\'' + 
			",issue_id = '" + issueId + '\'' + 
			",author = '" + author + '\'' + 
			",files = '" + files + '\'' + 
			",setting_value = '" + settingValue + '\'' + 
			"}";
		}
}