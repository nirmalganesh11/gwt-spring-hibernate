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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import com.google.gwt.event.shared.HandlerRegistration;

import proj.client.servicesClient.RoleServiceClient;
import proj.client.servicesClient.RoleServiceClientAsync;
import proj.shared.security.Permission;
import proj.shared.security.Role;

public class RolePanel extends VerticalPanel {
	
	 	private Button createRoleButton= new Button("Create Role");
	    private Button editRoleButton= new Button("Edit Role");
	    private Button deleteRoleButton= new Button("Delete Role");
	    private Button addFullUserButton = new Button("Add User");
	    
	    private CellTable<Role> roleTable= new CellTable<Role>();
        final SingleSelectionModel<Role> selectionModel = new SingleSelectionModel<>();

	    
	    private RoleServiceClientAsync roleServ = GWT.create(RoleServiceClient.class);
	    private List<Role> contextRoles;
	    
	    private VerticalPanel checkBoxPanel= new VerticalPanel();
	    
	    private HorizontalPanel buttonPanel= new HorizontalPanel();
	    
	    private VerticalPanel createRoleFormPanel = new VerticalPanel();
	    
	    private ListDataProvider<Role> dataProvider = new ListDataProvider<Role>();
	    
	    Set<Permission> selectedPermissions = new HashSet<>();
	    List<Permission> permissionlist = new ArrayList<>();
	    //private List<TextBox> listOfBoxes = new ArrayList<TextBox>();
	    
	    private TextBox roleName;
	    private TextBox roleDesc;
	    
	    private Role newRole;
	    private Role keyboardSelectedRole;
	    
	    private int selectedIndexInRoleTable=0;

