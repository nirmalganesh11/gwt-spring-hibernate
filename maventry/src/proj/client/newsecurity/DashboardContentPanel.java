package proj.client.newsecurity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;
import proj.client.Panels.PasswordMaskCell;
import proj.client.Panels.EventHandlingBus.ButtonClickEvent;
import proj.client.Panels.EventHandlingBus.ButtonClickEventHandler;
import proj.client.Panels.EventHandlingBus.EventBusBoy;
import proj.client.servicesClient.AuthenticationService;
import proj.client.servicesClient.AuthenticationServiceAsync;
import proj.client.servicesClient.EmployeeServiceClient;
import proj.client.servicesClient.EmployeeServiceClientAsync;
import proj.shared.Employee;

public class DashboardContentPanel extends VerticalPanel{
	public CellTable<Employee> employeeTable;
	final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<>();
	
    public ListDataProvider<Employee> dataProvider;
    private EmployeeServiceClientAsync empServ= GWT.create(EmployeeServiceClient.class);;
    private AuthenticationServiceAsync authServ= GWT.create(AuthenticationService.class);;
    private boolean ascending = true;
    private int selectedIndexEmployeeTable =0;
    private Employee add =null;
    
    VerticalPanel contentPanel = new VerticalPanel();
	VerticalPanel totalPanel = new VerticalPanel();
	VerticalPanel navBarPanel = new VerticalPanel();
    
	public DashboardContentPanel() {
		
		
		contentPanel.addStyleName("vertical-panel");
        

        MaterialNavBar navBar = new MaterialNavBar();
        
        
        navBar.addStyleName("nav-bar"); 
        
        MaterialLink spacer1 = new MaterialLink("");
        MaterialLink spacer2 = new MaterialLink("");
        MaterialLink spacer3 = new MaterialLink("");
        MaterialLink spacer4 = new MaterialLink("");
        
        spacer1.setWidth("70px"); // Adjust the width for spacing
        spacer2.setWidth("70px"); // Adjust the width for spacing
        spacer3.setWidth("70px"); // Adjust the width for spacing
        spacer4.setWidth("70px"); // Adjust the width for spacing
        
        MaterialLink homeLink = new MaterialLink("Home");
        MaterialLink designationLink = new MaterialLink("Designation");
        MaterialLink salaryLink = new MaterialLink("Salary");
        MaterialLink securityLink = new MaterialLink("Security");
        MaterialLink logoutLink = new MaterialLink("Logout");
        
       
        navBar.add(spacer1);
        navBar.add(homeLink);
        navBar.add(spacer2);
        navBar.add(designationLink);
        navBar.add(spacer3);
        navBar.add(salaryLink);
        navBar.add(spacer4);
        navBar.add(securityLink);
        navBar.add(spacer1);
        navBar.add(logoutLink);
        
        
        navBarPanel.add(navBar);
		
		
		
		
	
    	
	
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
        
        
        
        selectionModel.addSelectionChangeHandler(new Handler() {
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
     		   Window.alert("fetched");
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
        
        
        VerticalPanel verticalPanel = new VerticalPanel();
        
     
        // verticalPanel.add(scrollPanel);
         verticalPanel.setSpacing(15);
         
         VerticalPanel secondSide = new VerticalPanel();
         
         HorizontalPanel employeeButtonsPanel = new HorizontalPanel();
         employeeButtonsPanel.setSpacing(20);
         
         secondSide.add(employeeButtonsPanel);
         secondSide.add(scrollPanel);
         secondSide.setSpacing(20);
         
         HorizontalPanel hq = new HorizontalPanel();
         
         hq.setSpacing(40);
         hq.add(verticalPanel);
         //hq.add(scrollPanel);
         hq.add(secondSide);
         
         contentPanel.add(hq);
         totalPanel.add(navBarPanel);
         totalPanel.add(contentPanel);
         

         
         add(totalPanel);
         
         
         
        
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
