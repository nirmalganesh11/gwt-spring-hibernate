package proj.client.Panels;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import proj.client.servicesClient.DesigServiceClient;
import proj.client.servicesClient.DesigServiceClientAsync;

import proj.client.servicesClient.UserServiceClient;
import proj.client.servicesClient.UserServiceClientAsync;
import proj.shared.User;


public class SignupPanelClient {

    //private final DesigServiceClientAsync desigserv = GWT.create(DesigServiceClient.class);
    private final UserServiceClientAsync userserv = GWT.create(UserServiceClient.class);
    private final LoginPanelClient lg = new LoginPanelClient();
	private final DashBoardPanelClient dbp = new DashBoardPanelClient();
	
	private final User newUser = new User();
	
	private final VerticalPanel panel = new VerticalPanel();
    private final TextBox newUsernameTextBox = new TextBox();
    private final TextBox newPasswordTextBox = new TextBox();
    private final TextBox inputRoles = new TextBox();
    private final ListBox desigDropdown = new ListBox();
    private final Label checkUsernameLabel = new Label();
    
    String array[] = {};
    
	public VerticalPanel createSignupPanel() {
		
		
		
		
		
		
//		 EventBusBoy ebb = EventBusBoy.getInstance();
//	     @SuppressWarnings("static-access")
//	     SimpleEventBus eventBus =ebb.getEventBus();
		
		
       
        
        //keeping the default role when no changes are made
		array[0] ="ceo";
        newUser.setRolesArray(array);
        desigDropdown.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = desigDropdown.getSelectedIndex();
				String desigValue = desigDropdown.getValue(selectedIndex);
				array[0] = desigValue;
				newUser.setRolesArray(array);
			}
        });
        
        
//        desigserv.getDesignations(new AsyncCallback<List<String>>(){
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.toString());
//			}
//
//			@Override
//			public void onSuccess(List<String> result) {
//				for (String designame : result){
//			            desigDropdown.addItem(designame);
//			    }
//				//Window.alert("added few fields in desigdrowdown");
//			}
//        });
        
//        eventBus.addHandler(ButtonClickEvent.TYPE, new ButtonClickEventHandler() {
//     			@Override
//     			public void onButtonClick(ButtonClickEvent event) {
////     				eventBus.fireEvent(new ButtonClickEvent());
//     				 app.getDesignations(new AsyncCallback<List<String>>(){
//     						@Override
//     						public void onFailure(Throwable caught) {
//     							Window.alert(caught.toString());
//     						}
//     						@Override
//     						public void onSuccess(List<String> result) {
//     							desigDropdown.clear();
//     							for (String designame : result){
//     						            desigDropdown.addItem(designame);
//     						    }
//     							Window.alert("added few fields in desigdrowdown");
//     						}
//     			        });
//     			}
//             });
        
        
        
        
        
        Button usernameCheckButton = new Button("Check");
        usernameCheckButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String toBeCheckedUsername = newUsernameTextBox.getText();
				userserv.checkUsername(toBeCheckedUsername,new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						checkUsernameLabel.setText(result);
					}
					
				});
				
			}
        	
        });
        
        
        
     
        Button signUpButton = new Button("Sign Up");
        signUpButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(!newUsernameTextBox.getText().equalsIgnoreCase("") && !newPasswordTextBox.getText().equalsIgnoreCase("")) {
					newUser.setUsername(newUsernameTextBox.getText());
					newUser.setPassword(newPasswordTextBox.getText());
					String arr[] = inputRoles.getText().split(" ");
					newUser.setRolesArray(arr);
					userserv.signUpAuth(newUser, new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("user creation failed"+"due to"+ caught.toString());
						}
						@Override
						public void onSuccess(String result) {
							Window.alert("User Signup Happened");
							RootPanel.get().clear(); 
							dbp.createDashboardPanel(newUser);
						}
					});
				}
			}
        	
        });
        Button loginLink = new Button("Already have an account? Login!");
//        loginLink.addClickHandler(event -> {
//            RootPanel.get().clear(); 
//            RootPanel.get().add(lg.createLoginPanel()); 
//        });
        loginLink.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear(); 
	            RootPanel.get().add(lg.createLoginPanel()); 
			}
        	
        });
        
        HorizontalPanel hq = new HorizontalPanel();
        VerticalPanel vpn = new VerticalPanel();
     
        hq.setSpacing(100);
       	panel.setSpacing(7);
        panel.add(new Label("New Username:"));
        panel.add(newUsernameTextBox);
        panel.add(checkUsernameLabel);
        panel.add(usernameCheckButton);
        panel.add(new Label("New Password:"));
        panel.add(newPasswordTextBox);
        panel.add(new Label("Input roles With space"));
        panel.add(inputRoles);
       // panel.add(new Label("Choose designation"));
       // panel.add(desigDropdown);
        panel.add(signUpButton);
        panel.add(loginLink);
        hq.add(panel);
        vpn.add(hq);
        
        
        return vpn;
    }
}
