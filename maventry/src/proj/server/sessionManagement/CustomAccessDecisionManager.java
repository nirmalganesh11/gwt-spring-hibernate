package proj.server.sessionManagement;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomAccessDecisionManager extends AbstractAccessDecisionManager {
	//private List<AccessDecisionVoter<? extends Object>> decisionVoters;
	
	 public CustomAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
		 	
	        super(decisionVoters);
	        
	    }
	 
//	 public CustomAccessDecisionManager() {
//		super(this.decisionVoters);
//	 }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes == null) {
            return; 
        }

        for (ConfigAttribute attribute : configAttributes) {
            if (this.supports(attribute)) {
                if (!authentication.getAuthorities().contains(new SecurityConfig(attribute.getAttribute()))) {
                    throw new AccessDeniedException("Access is denied");
                }
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute.getAttribute() != null && attribute.getAttribute().startsWith("ROLE_");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
