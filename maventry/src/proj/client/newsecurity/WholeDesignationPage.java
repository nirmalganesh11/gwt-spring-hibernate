package proj.client.newsecurity;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBar;
import proj.client.Panels.DashBoardPanelClient;
import proj.client.Panels.PasswordMaskCell;
import proj.client.Panels.SalaryRecordDialog;
import proj.client.Panels.EventHandlingBus.EventBusBoy;
import proj.client.Security.SecurityPanel;
import proj.client.servicesClient.DesigServiceClient;
import proj.client.servicesClient.DesigServiceClientAsync;
import proj.shared.DesigClass;
import proj.shared.Employee;

public class WholeDesignationPage {
	
	private VerticalPanel finalPanel = new VerticalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	
	private VerticalPanel designationButtonPanel = new VerticalPanel();
	VerticalPanel navBarPanel = new VerticalPanel();
    MaterialNavBar navBar = new MaterialNavBar();
    
    private DashBoardPanelClient dbp;
    
    MaterialLink homeLink = new MaterialLink("Home");
    MaterialLink designationLink = new MaterialLink("Designation");
    MaterialLink salaryLink = new MaterialLink("Salary");
    MaterialLink securityLink = new MaterialLink("Security");
    MaterialLink logoutLink = new MaterialLink("Logout");
    MaterialLink employeeLink = new MaterialLink("Employee");
    
    DesignationInput desigInputPanel;
    
    public CellTable<DesigClass> desigTable = new CellTable<>();;
	final SingleSelectionModel<DesigClass> selectionModel = new SingleSelectionModel<>();
	public ListDataProvider<DesigClass> dataProvider = new ListDataProvider<DesigClass>();;
	public DesigClass desigVal;
	private DesigServiceClientAsync desigserv;
    
    ScrollPanel scrollPanel = new ScrollPanel();
	
    private int selectedIndexDesigTable =0;
    private DesigClass add =null;
	
	public WholeDesignationPage(){
		setUpUi();
	}
	public void fetchData() {
		desigserv.getDesignationsList(new AsyncCallback<List<DesigClass>>(){
	       	   @Override
	       	   public void onFailure(Throwable caught) {
	       		   Window.alert(caught.toString());	
	       	   }
	       	   @Override
	       	   public void onSuccess(List<DesigClass> result) {
	    		String s ="";
	    		for(DesigClass ds: result) {
	    			s=s+ds.getDesigname()+"--";
	    		}
	       		   //Window.alert(s);
	       		//contentPanel.add(scrollPanel);
//	       		   dataProvider.getList().clear();
	       		   dataProvider.getList().addAll(result);
	       		   dataProvider.flush();
	       		   desigTable.redraw();
	       	   } 
	          });    			
	}
	public void setUpUi() {
		
		desigTable.setPageSize(50);
        desigTable.setSelectionModel(selectionModel);
        
		 
		desigserv = GWT.create(DesigServiceClient.class);
		
        navBar.addStyleName("nav-bar"); 
        
        MaterialLink spacer1 = new MaterialLink("");
        MaterialLink spacer2 = new MaterialLink("");
        MaterialLink spacer3 = new MaterialLink("");
        MaterialLink spacer4 = new MaterialLink("");
        
        spacer1.setWidth("150px"); 
        spacer2.setWidth("150px"); 
        spacer3.setWidth("150px"); 
        spacer4.setWidth("150px"); 
        

        
       
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
        
        
        Button addDesignationButton = new Button("Add Designation");
        Button deleteDesignationButton = new Button("Delete Designation");
        Button editDesignationButton = new Button("Edit Designation");
        designationButtonPanel.setSpacing(20);
        designationButtonPanel.add(addDesignationButton);
        designationButtonPanel.add(deleteDesignationButton);
        designationButtonPanel.add(editDesignationButton);
        
        addDesignationButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				desigInputPanel = new DesignationInput();
				desigInputPanel.addStyleName("material-card");
				desigInputPanel.getDesigname().addStyleName("material-textbox");
				desigInputPanel.getMaxSalary().addStyleName("material-textbox");
				desigInputPanel.getMinSalary().addStyleName("material-textbox");
				
				
				contentPanel.add(desigInputPanel);
			}
        });
        
        
        
        
        contentPanel.setSpacing(25);
        contentPanel.add(designationButtonPanel);
        
        
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

				RootPanel.get().clear();
				WholeDesignationPage newpage = new WholeDesignationPage();
				newpage.createDesignationPage();
			}
        	
        });
        

        salaryLink.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				contentPanel.clear();
				SalaryRecordDialog srd = new SalaryRecordDialog();
				srd.addStyleName("material-card");
				contentPanel.add(srd);
			}
        	
        });
       
        
        securityLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				SecurityPanel sc = new SecurityPanel();
				sc.createSecurityPanel();
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
        
        fetchData();
		
        
        TextColumn<DesigClass> nameColumn = new TextColumn<DesigClass>() {
            @Override
            public String getValue(DesigClass desig) {
                return desig.getDesigname();
            }
        };
        TextColumn<DesigClass> salaryMinColumn = new TextColumn<DesigClass>() {
            @Override
            public String getValue(DesigClass desig) {
                return String.valueOf(desig.getMinsalary());
            }
        };
        TextColumn<DesigClass> salaryMaxColumn = new TextColumn<DesigClass>() {
            @Override
            public String getValue(DesigClass desig) {
                return String.valueOf(desig.getMaxsalary());
            }
        };
        
        desigTable.addColumn(nameColumn, "Name");
        desigTable.addColumn(salaryMinColumn, "Min Salary");
        desigTable.addColumn(salaryMaxColumn,"Max Salary");
        
        

        
        selectionModel.addSelectionChangeHandler(new Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                int selectedIndex = desigTable.getKeyboardSelectedRow();
                selectedIndexDesigTable = selectedIndex;
                if (selectedIndex >= 0) {
                   
                    System.out.println("Selected Index: " + selectedIndex);
                } else {
                    
                    System.out.println("No row selected");
                }
            }
        });
        
        
        
        scrollPanel.setWidget(desigTable);
        scrollPanel.setSize("700px", "500px");
		
		
		
	}
	
	public void createDesignationPage() {
		 
        finalPanel.add(navBarPanel);
        finalPanel.add(contentPanel);
        
        
		RootPanel.get().clear();
		RootPanel.get().add(finalPanel);

	}
	
	
	
}
