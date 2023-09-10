package proj.server.security.filters;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ade)
			throws IOException, ServletException {
		HttpServletRequest httpReq = request;
		if ( httpReq.getRequestedSessionId() != null){
			if (!httpReq.isRequestedSessionIdValid()) {
				//Expired session
				AuthenticationCredentialsNotFoundException ae = new AuthenticationCredentialsNotFoundException("SEC-011");
				SecurityContextHolder.getContext().setAuthentication(null);
				throw ae;				
			}
		}
	}
}
