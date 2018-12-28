package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Files{

	@JsonProperty("configuration.swift")
	private ConfigurationSwift configurationSwift;

	public void setConfigurationSwift(ConfigurationSwift configurationSwift){
		this.configurationSwift = configurationSwift;
	}

	public ConfigurationSwift getConfigurationSwift(){
		return configurationSwift;
	}

	@Override
 	public String toString(){
		return 
			"Files{" + 
			"configuration.swift = '" + configurationSwift + '\'' + 
			"}";
		}
}