package proj.client.Panels.EventHandlingBus;
//import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class EventBusBoy {
    private static final SimpleEventBus eventBus = new SimpleEventBus();
    private static final EventBusBoy INSTANCE = new EventBusBoy();
    
    public static SimpleEventBus getEventBus() {
        return eventBus;
    }
    public static EventBusBoy getInstance() {
        return INSTANCE;
    }
}
