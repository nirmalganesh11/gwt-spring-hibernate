package silva.client.Panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import silva.client.AppService;
import silva.client.AppServiceAsync;
import silva.client.Panels.EventHandlingBus.EventBusBoy;
import silva.shared.DesigClass;
import silva.client.Panels.EventHandlingBus.*;

public class DesignationDialog extends DialogBox {
	private final AppServiceAsync app = GWT.create(AppService.class);
    private TextBox designame;
    private TextBox maxSalary;
    private TextBox minSalary;
    
    public DesignationDialog() {
        // Set the dialog box properties
    	
    	
    	EventBusBoy ebb = EventBusBoy.getInstance();
        SimpleEventBus eventBus =ebb.getEventBus();
        
        setText("My Dialog Box");
        setAnimationEnabled(true);
        DesigClass ob = new DesigClass();

        // Create the main content panel
        VerticalPanel mainPanel = new VerticalPanel();
        
        // Create text fields
        mainPanel.setSpacing(10);
        designame = new TextBox();
        maxSalary = new TextBox();
        minSalary = new TextBox();

        // Create buttons
        Button okButton = new Button("Add");
        okButton.addClickHandler(event ->{
        	ob.setDesigname(designame.getText());
        	ob.setMinsalary(Integer.parseInt(minSalary.getText()));
        	ob.setMaxsalary(Integer.parseInt(maxSalary.getText()));
        	app.addDesignation(ob,new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.toString());
				}

				@Override
				public void onSuccess(String result) {
					Window.alert(result);
					hide();
				}
        	});
        	eventBus.fireEvent(new ButtonClickEvent());
        });
        
        
        Button cancelButton = new Button("Cancel");
        cancelButton.addClickHandler(event ->{
        	hide();
        });

        // Add buttons to a horizontal panel for layout
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add all components to the main panel
        mainPanel.add(new Label("Enter Designation"));
        mainPanel.add(designame);
        mainPanel.add(new Label("Enter Max Salary"));
        mainPanel.add(maxSalary);
        mainPanel.add(new Label("Enter Min Salary"));
        mainPanel.add(minSalary);
        mainPanel.add(buttonPanel);

        // Set the main panel as the content of the dialog box
        setWidget(mainPanel);
    }

    // Add any additional methods or logic as needed
}
