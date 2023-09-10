package proj.client.Security;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;

import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import proj.client.servicesClient.FullUserServiceClient;
import proj.client.servicesClient.FullUserServiceClientAsync;
import proj.shared.security.FullUser;

public class UserSelectionDialog extends DialogBox {
	
	VerticalPanel finalPanel = new VerticalPanel();
	
	CellTable<FullUser> fullUserTable = new CellTable<FullUser>();
	ListDataProvider<FullUser> dataProvider = new ListDataProvider<FullUser>();
	MultiSelectionModel<FullUser> selectionModel = new MultiSelectionModel<>();
	
	Button cancelButton = new Button("Cancel");
	Button saveChangesButton = new Button("Cancle");
	
	Set<FullUser> selectedUsersFromTable;
	FullUserServiceClientAsync fullUserServ = GWT.create(FullUserServiceClient.class);;

	public UserSelectionDialog() {
		

        dataProvider.addDataDisplay(fullUserTable);
    	fullUserTable.setSelectionModel(selectionModel);
    	
    	populateTable();
    	
      	
    	Column<FullUser, Boolean> checkboxColumn = new Column<FullUser, Boolean>(new CheckboxCell(true, true)) {
            @Override
            public Boolean getValue(FullUser object) {
                return selectionModel.isSelected(object);
            }
        };

        checkboxColumn.setFieldUpdater(new FieldUpdater<FullUser, Boolean>() {
            @Override
            public void update(int index, FullUser object, Boolean value) {
                selectionModel.setSelected(object, value);
            }
        });


        fullUserTable.addColumn(checkboxColumn, "");

    	TextColumn<FullUser> empIdColumn = new TextColumn<FullUser> () {

			@Override
			public String getValue(FullUser object) {
				return String.valueOf(object.getEmpId());
			}
    	};
    	fullUserTable.addColumn(empIdColumn,"Emp ID");
    	
    	TextColumn<FullUser> userIdColumn = new TextColumn<FullUser>() {

			@Override
			public String getValue(FullUser object) {
				return object.getUserId();
			}
    		
    	};
    	fullUserTable.addColumn(userIdColumn,"User Id");
    	
    	TextColumn<FullUser> firstNameColumn= new TextColumn<FullUser>() {

			@Override
			public String getValue(FullUser object) {
				return object.getFirstName();
			}
    	};
    	fullUserTable.addColumn(firstNameColumn,"First Name");
    	
//    	TextColumn<FullUser> lastNameColumn= new TextColumn<FullUser>() {
//
//			@Override
//			public String getValue(FullUser object) {
//				return object.getLastName();
//			}
//    	};
//    	fullUserTable.addColumn(lastNameColumn,"Last Name");
		
//    	HandlerRegistration selectionHandler = selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//    	    @Override
//    	    public void onSelectionChange(SelectionChangeEvent event) {
//    	        Set<FullUser> selectedUsers = selectionModel.getSelectedSet();
//    	        selectedUsersFromTable = selectedUsers;
//    	    }
//    	});
    	

    	selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
            	selectedUsersFromTable = selectionModel.getSelectedSet();
                for (FullUser selectedUser : selectionModel.getSelectedSet()) {
                    System.out.println("Selected User: " + selectedUser.getEmpId());
                }
            }
        });
    	
    	
    	
    	cancelButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
        	
        });
    	
    	
        setText("User Selection Box");
        //setAnimationEnabled(true);
    	finalPanel.add(fullUserTable);
    	finalPanel.add(cancelButton);
    	
    	setWidget(finalPanel);
	
	}
	
	public void populateTable() {
		
		fullUserServ.getAllFullUsers(new AsyncCallback<List<FullUser>> () {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to fetch all full users");
			}

			@Override
			public void onSuccess(List<FullUser> result) {
				dataProvider.getList().clear();
				dataProvider.getList().addAll(result);
				fullUserTable.redraw();
			}

		});
		
	}
	
	
}
