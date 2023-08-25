package proj.client.Panels;

import com.google.gwt.event.shared.SimpleEventBus;
import proj.client.Panels.EventHandlingBus.*;
import proj.client.servicesClient.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
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



//import proj.client.AppService;
//import proj.client.AppServiceAsync;
import proj.shared.DesigClass;
import proj.shared.Employee;
import proj.shared.User;

import com.google.gwt.cell.client.ClickableTextCell;

public class DashBoardPanelClient {
	public CellTable<Employee> employeeTable;
    public ListDataProvider<Employee> dataProvider;
    
    List<TextBox> myTextboxes = new ArrayList<>();
    Label success = new Label();
    TextBox nameTextBox = new TextBox();
    TextBox salaryTextBox = new TextBox();
    Button showDialogButton = new Button("Create Designation");
    //TextBox designationTextBox = new TextBox();
    String username;
    Button test;
   
    TextBox usernameTextBox = new TextBox();
    TextBox passwordTextBox = new TextBox();
    TextBox removeTextBox = new TextBox();
    Employee employee;
    public Label errorLabel = new Label();
    ListBox desigDropdown = new ListBox();
    String[] desigValueGlobal ={"Ceo"};
    LoginPanelClient lpc = new LoginPanelClient();
    
    private EmployeeServiceClientAsync empserv;
    private DesigServiceClientAsync desigserv;
    private AuthenticationServiceAsync authserv;
   
    public DesigClass desigVal;
    private boolean ascending = true;
    public void clearTextboxes(List<TextBox> textboxes) {
        for (TextBox textbox : textboxes) {
            textbox.setValue(""); 
        }
    }
    @SuppressWarnings("static-access")
	public void createDashboardPanel(User oblog) {
    	
    	empserv = GWT.create(EmployeeServiceClient.class);
    	desigserv = GWT.create(DesigServiceClient.class);
    	authserv = GWT.create(AuthenticationService.class);
    	
    	test = new Button("test");
    	
        employeeTable = new CellTable<>();
        employeeTable.setPageSize(50);
        dataProvider = new ListDataProvider<>();
        //EventBusBoy ebb = new EventBusBoy();
        EventBusBoy ebb = EventBusBoy.getInstance();
        SimpleEventBus eventBus =ebb.getEventBus();
        
        
        errorLabel.setStyleName("redLabel");
        
        
        
//        showDialogButton.addClickHandler(event -> {
//            DesignationDialog dialogBox = new DesignationDialog();
//            dialogBox.center(); // Center the dialog box on the screen
//            dialogBox.show();
//        });
        showDialogButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DesignationDialog dialogBox = new DesignationDialog();
	            dialogBox.center(); // Center the dialog box on the screen
	            dialogBox.show();
			}
        	
        });
     
        
        

        // Add the button to the root panel
        Button addSalRecord = new Button("Add Salary Record");
