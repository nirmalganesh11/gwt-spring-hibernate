package proj.client.Security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import proj.client.servicesClient.FullUserServiceClient;
import proj.client.servicesClient.FullUserServiceClientAsync;
import proj.client.servicesClient.RoleServiceClient;
import proj.client.servicesClient.RoleServiceClientAsync;
import proj.shared.security.FullUser;
import proj.shared.security.Permission;
import proj.shared.security.Role;

public class UserPanel extends VerticalPanel{
	
	Button createButton= new Button("Create User");
	Button editButton = new Button("Edit User");
	
	Button deleteButton= new Button("Delete User");
	Button addRolesButton= new Button("Add Roles");
	FullUserServiceClientAsync fullUserServ = GWT.create(FullUserServiceClient.class);
	
	HorizontalPanel hpPanel;
	VerticalPanel newVert = new VerticalPanel();;
	
	FullUser fulluser;
	
	CellTable<FullUser> userTable;
	ListDataProvider<FullUser> dataProvider;
	
	final SingleSelectionModel<FullUser> selectionModel = new SingleSelectionModel<>();
    private RoleServiceClientAsync roleServ = GWT.create(RoleServiceClient.class);

    List<Role> selectedRoles = new ArrayList<>();
    List<Role> rolesList = new ArrayList<>();
	
	Button save = new Button ("SAVE");
	
	TextBox empIdBox= new TextBox();
	TextBox userIdBox = new TextBox();
	TextBox firstNameBox = new TextBox();
	TextBox lastNameBox = new TextBox();
	TextBox titleBox = new TextBox();
	TextBox locationBox = new TextBox();
	TextBox emailBox = new TextBox();
	TextBox phnoBox = new TextBox();
	TextBox passwordBox = new TextBox();
	
	int selectedIndexInUserTable =0;
	
	
	VerticalPanel createUserFormPanel  = new VerticalPanel();
	VerticalPanel roleCheckBoxesPanel = new VerticalPanel();
	
	
	FullUser keyboardSelectedFullUser;
	
	
    public UserPanel(){
    	
    	hpPanel = new HorizontalPanel();
    	hpPanel.setSpacing(20);
    	hpPanel.add(createButton);
//    	hpPanel.add(addRolesButton);
    	hpPanel.add(editButton);
    	hpPanel.add(deleteButton);
    	
    	createUserFormPanel.setVisible(false);
    	
        
    	dataProvider = new ListDataProvider<FullUser>();
        
        userTable = new CellTable<FullUser>();

        userTable.setSelectionModel(selectionModel);
        dataProvider.addDataDisplay(userTable);
        

    	TextColumn<FullUser> empIdColumn = new TextColumn<FullUser> () {

			@Override
			public String getValue(FullUser object) {
				return String.valueOf(object.getEmpId());
			}
    	};
    	userTable.addColumn(empIdColumn,"Emp ID");
    	
    	TextColumn<FullUser> userIdColumn = new TextColumn<FullUser>() {

			@Override
			public String getValue(FullUser object) {
				return object.getUserId();
			}
    		
    	};
    	userTable.addColumn(userIdColumn,"User Id");
    	
    	TextColumn<FullUser> firstNameColumn= new TextColumn<FullUser>() {

			@Override
			public String getValue(FullUser object) {
				return object.getFirstName();
			}
    	};
    	userTable.addColumn(firstNameColumn,"First Name");
    	
    	TextColumn<FullUser> lastNameColumn= new TextColumn<FullUser>() {

			@Override
			public String getValue(FullUser object) {
				return object.getLastName();
			}
    	};
    	userTable.addColumn(lastNameColumn,"Last Name");
    	
    	selectionModel.addSelectionChangeHandler(new Handler() {
	            @Override
	            public void onSelectionChange(SelectionChangeEvent event) {
	                int selectedIndex = userTable.getKeyboardSelectedRow();
	                selectedIndexInUserTable = selectedIndex;
	                if (selectedIndex >= 0) {
	                    System.out.println("Selected Index: " + selectedIndex);
	                } else {    
	                    System.out.println("No row selected");
	                }
	            }
	        });
    	
    	createButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(createUserFormPanel.isVisible()) {
					createUserFormPanel.setVisible(false);
				}else {
					createUserFormPanel = createUserForm();
					add(createUserFormPanel);
					createUserFormPanel.setVisible(true);
				}
			}

    	});
    	
    	editButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				keyboardSelectedFullUser = dataProvider.getList().get(selectedIndexInUserTable);
				empIdBox.setText(String.valueOf(keyboardSelectedFullUser.getEmpId()));
				userIdBox.setText(keyboardSelectedFullUser.getUserId());
				passwordBox.setText(keyboardSelectedFullUser.getPassword());
				firstNameBox.setText(keyboardSelectedFullUser.getFirstName());
			    lastNameBox.setText(keyboardSelectedFullUser.getLastName());
			    titleBox.setText(keyboardSelectedFullUser.getTitle());
			    locationBox.setText(keyboardSelectedFullUser.getLocation());
			    phnoBox.setText(keyboardSelectedFullUser.getPhno());
			    emailBox.setText(keyboardSelectedFullUser.getEmail());

			}

    	});
    	
    	deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				keyboardSelectedFullUser = dataProvider.getList().get(selectedIndexInUserTable);
				if(keyboardSelectedFullUser == null) {
					Window.alert("selected Index null val : "+selectedIndexInUserTable);
				}
