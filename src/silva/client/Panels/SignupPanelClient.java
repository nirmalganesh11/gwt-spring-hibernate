package silva.client.Panels;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import silva.client.AppService;
import silva.client.AppServiceAsync;
import silva.client.Panels.EventHandlingBus.ButtonClickEvent;
import silva.client.Panels.EventHandlingBus.ButtonClickEventHandler;
import silva.client.Panels.EventHandlingBus.EventBusBoy;
import silva.shared.User;


public class SignupPanelClient {
	private final AppServiceAsync app = GWT.create(AppService.class);
	public VerticalPanel createSignupPanel() {
		
		LoginPanelClient lg = new LoginPanelClient();
		DashBoardPanelClient dbp = new DashBoardPanelClient();
		
		
		User newUser = new User();
		
		 EventBusBoy ebb = EventBusBoy.getInstance();
	     @SuppressWarnings("static-access")
	     SimpleEventBus eventBus =ebb.getEventBus();
		
		VerticalPanel panel = new VerticalPanel();
        TextBox newUsernameTextBox = new TextBox();
        TextBox newPasswordTextBox = new TextBox();
        ListBox desigDropdown = new ListBox();
        Label checkUsernameLabel = new Label();
        
        
        //keeping the default role when no changes are made
        newUser.setDesignation("Ceo");
        desigDropdown.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selectedIndex = desigDropdown.getSelectedIndex();
				String desigValue = desigDropdown.getValue(selectedIndex);
				newUser.setDesignation(desigValue);
			}
        });
        
        
        app.getDesignations(new AsyncCallback<List<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(List<String> result) {
				for (String designame : result){
			            desigDropdown.addItem(designame);
			    }
				Window.alert("added few fields in desigdrowdown");
			}
        });
        
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
				app.checkUsername(toBeCheckedUsername,new AsyncCallback<String>() {
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
				newUser.setUsername(newUsernameTextBox.getText());
				newUser.setPassword(newPasswordTextBox.getText());
				app.signUpAuth(newUser, new AsyncCallback<String>() {
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
        	
        });
        Label loginLink = new Label("Already have an account? Login!");
        loginLink.addClickHandler(event -> {
            RootPanel.get().clear(); 
            RootPanel.get().add(lg.createLoginPanel()); 
        });
        
        
        panel.add(new Label("New Username:"));
        panel.add(newUsernameTextBox);
        panel.add(checkUsernameLabel);
        panel.add(usernameCheckButton);
        panel.add(new Label("New Password:"));
        panel.add(newPasswordTextBox);
        panel.add(new Label("Choose designation"));
        panel.add(desigDropdown);
        panel.add(signUpButton);
        panel.add(loginLink);
        
        return panel;
    }
}
