package proj.server.Exceptions;

public class AccessDeniedException extends Exception {
 
	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
        super("Access Denied");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
