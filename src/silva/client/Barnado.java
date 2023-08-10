package silva.client;
import com.google.gwt.core.client.EntryPoint;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import silva.client.Panels.LoginPanelClient;
import silva.client.Panels.SignupPanelClient;
import silva.client.Panels.EventHandlingBus.EventBusBoy;

import com.google.web.bindery.event.shared.SimpleEventBus;

public class Barnado implements EntryPoint {
	
	 	private VerticalPanel loginPanel;
	    private VerticalPanel signupPanel;
	    public SimpleEventBus seb = new SimpleEventBus();
	    public void onModuleLoad() {
	    	LoginPanelClient lg = new LoginPanelClient();
	    	SignupPanelClient su= new SignupPanelClient();
	    	EventBusBoy ebb = new EventBusBoy();
	        loginPanel = lg.createLoginPanel();
	        signupPanel = su.createSignupPanel();
	        
	        RootPanel.get().add(loginPanel);
	    }    
}
