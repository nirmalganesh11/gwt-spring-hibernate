package proj.client.Panels;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import proj.client.Panels.EventHandlingBus.*;
import proj.client.Security.SecurityPanel;
import proj.client.newsecurity.DesignationInput;
import proj.client.newsecurity.EmployeeForm;
import proj.client.newsecurity.LoginView;
import proj.client.newsecurity.SalaryPanel;
import proj.client.newsecurity.WholeDesignationPage;
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
import com.google.gwt.user.client.ui.Image;
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

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialPanel;
//import proj.client.AppService;
//import proj.client.AppServiceAsync;
import proj.shared.DesigClass;
import proj.shared.Employee;
import proj.shared.User;
import proj.shared.security.Role;

import gwt.material.design.client.ui.MaterialSideNav;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

import com.google.gwt.cell.client.ClickableTextCell;

public class DashBoardPanelClient {
	
	public CellTable<Employee> employeeTable;
	final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();
	
    HorizontalPanel contentPanel = new HorizontalPanel();
    
    VerticalPanel employeeButtonsPanel = new VerticalPanel();
    
    VerticalPanel wholenew = new VerticalPanel();
	
    public ListDataProvider<Employee> dataProvider;
    public DesigClass desigVal;
    
//    Button openDesignationDialog = new Button("Create Designation");
    Button addEmployeeButton = new Button("Add Employee");
//    Button addSalRecordButton = new Button("Add Salary Record");
    Button editEmployeeButton = new Button("Edit Employee");
    Button removeEmployeeButton = new Button("Remove Employee");
    
    String username;
    Button loginUserCheckButton= new Button("LoginInfo");
    Button logoutUserSeeButton= new Button("Logout");
    Button securityButton = new Button("Security");

    LoginPanelClient lpc = new LoginPanelClient();
    Button homeButton = new Button("Home");
    
    //DesignationDialog dialogBox;
    DesignationInput desigInputPanel;
    
    EmployeeForm leftSidePanel;
    EmployeeForm editPanel;
    
    private DashBoardPanelClient dbp;
    
    
    private EmployeeServiceClientAsync empServ;
    private AuthenticationServiceAsync authServ;
   
    
    private boolean ascending = true;
    private int selectedIndexEmployeeTable =0;
    private Employee add =null;
    
    
    
    @SuppressWarnings("static-access")
	public void createDashboardPanel() {
    	
    	//dialogBox = new DesignationDialog();
    	//dialogBox.addStyleName("right-dialog-box"); 
    	
//    	Image homeButtonImage = new Image("proj/client/Icons/homeiconpng.png");
//        homeButtonImage.addStyleName("icon-button"); 
    	
    	
    	VerticalPanel navBarPanel = new VerticalPanel();
        MaterialNavBar navBar = new MaterialNavBar();
        
        


        
        navBar.addStyleName("nav-bar"); 
        
        MaterialLink spacer1 = new MaterialLink("");
        MaterialLink spacer2 = new MaterialLink("");
        MaterialLink spacer3 = new MaterialLink("");
        MaterialLink spacer4 = new MaterialLink("");
        
        spacer1.setWidth("150px"); // Adjust the width for spacing
        spacer2.setWidth("150px"); // Adjust the width for spacing
        spacer3.setWidth("150px"); // Adjust the width for spacing
        spacer4.setWidth("150px"); // Adjust the width for spacing
        
        MaterialLink homeLink = new MaterialLink("Home");
        MaterialLink designationLink = new MaterialLink("Designation");
        MaterialLink salaryLink = new MaterialLink("Salary");
        MaterialLink securityLink = new MaterialLink("Security");
        MaterialLink logoutLink = new MaterialLink("Logout");
        MaterialLink employeeLink = new MaterialLink("Employee");
        
       
        navBar.add(spacer1);
        navBar.add(homeLink);
        
        navBar.add(spacer2);
        navBar.add(employeeLink);
        
        navBar.add(spacer3);
        navBar.add(designationLink);
        
        navBar.add(spacer3);
        navBar.add(salaryLink);
        
        navBar.add(spacer4);
        navBar.add(securityLink);
        
        navBar.add(spacer1);
        navBar.add(logoutLink);
        
        
        navBarPanel.add(navBar);
        
        homeLink.addClickHandler(new ClickHandler(){
     			@Override
     			public void onClick(ClickEvent event) {
     	        	RootPanel.get().clear();

     	       	RootPanel.get().addStyleName("login-page");
//     	           
     	       	LoginView loginView = new LoginView();
     	       	 // Apply CSS class names to the widgets
     	           loginView.addStyleName("material-card");
     	           loginView.getUsernameTextBox().addStyleName("material-textbox");
     	           loginView.getPasswordTextBox().addStyleName("material-textbox");
     	           loginView.getLoginButton().addStyleName("material-button");
     	       	
     	       	
     	           //RootPanel.get().add(loginPanel);
     	           RootPanel.get().add(loginView);
     			}
             	
             });
        

        designationLink.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				// To show the dialog
				RootPanel.get().clear();
				WholeDesignationPage newpage = new WholeDesignationPage();
				newpage.createDesignationPage();
				
				
				
				
				
//				contentPanel.clear();
//				
//				desigInputPanel = new DesignationInput();
//				desigInputPanel.addStyleName("material-card");
//				desigInputPanel.getDesigname().addStyleName("material-textbox");
//				desigInputPanel.getMaxSalary().addStyleName("material-textbox");
//				desigInputPanel.getMinSalary().addStyleName("material-textbox");
//				
//				
//				contentPanel.add(desigInputPanel);
//				
	            //dialogBox.center();
	            //dialogBox.show();
			}
        	
        });
        

        salaryLink.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
