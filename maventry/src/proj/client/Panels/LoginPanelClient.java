package proj.client.Panels;

//import com.google.gwt.user.server.Base64Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import proj.client.servicesClient.AuthenticationService;
import proj.client.servicesClient.AuthenticationServiceAsync;
import proj.shared.User;
//import java.util.Base64;

public class LoginPanelClient {

   AuthenticationServiceAsync authserv;
   User oblog;
   TextBox usernameTextBox;
   TextBox passwordTextBox;
   SignupPanelClient su;
   DashBoardPanelClient dbp;
   VerticalPanel whole;
   HorizontalPanel hq;
   VerticalPanel panel;
   String username;
   String password;
   
   public VerticalPanel createLoginPanel() {
		 	oblog = new User();
		 	authserv = GWT.create(AuthenticationService.class);
		 	
		 	su = new SignupPanelClient();
		 	usernameTextBox = new TextBox();
		 	passwordTextBox = new TextBox();
		 	dbp = new DashBoardPanelClient();
		 	whole = new VerticalPanel();
		 	hq = new HorizontalPanel();
		 	hq.setSpacing(100);
		 	whole.setSpacing(7);
		 	panel = new VerticalPanel();
	        
	        
	        Button loginButton = new Button("Login");
	        Button signUpLink = new Button("Don't have an account? Nirmal build8 Sign Up!");
	        
	        loginButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
//					oblog.setUsername(usernameTextBox.getText());
//					oblog.setPassword(passwordTextBox.getText());
//					
//				userserv.loginUserAuth(oblog,new AsyncCallback<Boolean>() {
//						@Override
//						public void onFailure(Throwable caught) {
//							Window.alert("Login Failed Bro"+caught.toString());
//						}
//						@Override
//						public void onSuccess(Boolean result) {
//							if(result) {
//								RootPanel.get().clear(); 
//								dbp.createDashboardPanel(oblog);
//							}else
//								Window.alert("Login Failed,Incorrect Username or Password");
//						}
//						
//					});
					username = usernameTextBox.getText();
					password = passwordTextBox.getText();
					String credentials = username + ":" + password;
					String encoded = encodeBase64(credentials);
					//String encoded = encodeCredentials(username,password);
					
					authserv.authenticate(encoded, new AsyncCallback<Boolean>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(Boolean result) {
							if(result) {
								oblog.setUsername(username);
								oblog.setPassword(password);
								RootPanel.get().clear(); 
								dbp.createDashboardPanel(oblog);
							}else {
								Window.alert("Login Failed,Incorrect Username or Password");
							}
							
						}
					});
					
					
					
				}
	        	
	        });
//	        signUpLink.addClickHandler(event -> {
//	            RootPanel.get().clear(); 
//	            RootPanel.get().add(su.createSignupPanel()); 
//	        });
	        signUpLink.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					  RootPanel.get().clear(); 
			          RootPanel.get().add(su.createSignupPanel()); 
				}
	        	
	        });
	        
	        panel.setSpacing(7);
	        panel.add(new Label("Username:"));
	        panel.add(usernameTextBox);
	        panel.add(new Label("Password:"));
	        panel.add(passwordTextBox);
	        panel.add(loginButton);
	        panel.add(signUpLink);
	        
	        hq.add(panel);
	        whole.add(hq);
	        
	        return whole;
	    }
   
   private String encodeCredentials(String username, String password) {
       String credentials = username + ":" + password;
       //String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
      // return encodedCredentials;
       return "";
   }
   private native String encodeBase64(String input) /*-{
   return btoa(input);
	}-*/;

   
   
}
