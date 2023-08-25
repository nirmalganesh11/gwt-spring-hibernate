package proj.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DesigClass implements IsSerializable {
	private int id;
	private String designame;
	private int maxsalary;
	private int minsalary;
	public DesigClass() {}
	
	
	public DesigClass(String designame, int maxsalary, int minsalary) {
		super();
		this.designame = designame;
		this.maxsalary = maxsalary;
		this.minsalary = minsalary;
	}
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getDesigname() {
		return designame;
	}
	public void setDesigname(String designame) {
		this.designame = designame;
	}
	public int getMaxsalary() {
		return maxsalary;
	}
	public void setMaxsalary(int maxsalary) {
		this.maxsalary = maxsalary;
	}
	public int getMinsalary() {
		return minsalary;
	}
	public void setMinsalary(int minsalary) {
		this.minsalary = minsalary;
	}
	@Override
	public String toString() {
		return "DesigClass [designame=" + designame + ", maxsalary=" + maxsalary + ", minsalary=" + minsalary + "]";
	}
	
	

}
