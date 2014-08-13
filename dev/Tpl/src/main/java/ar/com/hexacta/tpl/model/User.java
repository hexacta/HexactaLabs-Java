package ar.com.hexacta.tpl.model;

public class User extends Entidad {
	private static final long serialVersionUID = -2599013520313365015L;
	
	private String username;
	private String email;
	private String password;
	private boolean enabled;
	
	// Hibernate needs
	public User() {
		super();
	}
	
	
	public User(String username, String pass){
		super();
		this.username = username;
		this.password = pass;
		this.enabled = true;
	}
	
	public User(String username, String pass, String email){
		super();
		this.username = username;
		this.password = pass;
		this.email = email;
		this.enabled = true;
	}
	
	public User(String username, String pass, String email, boolean enabled){
		super();
		this.username = username;
		this.password = pass;
		this.email = email;
		this.enabled = enabled;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setUsername(String user){
		this.username = user;
	}
	
	public void setPassword(String pass){
		this.password = pass;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public boolean isEnabled(){
		return this.enabled;
	}
	
	public void setEnable(){
		this.enabled = true;
	}
	
	public void setDisable(){
		this.enabled = false;
	}
}
