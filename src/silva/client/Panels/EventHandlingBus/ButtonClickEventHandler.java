package silva.client.Panels.EventHandlingBus;

import com.google.gwt.event.shared.EventHandler;

public interface ButtonClickEventHandler extends EventHandler {
    void onButtonClick(ButtonClickEvent event);
}