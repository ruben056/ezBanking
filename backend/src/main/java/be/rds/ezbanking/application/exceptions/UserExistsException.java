package be.rds.ezbanking.application.exceptions;

public class UserExistsException extends Exception {

	public UserExistsException(String message) {
        super(message);
    }
}