//	        	SalaryRecordDialog salaryBox = new SalaryRecordDialog();
//	        	dialogBox.addStyleName("right-dialog-box");
//	        	salaryBox.center();
//	        	salaryBox.show();
				contentPanel.clear();
				SalaryRecordDialog srd = new SalaryRecordDialog();
				srd.addStyleName("material-card");
				//SalaryPanel salarybox = new SalaryPanel();
				contentPanel.add(srd);
			}
        	
        });
       
        
        securityLink.addClickHandler(new ClickHandler() {

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
        
        logoutLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				authServ.LogoutUser(new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(Void result) {
					
						RootPanel.get().clear();
						RootPanel.get().addStyleName("login-page");
				    	LoginView loginView = new LoginView();
				    	 // Apply CSS class names to the widgets
				        loginView.addStyleName("material-card");
				        loginView.getUsernameTextBox().addStyleName("material-textbox");
				        loginView.getPasswordTextBox().addStyleName("material-textbox");
				        loginView.getLoginButton().addStyleName("material-button");
				    	
				    	
				        //RootPanel.get().add(loginPanel);
				        RootPanel.get().add(loginView);
					}
					
				});
				
			}
        	
        });
        
        editEmployeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Employee selected = dataProvider.getList().get(selectedIndexEmployeeTable);
//				EmployeeSignUpPanel empsign = new EmployeeSignUpPanel(selected);
//				empsign.editableEmployee = selected;
//				empsign.center();
//				empsign.show();
				
				editPanel = new EmployeeForm(selected);
				editPanel.addStyleName("material-card");
				editPanel.getUsernameTextBox().addStyleName("material-textbox");
			    editPanel.getPasswordTextBox().addStyleName("material-textbox");
				editPanel.getSalaryTextBox().addStyleName("material-textbox");
				editPanel.getPasswordTextBox().addStyleName("material-textbox");
				
				//editPanel.setVisible(!leftSidePanel.isVisible());
				//leftSidePanel.getAddButton().addStyleName("material-button");
				//leftSidePanel.getCancelButton().addStyleName("material-button");
				//contentPanel.remove(leftSidePanel);
				contentPanel.add(editPanel);
			}
        });
        
        
        addEmployeeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				leftSidePanel.setVisible(!leftSidePanel.isVisible());
				