	    public RolePanel() {
	    	
	        createRoleFormPanel.setVisible(false);

	        roleTable.setSelectionModel(selectionModel);

	        
	        dataProvider.addDataDisplay(roleTable);
	        
	        fetchDataRefresh();
	        
	        TextColumn<Role> roleIdColumn = new TextColumn<Role>() {
	            @Override
	            public String getValue(Role role) {
	                return String.valueOf(role.getRole_id());
	            }
	        };
	        roleTable.addColumn(roleIdColumn, "Role ID");

	        TextColumn<Role> roleNameColumn = new TextColumn<Role>() {
	            @Override
	            public String getValue(Role role) {
	                return role.getRoleName();
	            }
	        };
	        roleTable.addColumn(roleNameColumn, "Role Name");

	        TextColumn<Role> roleDescriptionColumn = new TextColumn<Role>() {
	            @Override
	            public String getValue(Role role) {
	                return role.getRoleDescription();
	            }
	        };
	        roleTable.addColumn(roleDescriptionColumn, "Role Description");

	        TextColumn<Role> usersCountColumn = new TextColumn<Role>() {
	            @Override
	            public String getValue(Role role) {
	                return String.valueOf(role.getUserCount());
	            }
	        };
	        roleTable.addColumn(usersCountColumn, "Users Count");


	        createRoleButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					//toggleTextBoxes(listOfBoxes);
					if(createRoleFormPanel.isVisible()) {
						createRoleFormPanel.setVisible(false);
					}else {
						createRoleFormPanel = showCreateRoleForm();
						add(createRoleFormPanel);
						createRoleFormPanel.setVisible(true);
					}
					
				}

	        });
	        
	        editRoleButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					keyboardSelectedRole = dataProvider.getList().get(selectedIndexInRoleTable);
					roleName.setText(keyboardSelectedRole.getRoleName());
					roleDesc.setText(keyboardSelectedRole.getRoleDescription());
					addCheckBoxesAndKeepCount();
					updateCheckboxes();
				}
	 
	        });
	        
	        
	        selectionModel.addSelectionChangeHandler(new Handler() {
	            @Override
	            public void onSelectionChange(SelectionChangeEvent event) {
	                int selectedIndex = roleTable.getKeyboardSelectedRow();
	                selectedIndexInRoleTable = selectedIndex;
	                if (selectedIndex >= 0) {
	                    System.out.println("Selected Index: " + selectedIndex);
	                } else {    
	                    System.out.println("No row selected");
	                }
	            }
	        });
	        
	        addFullUserButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					UserSelectionDialog eventbro = new UserSelectionDialog();
					eventbro.center();
					eventbro.show();
				}
	     
	        	
	        });
	        
	        
	        setSpacing(15);
	        // Add buttons to the horizontal panel
	        buttonPanel.setSpacing(15);
	        buttonPanel.add(createRoleButton);
	        buttonPanel.add(editRoleButton);
	        buttonPanel.add(deleteRoleButton);
	        buttonPanel.add(addFullUserButton);
	        
	        
	        add(roleTable);
	        add(buttonPanel);
	       // add(checkBoxPanel);
	        
	    }
	    
	    
	    public void toggleTextBoxes(List<TextBox> textboxes) {
	        for (TextBox textbox : textboxes) {
	        	 textbox.setEnabled(!textbox.isEnabled());
	        }
	    }
	    
	    
	    public VerticalPanel showCreateRoleForm() {
	    	VerticalPanel overAll = new VerticalPanel();
	    	
	    	HorizontalPanel subsidy = new HorizontalPanel();
	    	
	    	

	    	VerticalPanel roleForm = new VerticalPanel();
	    	Button saveButton = new Button("SAVE");
	    	
	    	Label roleNameLabel = new Label("Role Name");
	    	roleName = new TextBox();
	    	
	    	Label roleDescLabel = new Label("Role Description");
	    	roleDesc = new TextBox();
	    	
	    	saveButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if(roleName.getText() != "") {
						
						newRole = new Role(roleName.getText());
						newRole.setRoleDescription(roleDesc.getText());
						newRole.setRolePermissions(selectedPermissions);
						
						roleServ.addRole(newRole, new AsyncCallback<String> () {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.toString());
							}

							@Override
							public void onSuccess(String result) {
								Window.alert(result+"this is gwt thing");
								fetchDataRefresh();
							}
						});
						
						
					}
					
				}
	    		
	    	});
	    	
	    	
	       
	        addCheckBoxesAndKeepCount();

	    	roleForm.add(roleNameLabel);
	    	roleForm.add(roleName);
	    	roleForm.add(roleDescLabel);
	    	roleForm.add(roleDesc);
	    	roleForm.add(saveButton);
	    	
	    	subsidy.add(roleForm);
	    	subsidy.add(checkBoxPanel);
	    	
	    	overAll.add(subsidy);
	    	//roleForm.add(checkBoxPanel);
	    	return overAll;
	    }
	    
	    public void  addCheckBoxesAndKeepCount() {
	    	checkBoxPanel.clear();
	    	 for (final Permission permission : permissionlist) {
		            CheckBox checkBox = new CheckBox(permission.getPermissionName());
		            checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		                @Override
		                public void onValueChange(ValueChangeEvent<Boolean> event) {
		                    boolean isChecked = event.getValue();
		                    if (isChecked) {
		                        selectedPermissions.add(permission);
		                    } else {
		                        selectedPermissions.remove(permission);
		                    }
		                }
		            });
		            checkBoxPanel.add(checkBox);
		        }
	    }
	    
	    public void fetchDataRefresh() {
	    	
	    	permissionlist.clear();
	    	
	    	 roleServ.getAllPermissions(new AsyncCallback<List<Permission>> () {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fialed to get all permissions");
					}

					@Override
					public void onSuccess(List<Permission> result) {
						//Window.alert("fetched  perms");
						permissionlist.addAll(result);
					}	
		    });
	    	
	    	roleServ.getAllRoles(new AsyncCallback<List<Role>>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.toString());
				}

				@Override
				public void onSuccess(List<Role> result) {
					contextRoles = result;
					dataProvider.getList().clear();
					dataProvider.setList(result);
				}
		    	
		    	
		    });
	    	roleTable.redraw();
	    }
	    public void updateCheckboxes() {
	    	Set<Permission> permissions =keyboardSelectedRole.getRolePermissions(); 
	    	List<String> permStrings = new ArrayList<String>();
	    	for(Permission perm: permissions) {
	    		permStrings.add(perm.getPermissionName());
	    	}
	    	
	    	for (int i = 0; i < checkBoxPanel.getWidgetCount(); i++) {
	    	    Widget widget = checkBoxPanel.getWidget(i);

	    	    if (widget instanceof CheckBox) {
	    	        CheckBox checkbox = (CheckBox) widget;
	    	        String checkboxPermission = checkbox.getText(); 
	    	      
	    	        if (permStrings.contains(checkboxPermission)) {
	    	            checkbox.setValue(true); 
	    	        } else {
	    	            checkbox.setValue(false); 
	    	        }
	    	    }
	    	}
	    }
	    

}
