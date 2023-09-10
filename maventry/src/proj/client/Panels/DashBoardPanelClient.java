package proj.client.Panels;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import proj.client.Panels.EventHandlingBus.*;
import proj.client.Security.SecurityPanel;

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
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

//import proj.client.AppService;
//import proj.client.AppServiceAsync;
import proj.shared.DesigClass;
import proj.shared.Employee;
import proj.shared.User;
import proj.shared.security.Role;

import com.google.gwt.cell.client.ClickableTextCell;

public class DashBoardPanelClient {
	
	public CellTable<Employee> employeeTable;
	final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();
	
    public ListDataProvider<Employee> dataProvider;
    public DesigClass desigVal;
    
    Button openDesignationDialog = new Button("Create Designation");
    Button AddEmployeeButton = new Button("Add Employee");
    Button addSalRecordButton = new Button("Add Salary Record");
    Button editEmployeeButton = new Button("Edit Employee");
    Button removeEmployeeButton = new Button("Remove Employee");
    
    String username;
    Button loginUserCheckButton= new Button("LoginInfo");
    Button logoutUserSeeButton= new Button("Logout");
    Button securityButton = new Button("Security");

    LoginPanelClient lpc = new LoginPanelClient();
    Button homeButton = new Button("Home");
    
    private EmployeeServiceClientAsync empServ;
    private AuthenticationServiceAsync authServ;
   
    
    private boolean ascending = true;
    private int selectedIndexEmployeeTable =0;
    private Employee add =null;
    

    
    @SuppressWarnings("static-access")
	public void createDashboardPanel() {
    	
    	empServ = GWT.create(EmployeeServiceClient.class);
    	authServ = GWT.create(AuthenticationService.class);
    	
	
        employeeTable = new CellTable<>();
        employeeTable.setPageSize(50);
        dataProvider = new ListDataProvider<>();
        
        employeeTable.setSelectionModel(selectionModel);

        EventBusBoy ebb = EventBusBoy.getInstance();
        SimpleEventBus eventBus =ebb.getEventBus();
        
        
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
//        Column<Employee, String> editButtonColumn = new Column<Employee, String>(new EditButtonCell()) {
//            @Override
//            public String getValue(Employee user) {
//                return "Edit";
//            }
//        };
//        employeeTable.addColumn(editButtonColumn, "Edit");
        
        
        
        HandlerRegistration selectionHandler = selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = employeeTable.getKeyboardSelectedRow();
                selectedIndexEmployeeTable = selectedIndex;
                if (selectedIndex >= 0) {
                    // Get the selected index
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    // No row is selected
                    System.out.println("No row selected");
                }
            }
        });
        
        
        empServ.getEmployees(new AsyncCallback<List<Employee>>(){
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
        
        
        
        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(employeeTable);
        scrollPanel.setSize("700px", "500px");	

        
        
        eventBus.addHandler(ButtonClickEvent.TYPE, new ButtonClickEventHandler() {
     			@Override
     			public void onButtonClick(ButtonClickEvent event) {

     				empServ.getEmployees(new AsyncCallback<List<Employee>>(){
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
     				
     				
     			}
             });
           
        
        openDesignationDialog.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DesignationDialog dialogBox = new DesignationDialog();
	            dialogBox.center();
	            dialogBox.show();
			}
        	
        });
     
        
        AddEmployeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EmployeeSignUpPanel empsign = new EmployeeSignUpPanel(add);
				empsign.center();
				empsign.show();
			}
        	
        });


       
        addSalRecordButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
	        	SalaryRecordDialog salaryBox = new SalaryRecordDialog();
	        	salaryBox.center();
	        	salaryBox.show();
			}
        	
        });
        
        
        homeButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
	        	RootPanel.get().clear();
	        	RootPanel.get().add(lpc.createLoginPanel());
			}
        	
        });
        

   
        loginUserCheckButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				  
		        authServ.getLoggedInUser(new AsyncCallback<String>() {
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
        
        logoutUserSeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				authServ.LogoutUser(new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("user logged out ");
					}
					
				});
				
			}
        	
        });
        
        securityButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				SecurityTabDialog newSecurityTab = new SecurityTabDialog();
//				//newSecurityTab.setPixelSize(1000, 500);
//				newSecurityTab.center();
//				newSecurityTab.show();
				RootPanel.get().clear();
				SecurityPanel sc = new SecurityPanel();
				sc.createSecurityPanel();
			}
        	
        	
        	
        });
        editEmployeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Employee selected = dataProvider.getList().get(selectedIndexEmployeeTable);
				EmployeeSignUpPanel empsign = new EmployeeSignUpPanel(selected);
				empsign.editableEmployee = selected;
				empsign.center();
				empsign.show();
			}
        	
        	
        	
        });
        
        removeEmployeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Employee selected = dataProvider.getList().get(selectedIndexEmployeeTable);
				empServ.remEmployee(selected.getUsername(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failed to remove employee");
					}

					@Override
					public void onSuccess(Boolean result) {
						if(result) {
							dataProvider.getList().remove(selectedIndexEmployeeTable);
							Window.alert(result +"removed");
						}
					}
					
				});
				employeeTable.redraw();
			}
        	
        });
        
        
        
        
      
        
        VerticalPanel verticalPanel = new VerticalPanel();
        
        verticalPanel.add(homeButton);
        verticalPanel.add(loginUserCheckButton);
        
        
        verticalPanel.add(openDesignationDialog);
        
        
        verticalPanel.add(AddEmployeeButton);
        verticalPanel.add(editEmployeeButton);
        verticalPanel.add(removeEmployeeButton);
        
        verticalPanel.add(addSalRecordButton);
        
        
        verticalPanel.add(securityButton);
        
        verticalPanel.add(logoutUserSeeButton);
       
       // verticalPanel.add(scrollPanel);
        verticalPanel.setSpacing(15);
        
        VerticalPanel secondSide = new VerticalPanel();
        
        HorizontalPanel employeeButtonsPanel = new HorizontalPanel();
        employeeButtonsPanel.setSpacing(20);
        employeeButtonsPanel.add(AddEmployeeButton);
        employeeButtonsPanel.add(editEmployeeButton);
        employeeButtonsPanel.add(removeEmployeeButton);
        
        secondSide.add(employeeButtonsPanel);
        secondSide.add(scrollPanel);
        secondSide.setSpacing(20);
        
        HorizontalPanel hq = new HorizontalPanel();
        
        hq.setSpacing(40);
        hq.add(verticalPanel);
        //hq.add(scrollPanel);
        hq.add(secondSide);

        RootPanel.get().add(hq);

        dataProvider.addDataDisplay(employeeTable);
    }
    
    public void clearTextboxes(List<TextBox> textboxes) {
        for (TextBox textbox : textboxes) {
            textbox.setValue(""); 
        }
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
        ascending = !ascending; 
        employeeTable.redraw(); 
    }
    
}
