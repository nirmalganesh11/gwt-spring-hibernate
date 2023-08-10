package silva.Exceptions;
@SuppressWarnings("serial")
public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
