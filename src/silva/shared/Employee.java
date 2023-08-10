package silva.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Employee implements IsSerializable {
	int id;
	int salary;
	String name;
	String username;
	String password;
	String designation;
	public Employee() {
		
	}
	
	public Employee(String username,String password,String name,int salary,String designation) {
		//super();
		
		this.username = username;
		this.password = password;
		this.name= name;
		this.salary = salary;
		this.designation = designation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
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
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", username=" + username + ", password="
				+ password + "]";
	}
	
}
