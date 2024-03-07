package application.model.local_impl.profile.credentials;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Class Credential.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class Credential implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	private static final String USERNAME_MUST_BE_VALID = "username inputted must not be null or empty";
	private static final String PASSWORD_MUST_BE_VALID = "password inputted must not be null or empty";

	/**
	 * Instantiates a new credential.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public Credential(String username, String password) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_BE_VALID);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_BE_VALID);
		}
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Credential)) {
			return false;
		}
		var credential = (Credential) obj;
		var objectEqual = credential.getUsername().toLowerCase().equals(this.username.toLowerCase());
		return objectEqual;
	}
	
	@Override
	public int hashCode() {
		int result = Objects.hash(this.username.toLowerCase());
		return result; 
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_BE_VALID);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_BE_VALID);
		}
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_BE_VALID);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_BE_VALID);
		}
		this.password = password;
	}
}
