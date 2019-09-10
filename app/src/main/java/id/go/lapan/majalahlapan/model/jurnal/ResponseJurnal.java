package id.go.lapan.majalahlapan.model.jurnal;

import com.google.gson.annotations.SerializedName;

public class ResponseJurnal{

	@SerializedName("journal_id")
	private String journalId;

	@SerializedName("summary")
	private String summary;

	@SerializedName("image")
	private String image;

	@SerializedName("online_issn")
	private String onlineIssn;

	@SerializedName("print_issn")
	private String printIssn;

	@SerializedName("name")
	private String name;

	@SerializedName("about")
	private String about;

	@SerializedName("publisher")
	private String publisher;

	public void setJournalId(String journalId){
		this.journalId = journalId;
	}

	public String getJournalId(){
		return journalId;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setOnlineIssn(String onlineIssn){
		this.onlineIssn = onlineIssn;
	}

	public String getOnlineIssn(){
		return onlineIssn;
	}

	public void setPrintIssn(String printIssn){
		this.printIssn = printIssn;
	}

	public String getPrintIssn(){
		return printIssn;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAbout(String about){
		this.about = about;
	}

	public String getAbout(){
		return about;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	@Override
 	public String toString(){
		return 
			"ResponseJurnal{" + 
			"journal_id = '" + journalId + '\'' + 
			",summary = '" + summary + '\'' + 
			",image = '" + image + '\'' + 
			",online_issn = '" + onlineIssn + '\'' + 
			",print_issn = '" + printIssn + '\'' + 
			",name = '" + name + '\'' + 
			",about = '" + about + '\'' + 
			",publisher = '" + publisher + '\'' + 
			"}";
		}
}