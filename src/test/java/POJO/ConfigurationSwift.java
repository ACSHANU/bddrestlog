package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ConfigurationSwift{

	@JsonProperty("filename")
	private String filename;

	@JsonProperty("size")
	private int size;

	@JsonProperty("language")
	private String language;

	@JsonProperty("type")
	private String type;

	@JsonProperty("raw_url")
	private String rawUrl;

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setRawUrl(String rawUrl){
		this.rawUrl = rawUrl;
	}

	public String getRawUrl(){
		return rawUrl;
	}

	@Override
 	public String toString(){
		return 
			"ConfigurationSwift{" + 
			"filename = '" + filename + '\'' + 
			",size = '" + size + '\'' + 
			",language = '" + language + '\'' + 
			",type = '" + type + '\'' + 
			",raw_url = '" + rawUrl + '\'' + 
			"}";
		}
}