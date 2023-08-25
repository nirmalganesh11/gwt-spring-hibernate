package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import proj.shared.DesigClass;

public interface DesigServiceClientAsync {
	void getDesignation(String designame,AsyncCallback<DesigClass> asyncCallback);
	void addDesignation(DesigClass desigValue, AsyncCallback<String> callback);
	void getDesignations(AsyncCallback<List<String>> callback);
}
