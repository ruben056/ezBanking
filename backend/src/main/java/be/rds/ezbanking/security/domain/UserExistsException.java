package be.rds.ezbanking.security.domain;

public class UserExistsException extends Exception {

	public UserExistsException(String message) {
        super(message);
    }
}
