package id.go.lapan.majalahlapan.model.current;

import com.google.gson.annotations.SerializedName;

public class AuthorItem{

	@SerializedName("name")
	private String name;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"AuthorItem{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}