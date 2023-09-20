package proj.client.Panels;

import com.google.gwt.event.shared.SimpleEventBus;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import proj.client.servicesClient.DesigServiceClient;
import proj.client.servicesClient.DesigServiceClientAsync;
import proj.client.servicesClient.EmployeeServiceClient;
import proj.client.servicesClient.EmployeeServiceClientAsync;
import proj.shared.DesigClass;
import proj.shared.Employee;
//import silva.client.AppService;
//import silva.client.AppServiceAsync;
import proj.client.Panels.EventHandlingBus.*;

public class EmployeeSignUpPanel extends DialogBox {
	
	
	    private DesigServiceClientAsync desigserv;
	    private EmployeeServiceClientAsync empserv;
	    SimpleEventBus eventBus;

	    TextBox nameTextBox = new TextBox();
        TextBox salaryTextBox = new TextBox();
        TextBox usernameTextBox = new TextBox();
        TextBox passwordTextBox = new TextBox();
        ///TextBox removeTextBox = new TextBox();
        Button cancelButton = new Button("Cancel");
        
        Button addButton = new Button("Add");
        //Button removeButton = new Button("Remove");
        Label success = new Label();
        
        DesigClass desigVal;
        Employee employee;
        public Employee editableEmployee;
        
        
        List<TextBox> totalTextboxes = new ArrayList<>();
        
        String[] desigValueGlobal ={"Ceo"};
        
        ListBox desigDropdown = new ListBox();
        Label errorLabel = new Label();
        
	    public void clearTextboxes(List<TextBox> textboxes) {
	        for (TextBox textbox : textboxes) {
	            textbox.setValue(""); 
	        }
	    }
	    public void setTextBoxDataForEditable() {
	    	if(editableEmployee != null) {
	    		nameTextBox.setText(editableEmployee.getName());
	    		salaryTextBox.setText(String.valueOf(editableEmployee.getSalary()));
	    		usernameTextBox.setText(editableEmployee.getUsername());
	    		passwordTextBox.setText(editableEmployee.getPassword());

	    	}
	    }
	    
