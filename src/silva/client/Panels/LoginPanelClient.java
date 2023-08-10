package silva.client.Panels;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import silva.client.AppService;
import silva.client.AppServiceAsync;
import silva.shared.User;


public class LoginPanelClient {
	private final AppServiceAsync logAuth = GWT.create(AppService.class);
	 public VerticalPanel createLoginPanel() {
		 	
		 	User oblog = new User();
		 	
		 	SignupPanelClient su = new SignupPanelClient();
		 	DashBoardPanelClient dbp = new DashBoardPanelClient();
		 	
	        VerticalPanel panel = new VerticalPanel();
	        TextBox usernameTextBox = new TextBox();
	        TextBox passwordTextBox = new TextBox();
	        
	        Button loginButton = new Button("Login");
	        Label signUpLink = new Label("Don't have an account? Sign Up!");
	        
	        loginButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
					oblog.setUsername(usernameTextBox.getText());
					oblog.setPassword(passwordTextBox.getText());
					
					logAuth.loginUserAuth(oblog,new AsyncCallback<Boolean>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Login Failed Bro"+caught.toString());
						}
						@Override
						public void onSuccess(Boolean result) {
							if(result) {
								RootPanel.get().clear(); 
								dbp.createDashboardPanel(oblog);
							}else
								Window.alert("Login Failed,Incorrect Username or Password");
						}
						
					});
				}
	        	
	        });
	        signUpLink.addClickHandler(event -> {
	            RootPanel.get().clear(); 
	            RootPanel.get().add(su.createSignupPanel()); 
	        });

	        panel.add(new Label("Username:"));
	        panel.add(usernameTextBox);
	        panel.add(new Label("Password:"));
	        panel.add(passwordTextBox);
	        panel.add(loginButton);
	        panel.add(signUpLink);

	        return panel;
	    }
}