//        addSalRecord.addClickHandler(event ->{
//        	SalaryRecordDialog salaryBox = new SalaryRecordDialog();
//        	salaryBox.center();
//        	salaryBox.show();
//        });
        addSalRecord.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
	        	SalaryRecordDialog salaryBox = new SalaryRecordDialog();
	        	salaryBox.center();
	        	salaryBox.show();
			}
        	
        });
        
       
       empserv.getEmployees(new AsyncCallback<List<Employee>>(){
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

        Column<Employee, String> passwordColumn = new Column<Employee, String>(new PasswordMaskCell()) {
            @Override
            public String getValue(Employee user) {
                return user.getPassword();
            }
        };
        employeeTable.addColumn(nameColumn, "Name");
        employeeTable.addColumn(designationColumn, "Designation");
        employeeTable.addColumn(usernameColumn,"Username");
        employeeTable.addColumn(passwordColumn,"Password");
 
        addClickableHeader(salaryColumn,"Salary");
        
     // Create and set up edit button column
        Column<Employee, String> editButtonColumn = new Column<Employee, String>(new EditButtonCell()) {
            @Override
            public String getValue(Employee user) {
                return "Edit";
            }
        };
        employeeTable.addColumn(editButtonColumn, "Edit");

        
        
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(employeeTable);
        scrollPanel.setSize("1000px", "700px");	

        
        
  
        myTextboxes.add(nameTextBox);
        myTextboxes.add(salaryTextBox);
        myTextboxes.add(usernameTextBox);
        myTextboxes.add(passwordTextBox);
        myTextboxes.add(nameTextBox);
        
        
        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button homeButton = new Button("Home");
        
        
     
        
        
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
				//Window.alert("added few fields in desigdrowdown");
			}
        });
        
        
        eventBus.addHandler(ButtonClickEvent.TYPE, new ButtonClickEventHandler() {
     			@Override
     			public void onButtonClick(ButtonClickEvent event) {
//     				eventBus.fireEvent(new ButtonClickEvent());
     				Window.alert("button is triggered");
     				desigDropdown.clear();
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
     							//Window.alert("added few fields in desigdrowdown");
     						}
     			        });
     			}
             });
        
        
        //keeping the default role when no changes are made
        
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
        
        desigserv.getDesignation(desigValueGlobal[0], new AsyncCallback<DesigClass>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("salary TextBox get Designation failed");
			}

			@Override
			public void onSuccess(DesigClass result) {
				desigVal = result;
				}
			});

        homeButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
	        	RootPanel.get().clear();
	        	RootPanel.get().add(lpc.createLoginPanel());
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
	                	if(!result.matches("Employee Already Present"))
	                		dataProvider.getList().add(employee);
	          			employeeTable.redraw();
	          			success.setText(result);
	          			clearTextboxes(myTextboxes);
	                  }
	                }
	            );
	            //dataProvider.getList().add(employee);
	            employeeTable.redraw();
			}
        	
        });


        
        removeButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
	            int selectedIndex = employeeTable.getKeyboardSelectedRow();
	            dataProvider.getList().remove(selectedIndex);
	            employeeTable.redraw();
	          
	            if(removeTextBox.getText() != "") {
	            	String removedUser = removeTextBox.getText();
	            	
	                empserv.remEmployee(removedUser,new AsyncCallback<Boolean>() {
	                	
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
	        	
	            
	            
	           
	            
	            
	            empserv.getEmployees(new AsyncCallback<List<Employee>>(){
	        		@Override
	        		public void onFailure(Throwable caught) {
	        			Window.alert(caught.toString());	
	        		}
	
	        		@Override
	        		public void onSuccess(List<Employee> result) {
	        			dataProvider.getList().clear();
	        			dataProvider.getList().addAll(result);
	        			employeeTable.redraw();
	        		} 
	               });
	            employeeTable.redraw();
			}
        	
        	
        });
        
       
        
       

       

        test.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				  
		        authserv.getLoggedInUser(new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}
		        	
		        });
			}
        	
        });
        
      
        
        VerticalPanel verticalPanel = new VerticalPanel();
        //verticalPanel.add(new Label(oblog.getUsername()));
        verticalPanel.add(new Label(username));
        verticalPanel.add(test);
        verticalPanel.add(homeButton);
        verticalPanel.add(showDialogButton);
        verticalPanel.add(addSalRecord);
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
        verticalPanel.add(success);
        verticalPanel.add(addButton);
        verticalPanel.add(new Label("Enter Username of Employee to remove"));
        verticalPanel.add(removeTextBox);
        verticalPanel.add(removeButton);
        verticalPanel.add(scrollPanel);
        HorizontalPanel hq = new HorizontalPanel();
        hq.setSpacing(40);
        hq.add(verticalPanel);
        hq.add(scrollPanel);

        RootPanel.get().add(hq);

        dataProvider.addDataDisplay(employeeTable);
    }
    private void addClickableHeader(Column<Employee, ?> column, final String columnName) {
        // Create a header with a clickable cell
        ClickableTextCell headerCell = new ClickableTextCell();
        Header<String> header = new Header<String>(headerCell) {
            @Override
            public String getValue() {
                return columnName;
            }
        };
        header.setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(String value) {
                sortData(columnName);
            }
        });

        // Add the header and column to the table
        employeeTable.addColumn(column, header);
    }

    private void sortData(final String columnName) {
    	List<Employee> list = new ArrayList<>(dataProvider.getList());        
        Comparator<Employee> employeeComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
            	 int result;
                 if ("Name".equals(columnName)) {
                     result = e1.getName().compareTo(e2.getName());
                 } else if ("Salary".equals(columnName)) {
                     result = Integer.compare(e1.getSalary(), e2.getSalary());
                 } else {
                     result = 0;
                 }
                 return ascending ? result : -result;
            }
        };
        Collections.sort(list, employeeComparator);
        dataProvider.getList().clear();
        dataProvider.getList().addAll(list);
        ascending = !ascending; // Toggle sorting order
        employeeTable.redraw(); // Refresh the table after sorting
    }
    
    
    
}
