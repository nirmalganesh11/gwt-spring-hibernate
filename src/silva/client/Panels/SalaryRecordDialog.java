package silva.client.Panels;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;
//import com.github.gwtbootstrap.datepicker.client.ui.DateBox;

import silva.client.AppService;
import silva.client.AppServiceAsync;
import silva.client.Panels.EventHandlingBus.EventBusBoy;
import silva.shared.DesigClass;
import silva.shared.Employee;
import silva.shared.SalaryClass;
import silva.client.Panels.EventHandlingBus.*;
import com.google.gwt.user.datepicker.client.DateBox;

public class SalaryRecordDialog extends DialogBox {
	private final AppServiceAsync app = GWT.create(AppService.class);
    private ListBox designameDropdown;
    private ListBox desigBasedEmp;
    
    private Label fullname;
    private Label initialSal;
    private Label username;
    
    private TextBox amountBox;
    
    private List<Employee> empList;
    private List<SalaryClass> salList;
    
    private CellTable<SalaryClass> salaryTable ;
    private ListDataProvider<SalaryClass> dataProvider;
    
    private DateBox obdk;
    private DesigClass desigclass;
    
    public void fillEmployees(List<Employee> result) {
    	empList = result;
    }
    
    
    public SalaryRecordDialog(){
    	
    	 setText("Salary Record Box");
    	 salaryTable = new CellTable<>();
    	 dataProvider = new ListDataProvider<>();
    	 dataProvider.addDataDisplay(salaryTable);
    	 	
    	
    	 VerticalPanel mainPanel = new VerticalPanel();
    	 mainPanel.setSpacing(10);
    	 
    	 //Button saveChanges = new Button("Save");
    	 Button cancel = new Button("Close");
    	 
    	 designameDropdown = new ListBox();
    	 desigBasedEmp = new ListBox();
    	 
    	 amountBox = new TextBox();
    	 
    	 Button getButton = new Button("Get");
    	 Button addSalary = new Button("Add");
    	 
    	 fullname = new Label("fullname");
    	 initialSal = new Label("initialsal");
    	 username = new Label("Username: ");
    	 
    	 
    	 app.getDesignations(new AsyncCallback<List<String>>(){
 			@Override
 			public void onFailure(Throwable caught) {
 				Window.alert(caught.toString());
 			}

 			@Override
 			public void onSuccess(List<String> result) {
 				for (String designame : result){
 			            designameDropdown.addItem(designame);
 			    }
 				Window.alert("added few fields in desigdrowdown");
 			}
         });
    	 
    	//keeping the default role when no changes are made
    	// String desigValueGlobal[] = {designameDropdown.getValue(0)};
    	//Window.alert(designameDropdown.getValue(0));
    	 String desigValueGlobal[] = {"Ceo"};
         //String[] desigValueGlobal ={"Ceo"};
         app.getEmployeesOnDesig(desigValueGlobal[0], new AsyncCallback<List<Employee>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("failed to bring back the big employees based on designame");
				}

				@Override
				public void onSuccess(List<Employee> result) {
					fillEmployees(result);
					desigBasedEmp.clear();
					for(Employee emps: result ) {
						desigBasedEmp.addItem(emps.getName());
					}
					Window.alert("added employees on designame");	
				}
			
			});
         
         
         designameDropdown.addChangeHandler(new ChangeHandler() {
 			@Override
 			public void onChange(ChangeEvent event) {
 				int selectedIndex = designameDropdown.getSelectedIndex();
 				String desigValue = designameDropdown.getValue(selectedIndex);
 				desigValueGlobal[0]= desigValue;
 				
 				app.getEmployeesOnDesig(desigValue, new AsyncCallback<List<Employee>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failed to bring back the big employees based on designame");
					}

					@Override
					public void onSuccess(List<Employee> result) {
						if(result != null) {
						fillEmployees(result);
						desigBasedEmp.clear();
						for(Employee emps: result ) {
							desigBasedEmp.addItem(emps.getName());
						}
						
						
						Window.alert("added employees on designame");
						}
					}
 				
 				});
 				
 			}
         });
         
         //String[] empUsernameGlobal= {""};
         int selectedIndexEmp[]= {0};
         desigBasedEmp.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = desigBasedEmp.getSelectedIndex();
				selectedIndexEmp[0] = selectedIndex;
 				///String desigValue = desigBasedEmp.getValue(selectedIndex);
 				//Employee selectedEmployee = empList.get(selectedIndex);
 				//initialSal.setText(selectedEmployee.getSalary()+"");
 				//fullname.setText(selectedEmployee.getName());
				//empUsernameGlobal[0] = desigValue;
				
			}
         });
         
         getButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String designameval = desigValueGlobal[0];
		         app.getDesignation(designameval, new AsyncCallback<DesigClass> () {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(DesigClass result) {
						desigclass = result;
						Window.alert(desigclass.toString());
						
					}
		        	 
		         });
				
				
				
				
				
				   if(empList !=null) {
			        	 Employee ob = empList.get(selectedIndexEmp[0]);
			        	 fullname.setText(ob.getName());
			        	 initialSal.setText(ob.getSalary()+"");
			        	 username.setText(ob.getUsername());
			        	 
			        	 app.getSalariesEmp(ob.getUsername(),new AsyncCallback<List<SalaryClass>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.toString());
							}

							@Override
							public void onSuccess(List<SalaryClass> result) {
								dataProvider.getList().clear();
								dataProvider.getList().addAll(result);
								salaryTable.redraw();
							}
			        		 
			        	 }); 
			         }
				   else
					   Window.alert("Employee list is empty");
			}
        	 
         });
     
         obdk = new DateBox();
         
         addSalary.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				   SalaryClass ob = new SalaryClass();
				   int amount = Integer.parseInt(amountBox.getText());
				   Date selectedDate = obdk.getValue();
				   if(desigclass.getMinsalary() <= amount && amount <= desigclass.getMaxsalary() && selectedDate != null) {
					   ob.setAmount(amount);
					   ob.setUsername(username.getText());
				       ob.setReceivedDate(selectedDate);
				       //Window.alert(selectedDate.toString());
				       app.saveSalaryRecord(ob, new AsyncCallback<String>(){
				    	   @Override
				    	   public void onFailure(Throwable caught) {
				    		   	Window.alert(caught.toString());
				    	   }
				    	   @Override
				    	   public void onSuccess(String result) {
				    		   Window.alert(result);
				    		   dataProvider.getList().add(ob);
						       salaryTable.redraw();
				    	   }
				       });
				   }else {
					   Window.alert("range is not correct or date is not added");
				   }    
			}
        	 
         });
         
         
         
         
         
