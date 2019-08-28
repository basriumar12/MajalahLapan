package id.go.lapan.majalahlapan.model.current;

import com.google.gson.annotations.SerializedName;

public class ResponseAuthor{

	@SerializedName("submission_id")
	private String submissionId;

	@SerializedName("setting_value")
	private String settingValue;

	@SerializedName("seq")
	private String seq;

	public void setSubmissionId(String submissionId){
		this.submissionId = submissionId;
	}

	public String getSubmissionId(){
		return submissionId;
	}

	public void setSettingValue(String settingValue){
		this.settingValue = settingValue;
	}

	public String getSettingValue(){
		return settingValue;
	}

	public void setSeq(String seq){
		this.seq = seq;
	}

	public String getSeq(){
		return seq;
	}

	@Override
 	public String toString(){
		return 
			"ResponseAuthor{" + 
			"submission_id = '" + submissionId + '\'' + 
			",setting_value = '" + settingValue + '\'' + 
			",seq = '" + seq + '\'' + 
			"}";
		}
}