//				leftSidePanel = new EmployeeForm(add);
//				leftSidePanel.addStyleName("material-card");
//				leftSidePanel.getUsernameTextBox().addStyleName("material-textbox");
//				leftSidePanel.getPasswordTextBox().addStyleName("material-textbox");
//				leftSidePanel.getSalaryTextBox().addStyleName("material-textbox");
//				leftSidePanel.getPasswordTextBox().addStyleName("material-textbox");
				//leftSidePanel.setVisible(true);
				
				//leftSidePanel.getAddButton().addStyleName("material-button");
				//leftSidePanel.getCancelButton().addStyleName("material-button");
				
				contentPanel.add(leftSidePanel);
			}
        	
        });

        
  	
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
           
        
//        openDesignationDialog.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event) {
//				//DesignationDialog dialogBox = new DesignationDialog();
//	            //dialogBox.center();
//	            //dialogBox.show();
//			}
//        	
//        });
     
        
//        AddEmployeeButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				//EmployeeSignUpPanel empsign = new EmployeeSignUpPanel(add);
//				leftSidePanel = new EmployeeForm(add);
//				contentPanel.add(leftSidePanel);
//				//empsign.center();
//				//empsign.show();
//			}
//        	
//        });


       
//        addSalRecordButton.addClickHandler(new ClickHandler(){
//			@Override
//			public void onClick(ClickEvent event) {
//	        	SalaryRecordDialog salaryBox = new SalaryRecordDialog();
//	        	salaryBox.center();
//	        	salaryBox.show();
//			}
//        	
//        });
        
        
//        homeButton.addClickHandler(new ClickHandler(){
//			@Override
//			public void onClick(ClickEvent event) {
//	        	RootPanel.get().clear();
//	        	RootPanel.get().add(lpc.createLoginPanel());
//			}
//        	
//        });
        

   
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
        
//        logoutUserSeeButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				authServ.LogoutUser(new AsyncCallback<Void>(){
//
//					@Override
//					public void onFailure(Throwable caught) {
//						Window.alert(caught.toString());
//					}
//
//					@Override
//					public void onSuccess(Void result) {
//						Window.alert("user logged out ");
//					}
//					
//				});
//				
//			}
//        	
//        });
        
//        securityButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
////				SecurityTabDialog newSecurityTab = new SecurityTabDialog();
////				//newSecurityTab.setPixelSize(1000, 500);
////				newSecurityTab.center();
////				newSecurityTab.show();
//				RootPanel.get().clear();
//				SecurityPanel sc = new SecurityPanel();
//				sc.createSecurityPanel();
//			}
//        	
//        	
//        	
//        });
//        editEmployeeButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				Employee selected = dataProvider.getList().get(selectedIndexEmployeeTable);
//				EmployeeSignUpPanel empsign = new EmployeeSignUpPanel(selected);
//				empsign.editableEmployee = selected;
//				empsign.center();
//				empsign.show();
//			}
//        	
//        	
//        	
//        });
        
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
        
        employeeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
					RootPanel.get().clear();
					dbp= new DashBoardPanelClient();
					dbp.createDashboardPanel();
			}
        	
        });
        
        leftSidePanel = new EmployeeForm(add);
		leftSidePanel.addStyleName("material-card");
		leftSidePanel.getUsernameTextBox().addStyleName("material-textbox");
		leftSidePanel.getPasswordTextBox().addStyleName("material-textbox");
		leftSidePanel.getSalaryTextBox().addStyleName("material-textbox");
		leftSidePanel.getPasswordTextBox().addStyleName("material-textbox");
        leftSidePanel.setVisible(false);

        
        
        employeeButtonsPanel.setSpacing(20);
        employeeButtonsPanel.add(addEmployeeButton);
        employeeButtonsPanel.add(editEmployeeButton);
        employeeButtonsPanel.add(removeEmployeeButton);
        
       // contentPanel.add(sideNav);
        contentPanel.add(employeeButtonsPanel);
        contentPanel.add(scrollPanel);
        contentPanel.add(leftSidePanel);
        
     
        contentPanel.setSpacing(40);


        
       
        wholenew.add(navBarPanel);
     
        wholenew.add(contentPanel);
        
        RootPanel.get().addStyleName("dashboard-style");
        RootPanel.get().add(wholenew);

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
