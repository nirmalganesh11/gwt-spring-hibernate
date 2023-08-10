package silva.client.Panels;

import com.google.gwt.event.shared.SimpleEventBus;
import silva.client.Panels.EventHandlingBus.*;
import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.cellview.client.CellTable;




import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import silva.client.AppService;
import silva.client.AppServiceAsync;
import silva.shared.Employee;
import silva.shared.User;


public class DashBoardPanelClient {
	public CellTable<Employee> employeeTable;
    public ListDataProvider<Employee> dataProvider;
    private final AppServiceAsync app = GWT.create(AppService.class);
    
    @SuppressWarnings("static-access")
	public void createDashboardPanel(User oblog) {
    	
        LoginPanelClient lpc = new LoginPanelClient();
        employeeTable = new CellTable<>();
        dataProvider = new ListDataProvider<>();
        //EventBusBoy ebb = new EventBusBoy();
        EventBusBoy ebb = EventBusBoy.getInstance();
        SimpleEventBus eventBus =ebb.getEventBus();
        
     
        Button showDialogButton = new Button("Create Designation");
        showDialogButton.addClickHandler(event -> {
            DesignationDialog dialogBox = new DesignationDialog();
            dialogBox.center(); // Center the dialog box on the screen
            dialogBox.show();
        });

        // Add the button to the root panel
        Button addSalRecord = new Button("Add Salary Record");
        addSalRecord.addClickHandler(event ->{
        	SalaryRecordDialog salaryBox = new SalaryRecordDialog();
        	salaryBox.center();
        	salaryBox.show();
        });
        
        
       
       app.getEmployees(new AsyncCallback<List<Employee>>(){
		@Override
		public void onFailure(Throwable caught) {
			Window.alert(caught.toString());	
		}
		
		

		@Override
		public void onSuccess(List<Employee> result) {
			dataProvider.getList().addAll(result);
			employeeTable.redraw();
		} 
       });
        
        TextColumn<Employee> nameColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getName();
            }
        };
        TextColumn<Employee> salaryColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return String.valueOf(employee.getSalary());
            }
        };
        TextColumn<Employee> designationColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getDesignation();
            }
        };
        TextColumn<Employee> usernameColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getUsername();
            }
        };
        TextColumn<Employee> passwordColumn = new TextColumn<Employee>() {
            @Override
            public String getValue(Employee employee) {
                return employee.getPassword();
            }
        };
       
        employeeTable.addColumn(nameColumn, "Name");
        employeeTable.addColumn(salaryColumn, "Salary");
        employeeTable.addColumn(designationColumn, "Designation");
        employeeTable.addColumn(usernameColumn,"Username");
        employeeTable.addColumn(passwordColumn,"Password");
        
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(employeeTable);
        scrollPanel.setSize("1000px", "500px");

        
        TextBox nameTextBox = new TextBox();
        TextBox salaryTextBox = new TextBox();
        //TextBox designationTextBox = new TextBox();
        ListBox desigDropdown = new ListBox();
        TextBox usernameTextBox = new TextBox();
        TextBox passwordTextBox = new TextBox();
        TextBox removeTextBox = new TextBox();
        
        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button homeButton = new Button("Home");
        
        
        app.getDesignations(new AsyncCallback<List<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<String> result) {
				for (String designame : result){
			            desigDropdown.addItem(designame);
			    }
				Window.alert("added few fields in desigdrowdown");
			}
        });
        
        eventBus.addHandler(ButtonClickEvent.TYPE, new ButtonClickEventHandler() {
     			@Override
     			public void onButtonClick(ButtonClickEvent event) {
//     				eventBus.fireEvent(new ButtonClickEvent());
     				Window.alert("button is triggered");
     				desigDropdown.clear();
     				 app.getDesignations(new AsyncCallback<List<String>>(){
     						@Override
     						public void onFailure(Throwable caught) {
     							Window.alert(caught.toString());
     						}
     						@Override
     						public void onSuccess(List<String> result) {
     							for (String designame : result){
     						            desigDropdown.addItem(designame);
     						    }
     							Window.alert("added few fields in desigdrowdown");
     						}
     			        });
     			}
             });
        
        
        //keeping the default role when no changes are made
        String[] desigValueGlobal ={"Ceo"};
        desigDropdown.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = desigDropdown.getSelectedIndex();
				String desigValue = desigDropdown.getValue(selectedIndex);
				desigValueGlobal[0] = desigValue;
			}
        });
        
        homeButton.addClickHandler(event ->{
        	RootPanel.get().clear();
        	RootPanel.get().add(lpc.createLoginPanel());
        });
        
        
        addButton.addClickHandler(event -> {
            String name = nameTextBox.getText();
            int salary = Integer.parseInt(salaryTextBox.getText());
            String designation = desigValueGlobal[0];
            String username = usernameTextBox.getText();
            String password = passwordTextBox.getText();
            
            Employee employee = new Employee(username,password,name, salary, designation);
            app.signUpUser(employee,  new AsyncCallback<String>() {
                public void onFailure(Throwable caught) {
                	Window.alert(caught.toString());
                  }

                  public void onSuccess(String result) {
                	if(!result.matches("Employee Already Present"))
                		dataProvider.getList().add(employee);
          			employeeTable.redraw();
          			Window.alert(result);
                  }
                }
            );
            //dataProvider.getList().add(employee);
            employeeTable.redraw();
        });

        removeButton.addClickHandler(event -> {
            int selectedIndex = employeeTable.getKeyboardSelectedRow();
            dataProvider.getList().remove(selectedIndex);
            employeeTable.redraw();
          
            if(removeTextBox.getText() != "") {
            	String removedUser = removeTextBox.getText();
            	
                app.remEmployee(removedUser,new AsyncCallback<Boolean>() {
                	
    				@Override
    				public void onFailure(Throwable caught) {
    					Window.alert(caught.toString());
    				}

    				@Override
    				public void onSuccess(Boolean result) {
    					if(result)
    						Window.alert("removed employee from db");
    					else
    						Window.alert("Employee with given usernamenot found");
    				}
                });
            }
        	
            
//            app.getEmployee(removedUser,new AsyncCallback<Employee>(){
//
// 				@Override
// 				public void onFailure(Throwable caught) {
// 					Window.alert(caught.toString());
// 				}
//
// 				@Override
// 				public void onSuccess(Employee result) {
// 					Window.alert(result.toString());
// 					System.out.print(dataProvider.getList().toString());
// 					dataProvider.getList().remove(result);
// 					employeeTable.redraw();
// 				}
//             	
//             });
            
            app.getEmployees(new AsyncCallback<List<Employee>>(){
        		@Override
        		public void onFailure(Throwable caught) {
        			Window.alert(caught.toString());	
        		}

        		@Override
        		public void onSuccess(List<Employee> result) {
        			dataProvider.getList().clear();
        			employeeTable.redraw();
        			dataProvider.getList().addAll(result);
        			employeeTable.redraw();
        		} 
               });
            employeeTable.redraw();
        });

//        HorizontalPanel hrx = new HorizontalPanel();
//        hrx.add(scrollPanel);
//        hrx.add(passwordTextBox);
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(new Label(oblog.getUsername()));
        verticalPanel.add(homeButton);
        verticalPanel.add(showDialogButton);
        verticalPanel.add(addSalRecord);
        verticalPanel.add(new Label("Name:"));
        verticalPanel.add(nameTextBox);
        verticalPanel.add(new Label("Designation:"));
        verticalPanel.add(desigDropdown);
        
        verticalPanel.add(new Label("Initial Salary:"));
        verticalPanel.add(salaryTextBox);
        verticalPanel.add(new Label("Username:"));
        verticalPanel.add(usernameTextBox);
        verticalPanel.add(new Label("Password:"));
        verticalPanel.add(passwordTextBox);
        verticalPanel.add(addButton);
        verticalPanel.add(new Label("Enter Username of Employee to remove"));
        verticalPanel.add(removeTextBox);
        verticalPanel.add(removeButton);
        verticalPanel.add(scrollPanel);

        //RootPanel.get().add(hrx);
        RootPanel.get().add(verticalPanel);

        dataProvider.addDataDisplay(employeeTable);
    }
}