	    @SuppressWarnings("static-access")
		public EmployeeSignUpPanel(Employee pass) {
	    	
	    	editableEmployee = pass;
	    	setTextBoxDataForEditable();
	    	
	    	empserv = GWT.create(EmployeeServiceClient.class);
	    	desigserv = GWT.create(DesigServiceClient.class);
	    	errorLabel.setStyleName("redLabel");
	    	
	    	EventBusBoy ebb = EventBusBoy.getInstance();
	        eventBus =ebb.getEventBus();
	    	
	    	totalTextboxes.add(nameTextBox);
	    	totalTextboxes.add(salaryTextBox);
	    	totalTextboxes.add(usernameTextBox);
	    	totalTextboxes.add(passwordTextBox);
	    	totalTextboxes.add(nameTextBox);
	    	
	    	setText("Employee Entry Box");
	        setAnimationEnabled(true);
	        
	    	 desigserv.getDesignations(new AsyncCallback<List<String>>(){
	 			@Override
	 			public void onFailure(Throwable caught) {
	 				Window.alert(caught.toString());
	 			}

	 			@Override
	 			public void onSuccess(List<String> result) {
	 				for (String designame : result){
	 			            desigDropdown.addItem(designame);
	 			    }

	 			}
	         });
	    	 
	    	 desigDropdown.addChangeHandler(new ChangeHandler() {
	 			@Override
	 			public void onChange(ChangeEvent event) {
	 				int selectedIndex = desigDropdown.getSelectedIndex();
	 				String desigValue = desigDropdown.getValue(selectedIndex);
	 				desigValueGlobal[0] = desigValue;
	 				
	 				 desigserv.getDesignation(desigValue, new AsyncCallback<DesigClass>() {

	 						@Override
	 						public void onFailure(Throwable caught) {
	 							Window.alert("salary TextBox get Designation failed");
	 						}

	 						@Override
	 						public void onSuccess(DesigClass result) {
	 							desigVal = result;
	 							}
	 						});
	 			}
	         });
	    	
	    	 salaryTextBox.addChangeHandler(new ChangeHandler() {

	     			@Override
	     			public void onChange(ChangeEvent event) {
	     				errorLabel.setText("");
	     				int maxamount = desigVal.getMaxsalary();
	     				int minamount = desigVal.getMinsalary();
	     				int amount = Integer.parseInt(salaryTextBox.getText());
	     				if(amount <minamount) {
	     					errorLabel.setText("must be greater than ="+minamount);
	     				}else if(amount >maxamount) {
	     					errorLabel.setText("must be less than ="+maxamount);
	     				}else {
	     					errorLabel.setText("Just about right");
	     				}
	     			}
	             	
	             });
	    	 
	    	 addButton.addClickHandler(new ClickHandler(){

	 			@Override
	 			public void onClick(ClickEvent event) {
	 	            String name = nameTextBox.getText();
	 	            int salary = Integer.parseInt(salaryTextBox.getText());
	 	            String designation = desigValueGlobal[0];
	 	            String username = usernameTextBox.getText();
	 	            String password = passwordTextBox.getText();
	 	            
	 	            employee = new Employee(username,password,name, salary, designation);
	 	            empserv.signUpEmployee(employee,  new AsyncCallback<String>() {
	 	                public void onFailure(Throwable caught) {
	 	                	Window.alert(caught.toString());
	 	                  }
	 	
	 	                  public void onSuccess(String result) {
	 	                	if(!result.equals("RoleNotAllowed")) {
	 	                		if(!result.matches("Employee Already Present")) {
	 	                			Window.alert("Employee Added");
	 	                		}
	 	  
	 	          			success.setText(result);
	 	          			clearTextboxes(totalTextboxes);
	 	          			eventBus.fireEvent(new ButtonClickEvent());
	 	                  }
	 	                	else {
	 		                	  Window.alert("Role not allowed");
	 		                  }
	 	                  }
	 	                
	 	                }
	 	            );

	 			}
	         	
	         });
	    	 
	    	 
	         
//	         removeButton.addClickHandler(new ClickHandler(){
//
//	 			@Override
//	 			public void onClick(ClickEvent event) {
//	 	          
//	 	            if(removeTextBox.getText() != "") {
//	 	            	String removedUser = removeTextBox.getText();
//	 	            	
//	 	                empserv.remEmployee(removedUser,new AsyncCallback<Boolean>() {
//	 	                	
//	 	    				@Override
//	 	    				public void onFailure(Throwable caught) {
//	 	    					Window.alert(caught.toString());
//	 	    				}
//	 	
//	 	    				@Override
//	 	    				public void onSuccess(Boolean result) {
//	 	    					if(result) {
//	 	    						Window.alert("removed employee from db");
//	 	    						eventBus.fireEvent(new ButtonClickEvent());
//	 	    					}
//	 	    					else {
//	 	    						Window.alert("Employee with given usernamenot found");
//	 	    					}
//	 	    				}
//	 	                });
//	 	            }
//	 	        	
//	 			}
//	         	
//	         	
//	         });
	         
	         
	         cancelButton.addClickHandler(new ClickHandler(){
	 			@Override
	 			public void onClick(ClickEvent event) {
	 				hide();
	 			}
	         	
	         });
	         
	         HorizontalPanel hzp = new HorizontalPanel();
	         hzp.add(addButton);
	         //hzp.add(removeButton);
	         hzp.add(cancelButton);
	         hzp.setSpacing(5);
	         
	         
	         
	         

	         VerticalPanel verticalPanel = new VerticalPanel();
	         verticalPanel.add(new Label("Name:"));
	         verticalPanel.add(nameTextBox);
	         verticalPanel.add(new Label("Designation:"));
	         verticalPanel.add(desigDropdown);
	         
	         verticalPanel.add(new Label("Initial Salary:"));
	         verticalPanel.add(salaryTextBox);
	         verticalPanel.add(errorLabel);
	         verticalPanel.add(new Label("Username:"));
	         verticalPanel.add(usernameTextBox);
	         verticalPanel.add(new Label("Password:"));
	         verticalPanel.add(passwordTextBox);
	         

	        // verticalPanel.add(new Label("Enter Username to remove"));
	         //verticalPanel.add(removeTextBox);

	         verticalPanel.setSpacing(5);
	         verticalPanel.add(success);
	         verticalPanel.add(hzp);

	         setWidget(verticalPanel);
	    	
	    }
	
}



