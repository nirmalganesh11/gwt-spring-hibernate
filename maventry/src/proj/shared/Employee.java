package proj.shared;


import com.google.gwt.user.client.rpc.IsSerializable;

public class Employee implements IsSerializable {
    private int id;
    private int salary;
    private String name;
    private String designation;
    private String username;
    private String password;
	public Employee(String username,String password,String name,int salary, String designation) {
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}
	public Employee() {
		
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary + "]";
	}
}
