package be.rds.ezbanking.application.security;

import org.springframework.data.annotation.Id;

public class ApplicationUser {

	@Id
	private String id;
	private String username;
	private String password;
	
	public ApplicationUser() {
		//required for serialization and deserialization
		// both to client and for mongodb storage...
	}
	
	public ApplicationUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
