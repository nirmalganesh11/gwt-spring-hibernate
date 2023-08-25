package proj.client.Panels.EventHandlingBus;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ButtonClickEvent extends GwtEvent<ButtonClickEventHandler> {
	 public interface ButtonClickEventHandler extends EventHandler {
		 	void onButtonClick(ButtonClickEvent event);
	    }

	    public static Type<proj.client.Panels.EventHandlingBus.ButtonClickEventHandler> TYPE = new Type<>();

	    @Override
	    public Type<proj.client.Panels.EventHandlingBus.ButtonClickEventHandler> getAssociatedType() {
	        return TYPE;
	    }

//		@Override
//		protected void dispatch(ButtonClickEventHandler handler) {
//			// TODO Auto-generated method stub
//			
//		}

		@Override
		protected void dispatch(proj.client.Panels.EventHandlingBus.ButtonClickEventHandler handler) {
			// TODO Auto-generated method stub
			handler.onButtonClick(this);
			
		}
   
}
