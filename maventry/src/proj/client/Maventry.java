package proj.client;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



import proj.client.Panels.LoginPanelClient;


public class Maventry implements EntryPoint {
	
	private VerticalPanel loginPanel;
//    private VerticalPanel signupPanel;
//    public SimpleEventBus seb = new SimpleEventBus();
    public void onModuleLoad() {
    	LoginPanelClient lg = new LoginPanelClient();
//    	SignupPanelClient su= new SignupPanelClient();
//    	EventBusBoy ebb = new EventBusBoy();
        loginPanel = lg.createLoginPanel();
//        signupPanel = su.createSignupPanel();
 //       Button add = new Button("asd");
//        
        RootPanel.get().add(loginPanel);
        //RootPanel.get().add(add);
    }    

	
}
