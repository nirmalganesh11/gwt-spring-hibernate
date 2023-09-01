package proj.server.security;



import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionListener implements HttpSessionListener {

	private static int totalActiveSessions;
	private static Log log = LogFactory.getLog(SessionListener.class);
	
	 public static int getTotalActiveSession(){
			return totalActiveSessions;
	 }
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		totalActiveSessions++;
		log.info("Active Session Count Increased-Total Count: " + totalActiveSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		if(totalActiveSessions > 0){
			totalActiveSessions--;
		}
		log.info("Active Session Count Decreased-Total Count: " + totalActiveSessions);
	}

}