//         DatePicker datePicker = new DatePicker();
//         // Add a ValueChangeHandler to respond to date selection events
//         datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
//             @Override
//             public void onValueChange(ValueChangeEvent<Date> event) {
//                 Date selectedDate = event.getValue();
//                 Window.alert(selectedDate.toString());
//             }
//         });
//         
                  
         TextColumn<SalaryClass> dateColumn = new TextColumn<SalaryClass>() {
             @Override
             public String getValue(SalaryClass salary) {
            	 Date dateFormat = salary.getReceivedDate();
            	 String desiredFormatPattern = "dd-MM-yyyy";
                 DateTimeFormat dtf = DateTimeFormat.getFormat(desiredFormatPattern);
                 String formattedDate = dtf.format(dateFormat);
                 return formattedDate;
             }
         };
         TextColumn<SalaryClass> amountColumn = new TextColumn<SalaryClass>() {
             @Override
             public String getValue(SalaryClass salary) {
                 return salary.getAmount()+"";
             }
         };
         
         salaryTable.addColumn(dateColumn,"Date");
         salaryTable.addColumn(amountColumn, "Amount");
         
         
         
         
         ScrollPanel scrollPanel = new ScrollPanel();
         scrollPanel.setWidget(salaryTable);
         scrollPanel.setSize("250px", "150px");

         
         
         
         
         
         
    	 
         
         
    	 
    	 
    	HorizontalPanel addingPanel =  new HorizontalPanel();
    	addingPanel.add(obdk);
    	addingPanel.add(amountBox);
    	addingPanel.add(addSalary);
    	 
    	 
        // Add buttons to a horizontal panel for layout
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setSpacing(10);
        //buttonPanel.add(saveChanges);
        buttonPanel.add(cancel);
        
        HorizontalPanel fieldsPanel = new HorizontalPanel();
        fieldsPanel.add(new Label("Full Name:"));
        fieldsPanel.add(fullname);
        fieldsPanel.setSpacing(30);
        fieldsPanel.add(new Label("Initial Salary:"));
        fieldsPanel.add(initialSal);
        fieldsPanel.add(new Label("Username: "));
        fieldsPanel.add(username);
        
       
        cancel.addClickHandler(event ->{
        	hide();
        });
        // Add all components to the main panel
        mainPanel.add(new Label("Choose Designation"));
        mainPanel.add(designameDropdown);
        mainPanel.add(new Label("Choose Employee"));
        mainPanel.add(desigBasedEmp);
        mainPanel.add(getButton);
        
        //mainPanel.add(addSalary);
        //mainPanel.add(obdk);
        //mainPanel.add(datePicker);	
        mainPanel.add(fieldsPanel);
        mainPanel.add(new Label("Add Record"));
        mainPanel.add(addingPanel);
        mainPanel.add(scrollPanel);
        
        //mainPanel.add(obd);
        //mainPanel.add(new Label("Enter Min Salary"));
        //mainPanel.add(minSalary);
        mainPanel.add(buttonPanel);

        // Set the main panel as the content of the dialog box
        setWidget(mainPanel);
    }
    
    
    
    
    
    

    // Add any additional methods or logic as needed
}