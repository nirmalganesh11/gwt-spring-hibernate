package proj.shared.security;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FullUser implements IsSerializable {
	
	private int fuid;
	private int empId;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
    private String title;
    private String location;
    private String email;
    private String phno;
    
    private List<Role> rolesList;
    
   
   

	public FullUser() {
    	
    }
    
	
	public FullUser(int empId, String userId,String password, String firstName, String lastName) {
		super();
		this.empId = empId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
	public int getFuid() {
		return fuid;
	}

	public void setFuid(int fuid) {
		this.fuid = fuid;
	}
	public List<Role> getRolesList() {
		return rolesList;
	}


	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	
}
