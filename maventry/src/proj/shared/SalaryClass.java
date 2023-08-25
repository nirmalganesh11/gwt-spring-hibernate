package proj.shared;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;



public class SalaryClass implements IsSerializable {
	private int id;
	private String username;
	private Date receivedDate;
	private int amount;
	
	public SalaryClass() {}
	public SalaryClass (String username, int amount){
		this.username = username;
		this.amount = amount;
	}
	public SalaryClass(String username,Date recievedDate,int amount) {
		this.username = username;
		this.receivedDate = recievedDate;
		this.amount = amount;
	}
	public int getId() {
		return id;
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
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
