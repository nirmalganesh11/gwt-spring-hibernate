package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import proj.shared.SalaryClass;

public interface SalaryClassServiceClientAsync {
	void saveSalaryRecord(SalaryClass salcal,AsyncCallback<String> callback);
	void getSalariesEmp(String username, AsyncCallback<List<SalaryClass>> callback);

}
