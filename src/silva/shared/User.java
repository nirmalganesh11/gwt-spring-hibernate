package silva.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable {
	private String username;
	private String password;
	private String designation;
	private int id;
	public User() {}
	public User(String username, String password, String designation) {
		super();
		this.username = username;
		this.password = password;
		this.designation = designation;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", designation=" + designation + "]";
	}
	
	
	
	
}
