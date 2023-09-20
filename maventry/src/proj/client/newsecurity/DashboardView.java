package proj.client.newsecurity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialSideNav;
import proj.client.Panels.DashBoardPanelClient;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialLabel;


public class DashboardView extends Composite {
	
	private DashBoardPanelClient dbp ;
	VerticalPanel contentPanel = new VerticalPanel();
	VerticalPanel totalPanel = new VerticalPanel();
	
	
	
	
	VerticalPanel dbcp = new DashboardContentPanel();
	
    public DashboardView() {
    	
    	
        contentPanel.addStyleName("vertical-panel");
        
        VerticalPanel navBarPanel = new VerticalPanel();
        MaterialNavBar navBar = new MaterialNavBar();
        
        
        navBar.addStyleName("nav-bar"); 
        
        MaterialLink spacer1 = new MaterialLink("");
        MaterialLink spacer2 = new MaterialLink("");
        MaterialLink spacer3 = new MaterialLink("");
        MaterialLink spacer4 = new MaterialLink("");
        
        spacer1.setWidth("70px"); // Adjust the width for spacing
        spacer2.setWidth("70px"); // Adjust the width for spacing
        spacer3.setWidth("70px"); // Adjust the width for spacing
        spacer4.setWidth("70px"); // Adjust the width for spacing
        
        MaterialLink homeLink = new MaterialLink("Home");
        MaterialLink designationLink = new MaterialLink("Designation");
        MaterialLink salaryLink = new MaterialLink("Salary");
        MaterialLink securityLink = new MaterialLink("Security");
        MaterialLink logoutLink = new MaterialLink("Logout");
        
       
        navBar.add(spacer1);
        navBar.add(homeLink);
        navBar.add(spacer2);
        navBar.add(designationLink);
        navBar.add(spacer3);
        navBar.add(salaryLink);
        navBar.add(spacer4);
        navBar.add(securityLink);
        navBar.add(spacer1);
        navBar.add(logoutLink);
        
        
        navBarPanel.add(navBar);
        
        homeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				dbp = new DashBoardPanelClient();
//				RootPanel.get().clear();
//				dbp.createDashboardPanel();
//				RootPanel.get().clear();
//				DashboardContentPanel dpcDashboardView = new DashboardContentPanel();
//				RootPanel.get().add(dpcDashboardView);;
				contentPanel.add(dbcp);
			}
        	
        	
        });
        
        
        
        
        
        totalPanel.add(navBarPanel);
        totalPanel.add(contentPanel);
        
      
        initWidget(totalPanel);
        
    
    }
}
