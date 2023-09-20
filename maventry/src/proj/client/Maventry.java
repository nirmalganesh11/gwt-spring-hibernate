package proj.client;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



import proj.client.Panels.LoginPanelClient;
import proj.client.newsecurity.LoginView;


public class Maventry implements EntryPoint {
	
	private VerticalPanel loginPanel;
//    private VerticalPanel signupPanel;
//    public SimpleEventBus seb = new SimpleEventBus();
    public void onModuleLoad() {
    	//LoginPanelClient lg = new LoginPanelClient();
    	
//    	SignupPanelClient su= new SignupPanelClient();
//    	EventBusBoy ebb = new EventBusBoy();
        //loginPanel = lg.createLoginPanel();
        //loginPanel.addStyleName("login_panel_style");
//        signupPanel = su.createSignupPanel();
 //       Button add = new Button("asd");
    	
    	RootPanel.get().addStyleName("login-page");
//        
    	LoginView loginView = new LoginView();
    	 // Apply CSS class names to the widgets
        loginView.addStyleName("material-card");
        loginView.getUsernameTextBox().addStyleName("material-textbox");
        loginView.getPasswordTextBox().addStyleName("material-textbox");
        loginView.getLoginButton().addStyleName("material-button");
    
    	
        //RootPanel.get().add(loginPanel);
        RootPanel.get().add(loginView);
        //RootPanel.get().add(add);
    }    

	
}
