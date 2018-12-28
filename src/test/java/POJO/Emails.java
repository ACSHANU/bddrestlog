package POJO;

public class Emails{
	private String visibility;
	private boolean verified;
	private String email;
	private boolean primary;

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setVerified(boolean verified){
		this.verified = verified;
	}

	public boolean isVerified(){
		return verified;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPrimary(boolean primary){
		this.primary = primary;
	}

	public boolean isPrimary(){
		return primary;
	}

	@Override
 	public String toString(){
		return 
			"Emails{" + 
			"visibility = '" + visibility + '\'' + 
			",verified = '" + verified + '\'' + 
			",email = '" + email + '\'' + 
			",primary = '" + primary + '\'' + 
			"}";
		}
}
