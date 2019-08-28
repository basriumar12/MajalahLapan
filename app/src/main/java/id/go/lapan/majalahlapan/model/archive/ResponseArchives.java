package id.go.lapan.majalahlapan.model.archive;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseArchives{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("year")
	private String year;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	@Override
 	public String toString(){
		return 
			"ResponseArchives{" + 
			"data = '" + data + '\'' + 
			",year = '" + year + '\'' + 
			"}";
		}
}