package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class GistsPublic{

	@JsonProperty("owner")
	private Owner owner;

	@JsonProperty("commits_url")
	private String commitsUrl;

	@JsonProperty("comments")
	private int comments;

	@JsonProperty("forks_url")
	private String forksUrl;

	@JsonProperty("git_push_url")
	private String gitPushUrl;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("description")
	private String description;

	@JsonProperty("truncated")
	private boolean truncated;

	@JsonProperty("url")
	private String url;

	@JsonProperty("public")
	private boolean jsonMemberPublic;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("html_url")
	private String htmlUrl;

	@JsonProperty("git_pull_url")
	private String gitPullUrl;

	@JsonProperty("comments_url")
	private String commentsUrl;

	@JsonProperty("files")
	private Files files;

	@JsonProperty("id")
	private String id;

	@JsonProperty("user")
	private Object user;

	@JsonProperty("node_id")
	private String nodeId;

	public void setOwner(Owner owner){
		this.owner = owner;
	}

	public Owner getOwner(){
		return owner;
	}

	public void setCommitsUrl(String commitsUrl){
		this.commitsUrl = commitsUrl;
	}

	public String getCommitsUrl(){
		return commitsUrl;
	}

	public void setComments(int comments){
		this.comments = comments;
	}

	public int getComments(){
		return comments;
	}

	public void setForksUrl(String forksUrl){
		this.forksUrl = forksUrl;
	}

	public String getForksUrl(){
		return forksUrl;
	}

	public void setGitPushUrl(String gitPushUrl){
		this.gitPushUrl = gitPushUrl;
	}

	public String getGitPushUrl(){
		return gitPushUrl;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTruncated(boolean truncated){
		this.truncated = truncated;
	}

	public boolean isTruncated(){
		return truncated;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setJsonMemberPublic(boolean jsonMemberPublic){
		this.jsonMemberPublic = jsonMemberPublic;
	}

	public boolean isJsonMemberPublic(){
		return jsonMemberPublic;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setHtmlUrl(String htmlUrl){
		this.htmlUrl = htmlUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public void setGitPullUrl(String gitPullUrl){
		this.gitPullUrl = gitPullUrl;
	}

	public String getGitPullUrl(){
		return gitPullUrl;
	}

	public void setCommentsUrl(String commentsUrl){
		this.commentsUrl = commentsUrl;
	}

	public String getCommentsUrl(){
		return commentsUrl;
	}

	public void setFiles(Files files){
		this.files = files;
	}

	public Files getFiles(){
		return files;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUser(Object user){
		this.user = user;
	}

	public Object getUser(){
		return user;
	}

	public void setNodeId(String nodeId){
		this.nodeId = nodeId;
	}

	public String getNodeId(){
		return nodeId;
	}

	@Override
 	public String toString(){
		return 
			"GistsPublic{" + 
			"owner = '" + owner + '\'' + 
			",commits_url = '" + commitsUrl + '\'' + 
			",comments = '" + comments + '\'' + 
			",forks_url = '" + forksUrl + '\'' + 
			",git_push_url = '" + gitPushUrl + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",truncated = '" + truncated + '\'' + 
			",url = '" + url + '\'' + 
			",public = '" + jsonMemberPublic + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",html_url = '" + htmlUrl + '\'' + 
			",git_pull_url = '" + gitPullUrl + '\'' + 
			",comments_url = '" + commentsUrl + '\'' + 
			",files = '" + files + '\'' + 
			",id = '" + id + '\'' + 
			",user = '" + user + '\'' + 
			",node_id = '" + nodeId + '\'' + 
			"}";
		}
}