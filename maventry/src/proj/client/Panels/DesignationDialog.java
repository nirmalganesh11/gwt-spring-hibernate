package proj.client.Panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

//import silva.client.AppService;
//import silva.client.AppServiceAsync;
import proj.client.Panels.EventHandlingBus.EventBusBoy;
import proj.client.servicesClient.DesigServiceClient;
import proj.client.servicesClient.DesigServiceClientAsync;
import proj.shared.DesigClass;
import proj.client.Panels.EventHandlingBus.*;

public class DesignationDialog extends DialogBox {
	
    private DesigServiceClientAsync desigserv;
	
    private TextBox designame;
    private TextBox maxSalary;
    private TextBox minSalary;
    
    private HorizontalPanel buttonPanel;
    private DesigClass ob;
    SimpleEventBus eventBus;
    
    @SuppressWarnings("static-access")
	public DesignationDialog() {
     
    	
    	desigserv = GWT.create(DesigServiceClient.class);
    	designame = new TextBox();
        maxSalary = new TextBox();
        minSalary = new TextBox();
        buttonPanel = new HorizontalPanel();
        ob = new DesigClass();
        
    	EventBusBoy ebb = EventBusBoy.getInstance();
        eventBus=ebb.getEventBus();
        
        setText("Designation Entry Box");
        setAnimationEnabled(true);
        

        VerticalPanel mainPanel = new VerticalPanel();
        
  
        mainPanel.setSpacing(10);
        

        
        Button okButton = new Button("Add");
        okButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
	        	ob.setDesigname(designame.getText());
	        	ob.setMinsalary(Integer.parseInt(minSalary.getText()));
	        	ob.setMaxsalary(Integer.parseInt(maxSalary.getText()));
	        	desigserv.addDesignation(ob,new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}
	
					@Override
					public void onSuccess(String result) {
						if(!result.equals("RoleNotAllowed")){
							Window.alert(result);
							hide();
						}else {
							Window.alert("Role is not allowed");
						}
						
					}
	        	});
	        	eventBus.fireEvent(new ButtonClickEvent());
			}
        	
        });
        
        
        Button cancelButton = new Button("Cancel");
        cancelButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
        	
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setSpacing(6);

       
        mainPanel.add(new Label("Enter Designation"));
        mainPanel.add(designame);
        mainPanel.add(new Label("Enter Min Salary"));
        mainPanel.add(minSalary);
        mainPanel.add(new Label("Enter Max Salary"));
        mainPanel.add(maxSalary);
        mainPanel.add(buttonPanel);
        

        
        setWidget(mainPanel);
    }

}
