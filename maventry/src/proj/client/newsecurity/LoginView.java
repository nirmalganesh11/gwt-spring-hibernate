package proj.client.newsecurity;


import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import proj.client.Panels.DashBoardPanelClient;
import proj.client.servicesClient.AuthenticationService;
import proj.client.servicesClient.AuthenticationServiceAsync;

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
import com.google.gwt.user.client.ui.*;

public class LoginView extends Composite {
	
	private AuthenticationServiceAsync authserv;
	 
	
    private MaterialTextBox usernameTextBox;
    private MaterialTextBox passwordTextBox;
    private MaterialButton loginButton;
    
    private DashBoardPanelClient dbp;
    
    private String username;
    private String password;
    private String encoded;
    
    public LoginView() {
    	
        MaterialCard card = new MaterialCard();
        authserv = GWT.create(AuthenticationService.class);
        dbp = new DashBoardPanelClient();
        
        
        HTML heading = new HTML("<center><h3>System Login</h3><center>");
        
        MaterialLabel usernameLabel = new MaterialLabel("Username:");
        // Create widgets for the login form
        usernameTextBox = new MaterialTextBox();
        
        MaterialLabel passwordLabel = new MaterialLabel("Password:");
        passwordTextBox = new MaterialTextBox();
        
        MaterialLink forgotPassword = new MaterialLink("forgot your password?");
        
        
        
        loginButton = new MaterialButton(ButtonType.RAISED, "Submit", new MaterialIcon(IconType.SEND));
        
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
        forgotPassword.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get().addStyleName("login-page");
				ResetPasswordPage rsp = new ResetPasswordPage();
				rsp.addStyleName("material-card");
		        rsp.getUserIdTextBox().addStyleName("material-textbox");
		        rsp.getEmailTextBox().addStyleName("material-textbox");
		        rsp.getResetPasswordButton().addStyleName("material-button");
				RootPanel.get().add(rsp);
			}
        });

        card.add(heading);
        card.add(usernameLabel);
        card.add(usernameTextBox);
        card.add(passwordLabel);
        card.add(passwordTextBox);
        card.add(forgotPassword);
        card.add(loginButton);

        initWidget(card);
    }

    public MaterialTextBox getUsernameTextBox() {
		return usernameTextBox;
	}

	public void setUsernameTextBox(MaterialTextBox usernameTextBox) {
		this.usernameTextBox = usernameTextBox;
	}

	public MaterialTextBox getPasswordTextBox() {
		return passwordTextBox;
	}

	public void setPasswordTextBox(MaterialTextBox passwordTextBox) {
		this.passwordTextBox = passwordTextBox;
	}

	public void setLoginButton(MaterialButton loginButton) {
		this.loginButton = loginButton;
	}

	// You can provide getters for the form fields to access their values.
    public String getUsername() {
        return usernameTextBox.getText();
    }

    public String getPassword() {
        return passwordTextBox.getText();
    }

    public MaterialButton getLoginButton() {
        return loginButton;
    }
    
    
    
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
 					dbp= new DashBoardPanelClient();
 					dbp.createDashboardPanel();
// 					RootPanel.get().clear();
// 					DashboardContentPanel dbcp = new DashboardContentPanel();
// 					RootPanel.get().add(dbcp);
 					//dbp.createDashboardPanel();
 				}else {
 					Window.alert("Login Failed,Incorrect Username or Password");
 				}
 				
 			}
 		});
 	   
    }
   
    private native String encodeBase64(String input) /*-{
    return btoa(input);
 	}-*/;

}
