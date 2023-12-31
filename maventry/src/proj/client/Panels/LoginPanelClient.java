package proj.client.Panels;

//import com.google.gwt.user.server.Base64Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.Timer;


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
   Timer timer;
   String encoded;
   
   Button loginButton;
   Button signUpLink;
   Button testcontext;
   
   
   
   public void makeRequestToLogin(String encoded) {
	   
       String url = "/maventry/authenticationService";
       RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url);
       requestBuilder.setHeader("Authorization", "Basic " + encoded);

       try {
    	   
           requestBuilder.sendRequest(null, new RequestCallback() {
               @Override
               public void onResponseReceived(Request request, Response response) {
            	   
                   if (response.getStatusCode() == 200) {
                       String responseData = response.getText();
                       System.out.println("Response Data: " + responseData);
                       
                       checkAuthenticationAndGoToNextPage();
                       
                   } else {
                       System.err.println("HTTP Request Failed with Status Code: " + response.getStatusCode());
                   }
                   
               }

               @Override
               public void onError(Request request, Throwable exception) {
                   System.err.println("Request Error: " + exception.getMessage());
               }
           });
       } catch (RequestException e) {
           System.err.println("Request Exception: " + e.getMessage());
       }
	     
   }
   
   public void checkAuthenticationAndGoToNextPage() {
	   
	   authserv.isAuthenticated(new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}

			@Override
			public void onSuccess(Boolean result) {
				if(result) {
					RootPanel.get().clear(); 
					dbp.createDashboardPanel();
				}else {
					Window.alert("Login Failed,Incorrect Username or Password");
				}
				
			}
		});
	   
   }
  
   private native String encodeBase64(String input) /*-{
   return btoa(input);
	}-*/;

  
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
		 	loginButton = new Button("Login");
		 	loginButton.addStyleName("animated-button");
		 	loginButton.addStyleName("animated-button:hover");
		 	signUpLink = new Button("Don't have an account? Nirmal build10 Sign Up!");
		 	testcontext = new Button("Test");
	        

	        testcontext.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					authserv.getLoggedInUser(new AsyncCallback<String>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(String result) {
							Window.alert(result.toString());							
						}
						
					});
					
				}
	        	
	        	
	        });
	        
	        
	        loginButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					username = usernameTextBox.getText();
					password = passwordTextBox.getText();
					String credentials = username + ":" + password;
					encoded = encodeBase64(credentials);

					makeRequestToLogin(encoded);
												
				}
	        	
	        });

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
	        panel.add(testcontext);
	        
	        hq.add(panel);
	        whole.add(hq);
	        
	        return whole;
	    }
}
