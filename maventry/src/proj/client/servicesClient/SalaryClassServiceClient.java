package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import proj.shared.SalaryClass;

@RemoteServiceRelativePath("salary")
public interface SalaryClassServiceClient extends RemoteService{
	
	String saveSalaryRecord(SalaryClass salcal);
	List<SalaryClass> getSalariesEmp(String username);
}