//				Window.alert("selected Index : "+selectedIndexInUserTable);
//				Window.alert(keyboardSelectedFullUser.getFirstName());
				fullUserServ.deleteFullUser(keyboardSelectedFullUser, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Could not delete User");
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);	
					}
				});

			}
    		
    		
    	});
    	
    	addRolesButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showRolesSelection();
				addCheckBoxesAndKeepCount();
				//add(roleCheckBoxesPanel);
			}

    	});
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	populateTable();
    	
    	
    	add(userTable);
    	add(hpPanel);
    	//add(createUserForm());
    	
    	
    }
    
    public VerticalPanel createUserForm() {
    	
    
    	
    	save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				int employeeId = Integer.parseInt(empIdBox.getText());
				String userid = userIdBox.getText();
				String password = passwordBox.getText();
				String firstname = firstNameBox.getText();
				String lastname = lastNameBox.getText();
				
				String title = titleBox.getText();
				String location = titleBox.getText();
				String email = emailBox.getText();
				String phno = phnoBox.getText();
				
				FullUser newuser = new FullUser(employeeId,userid,password,firstname,lastname);
				newuser.setTitle(title);
				newuser.setLocation(location);
				newuser.setEmail(email);
				newuser.setPhno(phno);
				
				newuser.setRolesList(selectedRoles);
				
				
				fullUserServ.addFullUser(newuser, new AsyncCallback<String> () {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failed to add fulluser");
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
						populateTable();
					}
					
				});
				
				
			}
    			
    	});
    	
    	
    	newVert.add(new Label("Enter Emp ID:"));
    	newVert.add(empIdBox);
    	newVert.add(new Label("Enter User ID:"));
    	newVert.add(userIdBox);
    	newVert.add(new Label("Password Box:"));
    	newVert.add(passwordBox);
    	newVert.add(new Label("First Name:"));
    	newVert.add(firstNameBox);
    	newVert.add(new Label("Last Name:"));
    	newVert.add(lastNameBox);
    	
    	newVert.add(addRolesButton);
    	
    	
    	Button optionalButton = new Button("Open Optional Data");
    	optionalButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				optionalFields();
			}
    	});
    	newVert.add(optionalButton);
    	
    	newVert.add(save);
    	
    	return newVert;
    }
    
    public void optionalFields() {
    	
    	newVert.add(new Label("----------Optional Params----"));
    	newVert.add(new Label("Title:"));
    	newVert.add(titleBox);
    	newVert.add(new Label("Location:"));
    	newVert.add(locationBox);
    	newVert.add(new Label("Email: "));
    	newVert.add(emailBox);
    	newVert.add(new Label("Phno:"));
    	newVert.add(phnoBox);
    	
    }
    
    
    
    public void populateTable() {
    	
    	fullUserServ.getAllFullUsers(new AsyncCallback<List<FullUser>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<FullUser> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				
			}
    	});
    	userTable.redraw();
    }
    public void showRolesSelection() {
    	roleServ.getAllRoles(new AsyncCallback<List<Role>> () {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("could not fetch all the roles");
			}

			@Override
			public void onSuccess(List<Role> result) {
				rolesList.clear();
				rolesList.addAll(result);
			}
    		
    	});
    	

    }
    
    public void  addCheckBoxesAndKeepCount() {
    	roleCheckBoxesPanel.clear();
    	 for (final Role role : rolesList) {
	            CheckBox checkBox = new CheckBox(role.getRoleName());
	            checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
	                @Override
	                public void onValueChange(ValueChangeEvent<Boolean> event) {
	                    boolean isChecked = event.getValue();
	                    if (isChecked) {
	                        selectedRoles.add(role);
	                    } else {
	                        selectedRoles.remove(role);
	                    }
	                }
	            });
	            roleCheckBoxesPanel.add(checkBox);
	        }
    	 
    	 newVert.add(roleCheckBoxesPanel);
    }
    
    
    
}
