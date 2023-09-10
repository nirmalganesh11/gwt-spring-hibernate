package proj.client.Security;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import proj.client.Panels.DashBoardPanelClient;
import proj.client.servicesClient.UserServiceClient;
import proj.client.servicesClient.UserServiceClientAsync;

public class SecurityPanel {
	
	private Button usersButton;
	private Button groupsButton;
	private Button rolesButton;
	private Button userProfileButton;
	private VerticalPanel finalPanel;
	private VerticalPanel changePanel;
	private  Button cancelButton;
	
	private UserServiceClientAsync userServ;
	
	private Button testButton = new Button("TEST");
	
	public void createSecurityPanel() {
		finalPanel = new VerticalPanel();
		changePanel = new VerticalPanel();
		
		
		usersButton = new Button("Users");
		groupsButton = new Button("Groups");
		rolesButton = new Button("Roles");
		userProfileButton = new Button("Profile");
		cancelButton = new Button("Return To Home");
		
		 userServ = GWT.create(UserServiceClient.class);
		
		HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setSpacing(20);
		headerPanel.add(cancelButton);
		headerPanel.add(usersButton);
		headerPanel.add(groupsButton);
		headerPanel.add(rolesButton);
		headerPanel.add(userProfileButton);
		headerPanel.add(testButton);
		
		
		changePanel.clear();
		VerticalPanel userPanel	= new UserPanel();
		changePanel.add(userPanel);
		
		usersButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				changePanel.clear();
				VerticalPanel userPanel	= new UserPanel();
				changePanel.add(userPanel);
			}
			
		});
		
		rolesButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				changePanel.clear();
				VerticalPanel rolePanel = new RolePanel();
				changePanel.add(rolePanel);
			}
			
		});
		
	      
	    cancelButton.addClickHandler(new ClickHandler(){
	    	
				@Override
				public void onClick(ClickEvent event) {
					RootPanel.get().clear();
					DashBoardPanelClient dbp = new DashBoardPanelClient();
					dbp.createDashboardPanel();
				}
				
	    });
	    
	    testButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				
				
			}
	    	
	    });
		
	    changePanel.setSpacing(20);
	
		
		finalPanel.add(headerPanel);
		finalPanel.add(changePanel);
		
		
		
		RootPanel.get().add(finalPanel);
		
	}
}
