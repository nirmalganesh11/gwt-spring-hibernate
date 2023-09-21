package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import proj.shared.DesigClass;

@RemoteServiceRelativePath("desig")
public interface DesigServiceClient extends RemoteService {
	String addDesignation(DesigClass desigVal);
	List<String> getDesignations();
	DesigClass getDesignation(String designame);
	
	List<DesigClass> getDesignationsList();
}
