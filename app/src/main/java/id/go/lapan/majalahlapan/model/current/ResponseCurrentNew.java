package id.go.lapan.majalahlapan.model.current;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCurrentNew{

	@SerializedName("data")
	private List<DataCurrent> data;

	@SerializedName("year")
	private String year;

	public void setData(List<DataCurrent> data){
		this.data = data;
	}

	public List<DataCurrent> getData(){
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
			"ResponseCurrentNew{" + 
			"data = '" + data + '\'' + 
			",year = '" + year + '\'' + 
			"}";
		}
}